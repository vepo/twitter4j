package io.vepo.twitter4j;

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