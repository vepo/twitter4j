package io.vepo.twitter4j;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vepo.twitter4j.TwitterClientException.CauseType;
import io.vepo.twitter4j.api.GetStreamRulesResponse;
import io.vepo.twitter4j.api.RuleData;
import io.vepo.twitter4j.api.RulesDeleteIds;
import io.vepo.twitter4j.api.TwitterOAuth2Token;
import io.vepo.twitter4j.api.UpdateStreamRulesRequest;
import io.vepo.twitter4j.api.UpdateStreamRulesResponse;

public class TwitterClient {
    private static final Logger logger = LoggerFactory.getLogger(TwitterClient.class);

    public class TwitterStreamClient {

        private List<Rule> rules;
        private ExecutorService executor;

        private TwitterStreamClient() {
            this.rules = new ArrayList<>();
            executors.add(this.executor = Executors.newSingleThreadExecutor());
        }

        public TwitterStreamClient with(Rule rule) {
            logger.debug("Adding rule: {}", rule);
            this.rules.add(rule);
            return this;
        }

        public TwitterClient consume(Consumer<Tweet> tweetConsumer) {
            executor.submit(this::removeOldrules);
            executor.submit(this::createRules);
            executor.submit(() -> consumeStream(tweetConsumer));
            return TwitterClient.this;
        }

        private void removeOldrules() {
            try {
                logger.trace("Removing old rules...");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                         .GET()
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                logger.trace("Old rules request: {}", request);
                var response = httpClient.send(request, BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    var body = objectMapper.readValue(response.body(), GetStreamRulesResponse.class);
                    if (!body.getData().isEmpty()) {
                        deleteRules(body.getData().stream().map(RuleData::getId).collect(toList()));
                    }
                }
                logger.trace("Old rules response: {}", response);
            } catch (IOException e) {
                logger.error("Error!", e);
                throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
            } catch (InterruptedException e) {
                // Finishing
                Thread.currentThread().interrupt();
            }
        }

        private void deleteRules(List<String> ids) {
            if (!ids.isEmpty()) {
                logger.trace("Deleting all rules...");
                try {
                    var request = HttpRequest.newBuilder()
                                             .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                             .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(new UpdateStreamRulesRequest(new RulesDeleteIds(ids)))))
                                             .header("Content-Type", "application/json")
                                             .header("Authorization", generateAutorizationHeader())
                                             .build();
                    logger.trace("Delete all rules request: {}", request);
                    var response = httpClient.send(request, BodyHandlers.ofString());
                    logger.trace("Delete all rules response: {}", response);
                } catch (JsonProcessingException e) {
                    logger.error("Error!", e);
                    throw new TwitterClientException(CauseType.SERIALIZATION_ERROR, e);
                } catch (IOException e) {
                    logger.error("Error!", e);
                    throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
                } catch (InterruptedException e) {
                    // Finishing
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void consumeStream(Consumer<Tweet> tweetConsumer) {
            try {
                logger.trace("Starting consumer...");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream?tweet.fields=created_at&expansions=author_id&user.fields=created_at"))
                                         .GET()
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                logger.trace("Start consumer request: {}", request);
                var response = httpClient.send(request, BodyHandlers.ofInputStream());
                logger.trace("Start consumer response: {}", response);
                if (response.statusCode() == 200) {
                    try (BufferedReader reader =
                            new BufferedReader(new InputStreamReader(response.body(), Charset.forName("UTF-8")))) {
                        String line;
                        do {
                            line = reader.readLine();
                            if (nonNull(line) && !line.isBlank()) {
                                tweetConsumer.accept(objectMapper.readValue(line, Tweet.class));
                            }

                        } while (nonNull(line) && !executor.isShutdown());

                    }
                }
                logger.info("Consumer exited!");
            } catch (IOException e) {
                logger.error("Error!", e);
                throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
            } catch (InterruptedException e) {
                // Finishing
                Thread.currentThread().interrupt();
            }
        }

        private void createRules() {
            if (this.rules.isEmpty()) {
                throw new IllegalStateException("Add at least one rule!");
            }
            try {
                logger.trace("Creating rules...");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                         .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(new UpdateStreamRulesRequest(rules))))
                                         .header("Content-Type", "application/json")
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                logger.trace("Create rules request: {}", request);
                var response = httpClient.send(request, BodyHandlers.ofString());
                logger.trace("Create rules response: {}", response);
                if (response.statusCode() == 201) {
                    var data = objectMapper.readValue(response.body(), UpdateStreamRulesResponse.class);
                    if (hasErrors(data)) {
                        logger.error("Rules has errors: {}", data);
                        throw new TwitterClientException(CauseType.INVALID_RESPONSE);
                    }
                } else {
                    logger.warn("Unexpected status code! status={} answer={}", response.statusCode(), response.body());
                }
            } catch (JsonProcessingException e) {
                logger.error("Error!", e);
                throw new TwitterClientException(CauseType.SERIALIZATION_ERROR, e);
            } catch (IOException e) {
                logger.error("Error!", e);
                throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
            } catch (InterruptedException e) {
                // Finishing
                Thread.currentThread().interrupt();
            }
        }

