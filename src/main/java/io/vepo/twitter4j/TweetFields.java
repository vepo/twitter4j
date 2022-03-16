package io.vepo.twitter4j;

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
}