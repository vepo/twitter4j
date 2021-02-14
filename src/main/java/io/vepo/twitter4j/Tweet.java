package io.vepo.twitter4j;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    private TweetData data;
    private TweetIncludeData includes;
    @JsonProperty("matching_rules")
    private List<TweetMatchingRule> matchingRules;

    public TweetData getData() {
        return data;
    }

    public void setData(TweetData data) {
        this.data = data;
    }

    public TweetIncludeData getIncludes() {
        return includes;
    }

    public void setIncludes(TweetIncludeData includes) {
        this.includes = includes;
    }

    public List<TweetMatchingRule> getMatchingRules() {
        return matchingRules;
    }

    public void setMatchingRules(List<TweetMatchingRule> matchingRules) {
        this.matchingRules = matchingRules;
    }

    @Override
    public String toString() {
        return "Tweet [data=" + data + ", includes=" + includes + ", matchingRules=" + matchingRules + "]";
    }

}
