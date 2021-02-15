package io.vepo.twitter4j;

import java.util.Set;
import java.util.function.Consumer;

import io.vepo.twitter4j.stream.TweetInfo;

public interface TwitterStreamClient {

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

        public String getQueryValue() {
            return queryValue;
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

        public String getQueryValue() {
            return queryValue;
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

        public String getQueryValue() {
            return queryValue;
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

        public String getQueryValue() {
            return queryValue;
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

        public String getQueryValue() {
            return queryValue;
        }
    };

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

        public String getQueryValue() {
            return queryValue;
        }
    }

    public TwitterClient consume(Consumer<TweetInfo> tweetConsumer);

    public TwitterStreamClient requestExpansion(Expansions expansion);

    public TwitterStreamClient requestExpansions(Set<Expansions> expansions);

    public TwitterStreamClient requestMediaField(MediaFields mediaField);

    public TwitterStreamClient requestMediaFields(Set<MediaFields> mediaFields);

    public TwitterStreamClient requestPlaceField(PlaceFields placeField);

    public TwitterStreamClient requestPlaceFields(Set<PlaceFields> placeFields);

    public TwitterStreamClient requestPollField(PollFields pollField);

    public TwitterStreamClient requestPollFields(Set<PollFields> pollFields);

    public TwitterStreamClient requestTweetField(TweetFields tweetField);

    public TwitterStreamClient requestTweetFields(Set<TweetFields> tweetFields);

    public TwitterStreamClient requestUserField(UserFields userField);

    public TwitterStreamClient requestUserFields(Set<UserFields> userFields);

    public TwitterStreamClient withRule(Rule rule);

}
