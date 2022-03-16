package io.vepo.twitter4j;

import java.util.function.Consumer;

import io.vepo.twitter4j.impl.TwitterClientImplementation;

public interface TwitterClient {

    public static TwitterClient newClient(String apiKey, String apiKeySecret) {
        return new TwitterClientImplementation(apiKey, apiKeySecret);
    }

    public abstract TwitterClient authenticate();

    public abstract boolean isAutheticated();

    public abstract void join();

    public abstract TwitterStreamClient stream();

    public abstract TwitterClient reply(String tweetId, String text, Consumer<TweetData> callback);
}
