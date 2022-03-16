package io.vepo.twitter4j.impl.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReplyOptions(@JsonProperty("in_reply_to_tweet_id") String repliedTweetId) {

}
