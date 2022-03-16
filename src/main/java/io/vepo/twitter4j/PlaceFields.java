package io.vepo.twitter4j;

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