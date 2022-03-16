package io.vepo.twitter4j;

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
}