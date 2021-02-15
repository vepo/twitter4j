package io.vepo.twitter4j;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    /**
     * <p>
     * Expansions enable you to request additional data objects that relate to the
     * originally returned Tweets. Submit a list of desired expansions in a
     * comma-separated list without spaces. The ID that represents the expanded data
     * object will be included directly in the Tweet data object, but the expanded
     * object metadata will be returned within the includes response object, and
     * will also include the ID so that you can match this data object to the
     * original Tweet object.
     * </p>
     * 
     * <p>
     * The following data objects can be expanded using this parameter:
     * </p>
     * 
     * <ul>
     * <li>The Tweet author's user object</li>
     * <li>The user object of the Tweet’s author that the original Tweet is
     * responding to</li>
     * <li>Any mentioned users’ object</li>
     * <li>Any referenced Tweets’ author’s user object</li>
     * <li>Attached media’s object</li>
     * <li>Attached poll’s object</li>
     * <li>Attached place’s object</li>
     * <li>Any referenced Tweets’ object.</li>
     * </ul>
     * 
     */
    public enum Expansions {
        ATTACHMENTS_POLL_IDS("attachments.poll_ids"),
        ATTACHMENTS_MEDIA_KEYS("attachments.media_keys"),
        AUTHOR_ID("author_id"),
        ENTITIES_MENTIONS_USERNAME("entities.mentions.username"),
        GEO_PLACE_ID("geo.place_id"),
        IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
        REFERENCED_TWEETS_ID("referenced_tweets.id"),
        REFERENCED_TWEETS_ID_AUTHOR_ID("referenced_tweets.id.author_id");

        private String queryValue;

        Expansions(String queryValue) {
            this.queryValue = queryValue;
        }
    }

    /**
     * <p>
     * This fields parameter enables you to select which specific media fields will
     * deliver in each returned Tweet. Specify the desired fields in a
     * comma-separated list without spaces between commas and fields. The Tweet will
     * only return media fields if the Tweet contains media and if you've also
     * included the expansions=attachments.media_keys query parameter in your
     * request. While the media ID will be located in the Tweet object, you will
     * find this ID and all additional media fields in the includes data object.
     * </p>
     */
    public enum MediaFields {
        DURATION_MS("duration_ms"),
        HEIGHT("height"),
        MEDIA_KEY("media_key"),
        PREVIEW_IMAGE_URL("preview_image_url"),
        TYPE("type"),
        URL("url"),
        WIDTH("width"),
        PUBLIC_METRICS("public_metrics");

        private String queryValue;

        MediaFields(String queryValue) {
            this.queryValue = queryValue;
        }
    }

    /**
     * <p>
     * This fields parameter enables you to select which specific place fields will
     * deliver in each returned Tweet. Specify the desired fields in a
     * comma-separated list without spaces between commas and fields. The Tweet will
     * only return place fields if the Tweet contains a place and if you've also
     * included the expansions=geo.place_id query parameter in your request. While
     * the place ID will be located in the Tweet object, you will find this ID and
     * all additional place fields in the includes data object.
     * </p>
     */
    public enum PlaceFields {
        CONTAINED_WITHIN("contained_within"),
        COUNTRY("country"),
        COUNTRY_CODE("country_code"),
        FULL_NAME("full_name"),
        GEO("geo"),
        ID("id"),
        NAME("name"),
        PLACE_TYPE("place_type");

        private String queryValue;

        PlaceFields(String queryValue) {
            this.queryValue = queryValue;
        }
    }

    public enum PollFields {
        DURATION_MINUTES("duration_minutes"),
        END_DATETIME("end_datetime"),
        ID("id"),
        OPTIONS("options"),
        VOTING_STATUS("voting_status");

        private String queryValue;

        PollFields(String queryValue) {
            this.queryValue = queryValue;
        }
    };

    /**
     * <p>
     * This fields parameter enables you to select which specific Tweet fields will
     * deliver in each returned Tweet object. Specify the desired fields in a
     * comma-separated list without spaces between commas and fields. You can also
     * pass the expansions=referenced_tweets.id expansion to return the specified
     * fields for both the original Tweet and any included referenced Tweets. The
     * requested Tweet fields will display in both the original Tweet data object,
     * as well as in the referenced Tweet expanded data object that will be located
     * in the includes data object.
     * </p>
     */
    public enum TweetFields {
        ATTACHMENTS("attachments"),
        AUTHOR_ID("author_id"),
        CONTEXT_ANNOTATIONS("context_annotations"),
        CONVERSATION_ID("conversation_id"),
        CREATED_AT("created_at"),
        ENTITIES("entities"),
        GEO("geo"),
        ID("id"),
        IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
        LANG("lang"),
        PUBLIC_METRICS("public_metrics"),
        POSSIBLY_SENSITIVE("possibly_sensitive"),
        REFERENCED_TWEETS("referenced_tweets"),
        REPLY_SETTINGS("reply_settings"),
        SOURCE("source"),
        TEXT("text"),
        WITHHELD("withheld");

        private String queryValue;

        TweetFields(String queryValue) {
            this.queryValue = queryValue;
        }
    };

    public class TwitterStreamClient {

        private static final String QS_POLL_FIELDS = "poll.fields";
        private static final String QS_PLACE_FIELDS = "place.fields";
        private static final String QS_MEDIA_FIELDS = "media.fields";
        private static final String QS_USER_FIELDS = "user.fields";
        private static final String QS_EXPANSIONS = "expansions";
        private static final String QS_TWEET_FIELDS = "tweet.fields";
        private static final String TWITTER_ENDPOINT_STREAM = "https://api.twitter.com/2/tweets/search/stream";
        private static final String TWITTER_ENDPOINT_STREAM_RULES =
                "https://api.twitter.com/2/tweets/search/stream/rules";
        private final List<Rule> rules;
        private final ExecutorService executor;
        private final Set<TweetFields> tweetFields;
        private final Set<Expansions> expansions;
        private final Set<UserFields> userFields;
        private final Set<MediaFields> mediaFields;
        private final Set<PlaceFields> placeFields;
        private final Set<PollFields> pollFields;

        private TwitterStreamClient() {
            this.rules = new ArrayList<>();
            executors.add(this.executor = Executors.newSingleThreadExecutor());
            tweetFields = new HashSet<TweetFields>();
            expansions = new HashSet<Expansions>();
            userFields = new HashSet<UserFields>();
            mediaFields = new HashSet<MediaFields>();
            placeFields = new HashSet<PlaceFields>();
            pollFields = new HashSet<PollFields>();
        }

        private String buildStreamParameters() {
            var params = new ArrayList<String>();
            if (this.tweetFields.size() > 0) {
                params.add(QS_TWEET_FIELDS + "=" + this.tweetFields.stream()
                                                                   .map(field -> field.queryValue)
                                                                   .collect(joining(",")));
            }

            if (this.expansions.size() > 0) {
                params.add(QS_EXPANSIONS + "=" + this.expansions.stream()
                                                                .map(field -> field.queryValue)
                                                                .collect(joining(",")));
            }

            if (this.userFields.size() > 0) {
                params.add(QS_USER_FIELDS + "=" + this.userFields.stream()
                                                                 .map(field -> field.queryValue)
                                                                 .collect(joining(",")));
            }

            if (this.mediaFields.size() > 0) {
                params.add(QS_MEDIA_FIELDS + "=" + this.mediaFields.stream()
                                                                   .map(field -> field.queryValue)
                                                                   .collect(joining(",")));
            }

            if (this.placeFields.size() > 0) {
                params.add(QS_PLACE_FIELDS + "=" + this.placeFields.stream()
                                                                   .map(field -> field.queryValue)
                                                                   .collect(joining(",")));
            }

            if (this.pollFields.size() > 0) {
                params.add(QS_POLL_FIELDS + "=" + this.pollFields.stream()
                                                                 .map(field -> field.queryValue)
                                                                 .collect(joining(",")));
            }

            return params.size() > 0 ? ("?" + params.stream().collect(joining("&"))) : "";
        }

        public TwitterClient consume(Consumer<TweetInfo> tweetConsumer) {
            executor.submit(this::removeOldrules);
            executor.submit(this::createRules);
            executor.submit(() -> consumeStream(tweetConsumer));
            return TwitterClient.this;
        }

        private void consumeStream(Consumer<TweetInfo> tweetConsumer) {
            try {
                logger.trace("Starting consumer...");
                var request = HttpRequest.newBuilder()
                                         .uri(URI.create(TWITTER_ENDPOINT_STREAM + buildStreamParameters()))
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
                                logger.info("Processando line: {}", line);
                                tweetConsumer.accept(objectMapper.readValue(line, TweetInfo.class));
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

        private void deleteRules(List<String> ids) {
            if (!ids.isEmpty()) {
                logger.trace("Deleting all rules...");
                try {
                    var request = HttpRequest.newBuilder()
                                             .uri(URI.create(TWITTER_ENDPOINT_STREAM_RULES))
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

        public TwitterStreamClient requestExpansion(Expansions expansion) {
            this.expansions.add(expansion);
            return this;
        }

        public TwitterStreamClient requestExpansions(Set<Expansions> expansions) {
            this.expansions.addAll(expansions);
            return this;
        }

        public TwitterStreamClient requestMediaField(MediaFields mediaField) {
            this.mediaFields.add(mediaField);
            return this;
        }
        
        public TwitterStreamClient requestMediaFields(Set<MediaFields> mediaFields) {
            this.mediaFields.addAll(mediaFields);
            return this;
        }

        public TwitterStreamClient requestPlaceField(PlaceFields placeField) {
            this.placeFields.add(placeField);
            return this;
        }

        public TwitterStreamClient requestPlaceFields(Set<PlaceFields> placeFields) {
            this.placeFields.addAll(placeFields);
            return this;
        }

        public TwitterStreamClient requestPollField(PollFields pollField) {
            this.pollFields.add(pollField);
            return this;
        }

        public TwitterStreamClient requestPollFields(Set<PollFields> pollFields) {
            this.pollFields.addAll(pollFields);
            return this;
        }

        public TwitterStreamClient requestTweetField(TweetFields tweetField) {
            this.tweetFields.add(tweetField);
            return this;
        }

        public TwitterStreamClient requestTweetFields(Set<TweetFields> tweetFields) {
            this.tweetFields.addAll(tweetFields);
            return this;
        }

        public TwitterStreamClient requestUserField(UserFields userField) {
            this.userFields.add(userField);
            return this;
        }

        public TwitterStreamClient requestUserFields(Set<UserFields> userFields) {
            this.userFields.addAll(userFields);
            return this;
        }

        public TwitterStreamClient withRule(Rule rule) {
            logger.debug("Adding rule: {}", rule);
            this.rules.add(rule);
            return this;
        }

    }

    /**
     * <p>
     * This fields parameter enables you to select which specific user fields will
     * deliver in each returned Tweet. Specify the desired fields in a
     * comma-separated list without spaces between commas and fields. While the user
     * ID will be located in the original Tweet object, you will find this ID and
     * all additional user fields in the includes data object.
     * </p>
     * 
     * <p>
     * You must also pass one of the user expansions to return the desired user
     * fields:
     * </p>
     * 
     * <ul>
     * <li>expansions=author_id</li>
     * <li>expansions=entities.mentions.username</li>
     * <li>expansions=in_reply_to_user_id</li>
     * <li>expansions=referenced_tweets.id.author_id</li>
     * </ul>
     *
     */
    public enum UserFields {
        CREATED_AT("created_at"),
        DESCRIPTION("description"),
        ENTITIES("entities"), ID("id"),
        LOCATION("location"),
        NAME("name"),
        PINNED_TWEET_ID("pinned_tweet_id"),
        PROFILE_IMAGE_URL("profile_image_url"),
        PROTECTED("protected"),
        PUBLIC_METRICS("public_metrics"),
        URL("url"),
        USERNAME("username"),
        VERIFIED("verified"),
        WITHHELD("withheld");

        private String queryValue;

        UserFields(String queryValue) {
            this.queryValue = queryValue;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(TwitterClient.class);

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

    private String generateAutorizationHeader() {
        String header;
        switch (this.oauth2Token.getTokenType()) {
            case "bearer" -> header = "Bearer " + this.oauth2Token.getAccessToken();
            default -> throw new IllegalArgumentException("Unexpected value: " + this.oauth2Token.getAccessToken());
        }

        return header;
    }

    private String generateAutorizationHeaderForNonAuthenticated() {
        return "Basic " + Base64.getEncoder().encodeToString(((apiKey + ":" + apiKeySecret).getBytes()));
    }

    public boolean isAutheticated() {
        return Objects.nonNull(this.oauth2Token);
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

    public TwitterStreamClient stream() {
        if (isAutheticated()) {
            return new TwitterStreamClient();
        } else {
            throw new IllegalStateException("Not Authenticated! Call authenticate() before creating Stream Client!");
        }
    }

}
