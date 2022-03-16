package io.vepo.twitter4j;

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