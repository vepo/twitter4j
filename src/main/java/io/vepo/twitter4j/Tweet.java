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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((includes == null) ? 0 : includes.hashCode());
        result = prime * result + ((matchingRules == null) ? 0 : matchingRules.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tweet other = (Tweet) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (includes == null) {
            if (other.includes != null)
                return false;
        } else if (!includes.equals(other.includes))
            return false;
        if (matchingRules == null) {
            if (other.matchingRules != null)
                return false;
        } else if (!matchingRules.equals(other.matchingRules))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tweet [data=" + data + ", includes=" + includes + ", matchingRules=" + matchingRules + "]";
    }

}
