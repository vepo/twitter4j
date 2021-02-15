package io.vepo.twitter4j.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Base64;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.vepo.twitter4j.TwitterClient;
import io.vepo.twitter4j.TwitterClientException;
import io.vepo.twitter4j.TwitterClientException.CauseType;
import io.vepo.twitter4j.TwitterStreamClient;
import io.vepo.twitter4j.impl.api.TwitterOAuth2Token;

public class TwitterClientImplementation implements TwitterClient {
    private static final Logger logger = LoggerFactory.getLogger(TwitterClientImplementation.class);

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
                                         .uri(URI.create("https://api.twitter.com/oauth2/token"))
                                         .POST(BodyPublishers.ofString("grant_type=client_credentials"))
                                         .header("Authorization", generateAutorizationHeaderForNonAuthenticated())
                                         .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                         .build();
            var response = context.getStringResponse(authRequest);
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
    public TwitterStreamClient stream() {
        if (isAutheticated()) {
            return new TwitterStreamClientImplementation(this.context);
        } else {
            throw new IllegalStateException("Not Authenticated! Call authenticate() before creating Stream Client!");
        }
    }
}
