package io.vepo.twitter4j.stream;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPublicMetrics {
    @JsonProperty("followers_count")
    private int followersCount;
    @JsonProperty("following_count")
    private int followingCount;
    @JsonProperty("tweetCount")
    private int tweetCount;
    @JsonProperty("listed_count")
    private int listedCount;

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }

    public int getListedCount() {
        return listedCount;
    }

    public void setListedCount(int listedCount) {
        this.listedCount = listedCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(followersCount);
        result = prime * result + Integer.hashCode(followingCount);
        result = prime * result + Integer.hashCode(listedCount);
        result = prime * result + Integer.hashCode(tweetCount);
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
        UserPublicMetrics other = (UserPublicMetrics) obj;
        return followersCount == other.followersCount &&
                followingCount == other.followingCount
                && listedCount == other.listedCount &&
                tweetCount == other.tweetCount;
    }

    @Override
    public String toString() {
        return String.format("UserPublicMetrics [followersCount=%s, followingCount=%s, tweetCount=%s, listedCount=%s]",
                             followersCount, followingCount, tweetCount, listedCount);
    }

}