        private boolean hasErrors(UpdateStreamRulesResponse data) {
            return data.getErrors()
                       .stream()
                       .noneMatch(error -> error.getType().compareToIgnoreCase("Duplicated") != 0);
        }

    }

    private String apiKey;
    private String apiKeySecret;
    private ObjectMapper objectMapper;
    private TwitterOAuth2Token oauth2Token;
    private HttpClient httpClient;
    private List<ExecutorService> executors;

    public TwitterClient(String apiKey, String apiKeySecret) {
        this.apiKey = apiKey;
        this.apiKeySecret = apiKeySecret;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
        this.executors = new ArrayList<>();
    }

    public TwitterClient authenticate() {
        try {
            logger.trace("Starting authentication...");
            var authRequest = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/oauth2/token"))
                                         .POST(BodyPublishers.ofString("grant_type=client_credentials"))
                                         .header("Authorization", generateAutorizationHeaderForNonAuthenticated())
                                         .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                         .build();
            var response = httpClient.send(authRequest, BodyHandlers.ofString());
            switch (response.statusCode()) {
                case 200 -> this.oauth2Token = objectMapper.readValue(response.body(), TwitterOAuth2Token.class);
                case 403 -> throw new TwitterClientException(CauseType.INVALID_CREDENTIALS);
                case 500 -> throw new TwitterClientException(CauseType.SERVER_ERROR);
                default -> throw new IllegalArgumentException("Unexpected value: " + response.statusCode());
            }
            logger.info("Authenticated!");
        } catch (JsonProcessingException e) {
            logger.error("Error!", e);
            throw new TwitterClientException(CauseType.INVALID_RESPONSE, e);
        } catch (IOException e) {
            logger.error("Error!", e);
            throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
        } catch (InterruptedException e) {
            // Finishing
            Thread.currentThread().interrupt();
        }
        return this;
    }

    public TwitterStreamClient stream() {
        if (isAutheticated()) {
            return new TwitterStreamClient();
        } else {
            throw new IllegalStateException("Not Authenticated! Call authenticate() before creating Stream Client!");
        }
    }

    public boolean isAutheticated() {
        return Objects.nonNull(this.oauth2Token);
    }

    private String generateAutorizationHeaderForNonAuthenticated() {
        return "Basic " + Base64.getEncoder().encodeToString(((apiKey + ":" + apiKeySecret).getBytes()));
    }

    private String generateAutorizationHeader() {
        String header;
        switch (this.oauth2Token.getTokenType()) {
            case "bearer" -> header = "Bearer " + this.oauth2Token.getAccessToken();
            default -> throw new IllegalArgumentException("Unexpected value: " + this.oauth2Token.getAccessToken());
        }

        return header;
    }

    public void join() {
        AtomicBoolean running = new AtomicBoolean(true);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Shutdown signal received...");
                executors.forEach(ExecutorService::shutdown);
                logger.info("Clients shutdown required...");
                executors.forEach(executor -> {
                    try {
                        executor.awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                running.set(false);
                logger.info("Shutdown hookup finished!");
            }
        });
        while (executors.stream().allMatch(exec -> {
            try {
                return !exec.awaitTermination(10, TimeUnit.SECONDS) && !exec.isShutdown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return true;
            }
        }))
            ;
        logger.info("Clients finished!");
    }

}
