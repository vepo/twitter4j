package io.vepo.twitter4j.stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPublicMetrics {
    @JsonProperty("followers_count")
    private int followers_count;
    @JsonProperty("following_count")
    private int following_count;
    @JsonProperty("tweet_count")
    private int tweet_count;
    @JsonProperty("listed_count")
    private int listed_count;

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getTweet_count() {
        return tweet_count;
    }

    public void setTweet_count(int tweet_count) {
        this.tweet_count = tweet_count;
    }

    public int getListed_count() {
        return listed_count;
    }

    public void setListed_count(int listed_count) {
        this.listed_count = listed_count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + followers_count;
        result = prime * result + following_count;
        result = prime * result + listed_count;
        result = prime * result + tweet_count;
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
        UserPublicMetrics other = (UserPublicMetrics) obj;
        if (followers_count != other.followers_count)
            return false;
        if (following_count != other.following_count)
            return false;
        if (listed_count != other.listed_count)
            return false;
        if (tweet_count != other.tweet_count)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserPublicMetrics [followers_count=" + followers_count + ", following_count=" + following_count
                + ", tweet_count=" + tweet_count + ", listed_count=" + listed_count + "]";
    }

}
