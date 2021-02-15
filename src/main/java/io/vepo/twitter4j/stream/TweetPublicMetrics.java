package io.vepo.twitter4j.stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetPublicMetrics {
    @JsonProperty("retweet_count")
    private int retweetCount;
    @JsonProperty("reply_count")
    private int replyCount;
    @JsonProperty("like_count")
    private int like_count;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
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
        result = prime * result + like_count;
        result = prime * result + quoteCount;
        result = prime * result + replyCount;
        result = prime * result + retweetCount;
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
        TweetPublicMetrics other = (TweetPublicMetrics) obj;
        if (like_count != other.like_count)
            return false;
        if (quoteCount != other.quoteCount)
            return false;
        if (replyCount != other.replyCount)
            return false;
        if (retweetCount != other.retweetCount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TweetPublicMetrics [retweetCount=" + retweetCount + ", replyCount=" + replyCount + ", like_count="
                + like_count + ", quoteCount=" + quoteCount + "]";
    }

}
