package io.vepo.twitter4j.impl;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.vepo.twitter4j.Rule;
import io.vepo.twitter4j.TwitterClient;
import io.vepo.twitter4j.TwitterClientException;
import io.vepo.twitter4j.TwitterClientException.CauseType;
import io.vepo.twitter4j.impl.api.GetStreamRulesResponse;
import io.vepo.twitter4j.impl.api.RuleData;
import io.vepo.twitter4j.impl.api.RulesDeleteIds;
import io.vepo.twitter4j.impl.api.UpdateStreamRulesRequest;
import io.vepo.twitter4j.impl.api.UpdateStreamRulesResponse;
import io.vepo.twitter4j.TwitterStreamClient;
import io.vepo.twitter4j.stream.TweetInfo;

public class TwitterStreamClientImplementation implements TwitterStreamClient {
    private static final Logger logger = LoggerFactory.getLogger(TwitterStreamClientImplementation.class);

    private static final String QS_POLL_FIELDS = "poll.fields";
    private static final String QS_PLACE_FIELDS = "place.fields";
    private static final String QS_MEDIA_FIELDS = "media.fields";
    private static final String QS_USER_FIELDS = "user.fields";
    private static final String QS_EXPANSIONS = "expansions";
    private static final String QS_TWEET_FIELDS = "tweet.fields";
    private static final String TWITTER_ENDPOINT_STREAM = "https://api.twitter.com/2/tweets/search/stream";
    private static final String TWITTER_ENDPOINT_STREAM_RULES = "https://api.twitter.com/2/tweets/search/stream/rules";

    private final List<Rule> rules;
    private final ExecutorService executor;
    private final Set<TweetFields> tweetFields;
    private final Set<Expansions> expansions;
    private final Set<UserFields> userFields;
    private final Set<MediaFields> mediaFields;
    private final Set<PlaceFields> placeFields;
    private final Set<PollFields> pollFields;

    private Context context;

    TwitterStreamClientImplementation(Context context) {
        this.rules = new ArrayList<>();
        this.executor = Executors.newSingleThreadExecutor();
        tweetFields = new HashSet<TweetFields>();
        expansions = new HashSet<Expansions>();
        userFields = new HashSet<UserFields>();
        mediaFields = new HashSet<MediaFields>();
        placeFields = new HashSet<PlaceFields>();
        pollFields = new HashSet<PollFields>();
        this.context = context;
    }

    private String buildStreamParameters() {
        var params = new ArrayList<String>();
        if (this.tweetFields.size() > 0) {
            params.add(QS_TWEET_FIELDS + "=" + this.tweetFields.stream()
                                                               .map(TweetFields::getQueryValue)
                                                               .collect(joining(",")));
        }

        if (this.expansions.size() > 0) {
            params.add(QS_EXPANSIONS + "=" + this.expansions.stream()
                                                            .map(Expansions::getQueryValue)
                                                            .collect(joining(",")));
        }

        if (this.userFields.size() > 0) {
            params.add(QS_USER_FIELDS + "=" + this.userFields.stream()
                                                             .map(UserFields::getQueryValue)
                                                             .collect(joining(",")));
        }

        if (this.mediaFields.size() > 0) {
            params.add(QS_MEDIA_FIELDS + "=" + this.mediaFields.stream()
                                                               .map(MediaFields::getQueryValue)
                                                               .collect(joining(",")));
        }

        if (this.placeFields.size() > 0) {
            params.add(QS_PLACE_FIELDS + "=" + this.placeFields.stream()
                                                               .map(PlaceFields::getQueryValue)
                                                               .collect(joining(",")));
        }

        if (this.pollFields.size() > 0) {
            params.add(QS_POLL_FIELDS + "=" + this.pollFields.stream()
                                                             .map(PollFields::getQueryValue)
                                                             .collect(joining(",")));
        }

        return params.size() > 0 ? ("?" + params.stream().collect(joining("&"))) : "";
    }

    @Override
    public TwitterClient consume(Consumer<TweetInfo> tweetConsumer) {
        executor.submit(this::removeOldrules);
        executor.submit(this::createRules);
        executor.submit(() -> consumeStream(tweetConsumer));
        return context.main;
    }

