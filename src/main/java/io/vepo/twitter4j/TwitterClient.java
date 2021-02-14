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
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vepo.twitter4j.TwitterClientException.CauseType;
import io.vepo.twitter4j.api.GetStreamRulesResponse;
import io.vepo.twitter4j.api.RuleData;
import io.vepo.twitter4j.api.RulesDeleteIds;
import io.vepo.twitter4j.api.UpdateStreamRulesRequest;
import io.vepo.twitter4j.api.UpdateStreamRulesResponse;

public class TwitterClient {
    public class TwitterStreamClient {

        private List<Rule> rules;

        private TwitterStreamClient() {
            this.rules = new ArrayList<>();
        }

        public TwitterStreamClient with(Rule rule) {
            System.out.println("Adding rule: " + rule);
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
                System.out.println("Consumer");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                         .GET()
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                System.out.println("Remove rules request=" + request);
                var response = httpClient.send(request, BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    var body = objectMapper.readValue(response.body(), GetStreamRulesResponse.class);
                    if (!body.getData().isEmpty()) {
                        deleteRules(body.getData().stream().map(RuleData::getId).collect(toList()));
                    }
                }
                System.out.println("Remove rules response=" + response);

                System.out.println(response.body());
            } catch (IOException e) {
                throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
            } catch (InterruptedException e) {
                // Finishing
                Thread.currentThread().interrupt();
            }
        }

        private void deleteRules(List<String> ids) {
            if (!ids.isEmpty()) {
                try {
                    var request = HttpRequest.newBuilder()
                                             .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                             .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(new UpdateStreamRulesRequest(new RulesDeleteIds(ids)))))
                                             .header("Content-Type", "application/json")
                                             .header("Authorization", generateAutorizationHeader())
                                             .build();
                    System.out.println("Stream request: " + request);
                    var response = httpClient.send(request, BodyHandlers.ofString());
                    System.out.println("Delete old rules: " + response);
                } catch (JsonProcessingException e) {
                    throw new TwitterClientException(CauseType.SERIALIZATION_ERROR, e);
                } catch (IOException e) {
                    throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
                } catch (InterruptedException e) {
                    // Finishing
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void consumeStream(Consumer<Tweet> tweetConsumer) {
            try {
                System.out.println("Consumer");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream?tweet.fields=created_at&expansions=author_id&user.fields=created_at"))
                                         .GET()
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                System.out.println("Consumer request=" + request);
                var response = httpClient.send(request, BodyHandlers.ofInputStream());
                if (response.statusCode() == 200) {
                    try (BufferedReader reader =
                            new BufferedReader(new InputStreamReader(response.body(), Charset.forName("UTF-8")))) {
                        String line;
                        do {
                            line = reader.readLine();
                            if (nonNull(line) && !line.isBlank()) {
                                tweetConsumer.accept(objectMapper.readValue(line, Tweet.class));
                            }
                        } while (nonNull(line));

                    }
                }
                System.out.println("Consumer response=" + response);
            } catch (IOException e) {
                throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
            } catch (InterruptedException e) {
                // Finishing
                Thread.currentThread().interrupt();
            }
            System.out.println("Why!?!?");
        }

        private void createRules() {
            System.out.println("Creating Stream...");
            if (this.rules.isEmpty()) {
                throw new IllegalStateException("Add at least one rule!");
            }
            try {
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/2/tweets/search/stream/rules"))
                                         .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(new UpdateStreamRulesRequest(rules))))
                                         .header("Content-Type", "application/json")
                                         .header("Authorization", generateAutorizationHeader())
                                         .build();
                System.out.println("Stream request: " + request);
                var response = httpClient.send(request, BodyHandlers.ofString());
                System.out.println(response.body());
                if (response.statusCode() == 201) {
                    var data = objectMapper.readValue(response.body(), UpdateStreamRulesResponse.class);
                    if (hasErrors(data)) {
                        System.out.println("ERROR: " + data);
                        throw new TwitterClientException(CauseType.INVALID_RESPONSE);
                    } else {
                        System.out.println("No Errors! " + data);
                    }
                } else {
                    System.out.println(response);
                }
                System.out.println("DONE");
            } catch (JsonProcessingException e) {
                throw new TwitterClientException(CauseType.SERIALIZATION_ERROR, e);
            } catch (IOException e) {
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
    private TwiiterOAuth2Token oauth2Token;
    private HttpClient httpClient;
    private ExecutorService executor;

    public TwitterClient(String apiKey, String apiKeySecret) {
        this.apiKey = apiKey;
        this.apiKeySecret = apiKeySecret;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public TwitterClient authenticate() {
        try {
            var authRequest = HttpRequest.newBuilder()
                                         .uri(URI.create("https://api.twitter.com/oauth2/token"))
                                         .POST(BodyPublishers.ofString("grant_type=client_credentials"))
                                         .header("Authorization", generateAutorizationHeaderForNonAuthenticated())
                                         .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                         .build();
            var response = httpClient.send(authRequest, BodyHandlers.ofString());
            switch (response.statusCode()) {
                case 200 -> this.oauth2Token = objectMapper.readValue(response.body(), TwiiterOAuth2Token.class);
                case 403 -> throw new TwitterClientException(CauseType.INVALID_CREDENTIALS);
                case 500 -> throw new TwitterClientException(CauseType.SERVER_ERROR);
                default -> throw new IllegalArgumentException("Unexpected value: " + response.statusCode());
            }
            System.out.println("Autenticado! " + this.oauth2Token);
        } catch (JsonProcessingException e) {
            throw new TwitterClientException(CauseType.INVALID_RESPONSE, e);
        } catch (IOException e) {
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

}
