package io.vepo.twitter4j.stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetPublicMetrics {
    @JsonProperty("retweet_count")
    private int retweetCount;
    @JsonProperty("reply_count")
    private int replyCount;
    @JsonProperty("like_count")
    private int likeCount;
    @JsonProperty("quote_count")
    private int quoteCount;

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount) {
        this.quoteCount = quoteCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(likeCount);
        result = prime * result + Integer.hashCode(quoteCount);
        result = prime * result + Integer.hashCode(replyCount);
        result = prime * result + Integer.hashCode(retweetCount);
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
        TweetPublicMetrics other = (TweetPublicMetrics) obj;
        return likeCount == other.likeCount &&
                quoteCount == other.quoteCount &&
                replyCount == other.replyCount &&
                retweetCount == other.retweetCount;
    }

    @Override
    public String toString() {
        return String.format("TweetPublicMetrics [retweetCount=%s, replyCount=%s, likeCount=%s, quoteCount=%s]",
                             retweetCount, replyCount, likeCount, quoteCount);
    }

}