    private void consumeStream(Consumer<TweetInfo> tweetConsumer) {
        try {
            logger.trace("Starting consumer...");
            var request = HttpRequest.newBuilder()
                                     .uri(URI.create(TWITTER_ENDPOINT_STREAM + buildStreamParameters()))
                                     .GET()
                                     .header("Authorization", context.generateAutorizationHeader())
                                     .build();
            logger.trace("Start consumer request: {}", request);
            var response = context.getInputStreamResponse(request);
            logger.trace("Start consumer response: {}", response);
            if (response.statusCode() == 200) {
                try (BufferedReader reader =
                        new BufferedReader(new InputStreamReader(response.body(), Charset.forName("UTF-8")))) {
                    String line;
                    do {
                        line = reader.readLine();
                        if (nonNull(line) && !line.isBlank()) {
                            logger.info("Processando line: {}", line);
                            tweetConsumer.accept(this.context.readValue(line, TweetInfo.class));
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
                                     .uri(URI.create(TWITTER_ENDPOINT_STREAM_RULES))
                                     .POST(BodyPublishers.ofString(context.writeValueAsString(new UpdateStreamRulesRequest(rules))))
                                     .header("Content-Type", "application/json")
                                     .header("Authorization", context.generateAutorizationHeader())
                                     .build();
            logger.trace("Create rules request: {}", request);
            var response = context.getStringResponse(request);
            logger.trace("Create rules response: {}", response);
            if (response.statusCode() == 201) {
                var data = context.readValue(response.body(), UpdateStreamRulesResponse.class);
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

    private void deleteRules(List<String> ids) {
        if (!ids.isEmpty()) {
            logger.trace("Deleting all rules...");
            try {
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create(TWITTER_ENDPOINT_STREAM_RULES))
                                         .POST(BodyPublishers.ofString(context.writeValueAsString(new UpdateStreamRulesRequest(new RulesDeleteIds(ids)))))
                                         .header("Content-Type", "application/json")
                                         .header("Authorization", context.generateAutorizationHeader())
                                         .build();
                logger.trace("Delete all rules request: {}", request);
                var response = context.getStringResponse(request);
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

    private boolean hasErrors(UpdateStreamRulesResponse data) {
        return data.getErrors()
                   .stream()
                   .noneMatch(error -> error.getType().compareToIgnoreCase("Duplicated") != 0);
    }

    private void removeOldrules() {
        try {
            logger.trace("Removing old rules...");
            var request = HttpRequest.newBuilder()
                                     .uri(URI.create(TWITTER_ENDPOINT_STREAM_RULES))
                                     .GET()
                                     .header("Authorization", context.generateAutorizationHeader())
                                     .build();
            logger.trace("Old rules request: {}", request);
            var response = context.getStringResponse(request);
            if (response.statusCode() == 200) {
                var body = context.readValue(response.body(), GetStreamRulesResponse.class);
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

    @Override
    public TwitterStreamClient requestExpansion(Expansions expansion) {
        this.expansions.add(expansion);
        return this;
    }

    @Override
    public TwitterStreamClient requestExpansions(Set<Expansions> expansions) {
        this.expansions.addAll(expansions);
        return this;
    }

    @Override
    public TwitterStreamClient requestMediaField(MediaFields mediaField) {
        this.mediaFields.add(mediaField);
        return this;
    }

    @Override
    public TwitterStreamClient requestMediaFields(Set<MediaFields> mediaFields) {
        this.mediaFields.addAll(mediaFields);
        return this;
    }

    @Override
    public TwitterStreamClient requestPlaceField(PlaceFields placeField) {
        this.placeFields.add(placeField);
        return this;
    }

    @Override
    public TwitterStreamClient requestPlaceFields(Set<PlaceFields> placeFields) {
        this.placeFields.addAll(placeFields);
        return this;
    }

    @Override
    public TwitterStreamClient requestPollField(PollFields pollField) {
        this.pollFields.add(pollField);
        return this;
    }

    @Override
    public TwitterStreamClient requestPollFields(Set<PollFields> pollFields) {
        this.pollFields.addAll(pollFields);
        return this;
    }

    @Override
    public TwitterStreamClient requestTweetField(TweetFields tweetField) {
        this.tweetFields.add(tweetField);
        return this;
    }

    @Override
    public TwitterStreamClient requestTweetFields(Set<TweetFields> tweetFields) {
        this.tweetFields.addAll(tweetFields);
        return this;
    }

    @Override
    public TwitterStreamClient requestUserField(UserFields userField) {
        this.userFields.add(userField);
        return this;
    }

    @Override
    public TwitterStreamClient requestUserFields(Set<UserFields> userFields) {
        this.userFields.addAll(userFields);
        return this;
    }

    @Override
    public TwitterStreamClient withRule(Rule rule) {
        logger.debug("Adding rule: {}", rule);
        this.rules.add(rule);
        return this;
    }

}
