package io.vepo.twitter4j;

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