package io.vepo.twitter4j.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vepo.twitter4j.TwitterClient;
import io.vepo.twitter4j.impl.api.TwitterOAuth2Token;

class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    private HttpClient httpClient;
    TwitterClient main;
    TwitterOAuth2Token oauth2Token;
    private ObjectMapper objectMapper;
    private List<ExecutorService> executors;

    Context(TwitterClient main) {
        this.main = main;
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
        executors = new ArrayList<>();
    }

    String generateAutorizationHeader() {
        String header;
        switch (this.oauth2Token.getTokenType()) {
            case "bearer" -> header = "Bearer " + this.oauth2Token.getAccessToken();
            default -> throw new IllegalArgumentException("Unexpected value: " + this.oauth2Token.getAccessToken());
        }

        return header;
    }

    HttpResponse<String> getStringResponse(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, BodyHandlers.ofString());
    }

    HttpResponse<InputStream> getInputStreamResponse(HttpRequest request)
            throws IOException, InterruptedException {
        return httpClient.send(request, BodyHandlers.ofInputStream());
    }

    <T> T readValue(String content, Class<T> contentClass) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(content, contentClass);
    }

    <T> String writeValueAsString(T value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

    void join() {
        AtomicBoolean running = new AtomicBoolean(true);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Shutdown signal received...");
                executors.forEach(ExecutorService::shutdown);
                logger.info("Clients shutdown required...");
                executors.forEach(executor -> {
                    try {
                        executor.awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                running.set(false);
                logger.info("Shutdown hookup finished!");
            }
        });
        while (executors.stream().allMatch(exec -> {
            try {
                return !exec.awaitTermination(10, TimeUnit.SECONDS) && !exec.isShutdown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return true;
            }
        })) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

    }

}
