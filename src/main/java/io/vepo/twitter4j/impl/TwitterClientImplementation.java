package io.vepo.twitter4j.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.vepo.twitter4j.TweetData;
import io.vepo.twitter4j.TwitterClient;
import io.vepo.twitter4j.TwitterClientException;
import io.vepo.twitter4j.TwitterClientException.CauseType;
import io.vepo.twitter4j.TwitterStreamClient;
import io.vepo.twitter4j.impl.api.CreateTweetRequest;
import io.vepo.twitter4j.impl.api.CreateTweetResponse;
import io.vepo.twitter4j.impl.api.ReplyOptions;
import io.vepo.twitter4j.impl.api.TwitterOAuth2Token;

public class TwitterClientImplementation implements TwitterClient {
    private static final Logger logger = LoggerFactory.getLogger(TwitterClientImplementation.class);

    static String TWITTER_AUTH_ENDPOINT = "https://api.twitter.com/oauth2/token";
    static String TWITTER_MANAGE_TWEET_ENDPOINT = "https://api.twitter.com/2/tweets";

    private String apiKey;
    private String apiKeySecret;

    private Context context;

    public TwitterClientImplementation(String apiKey, String apiKeySecret) {
        this.apiKey = apiKey;
        this.apiKeySecret = apiKeySecret;
        this.context = new Context(this);
    }

    @Override
    public TwitterClient authenticate() {
        try {
            logger.trace("Starting authentication...");
            var authRequest = HttpRequest.newBuilder()
                                         .uri(URI.create(TWITTER_AUTH_ENDPOINT))
                                         .POST(BodyPublishers.ofString("grant_type=client_credentials&scope=tweet.write&client_id=bnJZa2dlZHBiMlQyYUpuY3RubUE6MTpjaQ"))
                                         .header("Authorization", generateAutorizationHeaderForNonAuthenticated())
                                         .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                         .build();
            var response = context.getStringResponse(authRequest);
            logger.info("Authenticate response={}", response);
            switch (response.statusCode()) {
                case 200 -> this.context.oauth2Token = context.readValue(response.body(), TwitterOAuth2Token.class);
                case 403 -> throw new TwitterClientException(CauseType.INVALID_CREDENTIALS);
                case 500 -> throw new TwitterClientException(CauseType.SERVER_ERROR);
                default -> throw new IllegalArgumentException("Unexpected value: " + response.statusCode());
            }
            logger.info("Authenticated!");
        } catch (JsonProcessingException e) {
            throw new TwitterClientException(CauseType.INVALID_RESPONSE, e);
        } catch (IOException e) {
            throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
        } catch (InterruptedException e) {
            // Finishing
            Thread.currentThread().interrupt();
        }
        return this;
    }

    private String generateAutorizationHeaderForNonAuthenticated() {
        return "Basic " + Base64.getEncoder().encodeToString(((apiKey + ":" + apiKeySecret).getBytes()));
    }

    @Override
    public boolean isAutheticated() {
        return Objects.nonNull(this.context.oauth2Token);
    }

    @Override
    public void join() {
        context.join();
        logger.info("Clients finished!");
    }

    @Override
    public TwitterClient reply(String tweetId, String text, Consumer<TweetData> callback) {
        try {
            /**
             * curl \
             * -X POST \
             * -H "Content-type: application/json" \
             * -d "{\"text\":\"just setting up my #TwitterAPI\"}" \
             * -H "Authorization: Bearer
             * SFphTGxRVFJNZU1GVzJFNld3dnhVd19leHA2dGJJVnR1dUFXUWktVS1lVm9NOjE2NDczOTUzMzAwNzE6MToxOmF0OjE"
             * \
             * "https://api.twitter.com/2/tweets"
             */
            logger.trace("Sending reply...");
            var replyRequest = HttpRequest.newBuilder()
                                          .uri(URI.create(TWITTER_MANAGE_TWEET_ENDPOINT))
                                          .POST(BodyPublishers.ofString(context.writeValueAsString(new CreateTweetRequest(text, new ReplyOptions(tweetId)))))
                                          .header("Authorization", context.generateAutorizationHeader())
                                          .header("Content-Type", "application/json")
                                          .build();
            var response = context.getStringResponse(replyRequest);
            logger.info("Reply response={} body={}", response, response.body());
            switch (response.statusCode()) {
                case 201 -> callback.accept(context.readValue(response.body(), CreateTweetResponse.class).data());
                case 403 -> throw new TwitterClientException(CauseType.INVALID_CREDENTIALS);
                case 500 -> throw new TwitterClientException(CauseType.SERVER_ERROR);
                default -> throw new IllegalArgumentException("Unexpected value: " + response.statusCode());
            }
            logger.info("Authenticated!");
        } catch (JsonProcessingException e) {
            throw new TwitterClientException(CauseType.INVALID_RESPONSE, e);
        } catch (IOException e) {
            throw new TwitterClientException(CauseType.IO_EXCEPTION, e);
        } catch (InterruptedException e) {
            // Finishing
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Override
    public TwitterStreamClient stream() {
        if (isAutheticated()) {
            return new TwitterStreamClientImplementation(this.context);
        } else {
            throw new IllegalStateException("Not Authenticated! Call authenticate() before creating Stream Client!");
        }
    }
}
