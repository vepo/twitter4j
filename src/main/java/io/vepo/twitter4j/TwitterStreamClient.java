package io.vepo.twitter4j;

import java.util.Set;
import java.util.function.Consumer;

import io.vepo.twitter4j.stream.TweetInfo;

public interface TwitterStreamClient {

    public TwitterClient consume(Consumer<TweetInfo> tweetConsumer);

    public TwitterStreamClient requestExpansion(Expansions expansion);

    public TwitterStreamClient requestExpansions(Set<Expansions> expansions);

    public TwitterStreamClient requestMediaField(MediaFields mediaField);

    public TwitterStreamClient requestMediaFields(Set<MediaFields> mediaFields);

    public TwitterStreamClient requestPlaceField(PlaceFields placeField);

    public TwitterStreamClient requestPlaceFields(Set<PlaceFields> placeFields);

    public TwitterStreamClient requestPollField(PollFields pollField);

    public TwitterStreamClient requestPollFields(Set<PollFields> pollFields);

    public TwitterStreamClient requestTweetField(TweetFields tweetField);

    public TwitterStreamClient requestTweetFields(Set<TweetFields> tweetFields);

    public TwitterStreamClient requestUserField(UserFields userField);

    public TwitterStreamClient requestUserFields(Set<UserFields> userFields);

    public TwitterStreamClient withRule(Rule rule);

}
