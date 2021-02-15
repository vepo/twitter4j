package io.vepo.twitter4j.stream;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetInfo {
    private TweetInfoData data;
    private TweetInfoIncludes includes;
    @JsonProperty("matching_rules")
    private List<TweetMatchingRule> matchingRules;

    public TweetInfoData getData() {
        return data;
    }

    public void setData(TweetInfoData data) {
        this.data = data;
    }

    public TweetInfoIncludes getIncludes() {
        return includes;
    }

    public void setIncludes(TweetInfoIncludes includes) {
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
        result = prime * result + Objects.hashCode(data);
        result = prime * result + Objects.hashCode(includes);
        result = prime * result + Objects.hashCode(matchingRules);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TweetInfo other = (TweetInfo) obj;
        return Objects.equals(data, other.data) &&
                Objects.equals(includes, other.includes) &&
                Objects.equals(matchingRules, other.matchingRules);
    }

    @Override
    public String toString() {
        return String.format("TweetInfo [data=%s, includes=%s, matchingRules=%s]", data, includes, matchingRules);
    }

}
