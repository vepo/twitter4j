package io.vepo.twitter4j.impl;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;

import io.vepo.twitter4j.TwitterClient;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {
    8787,
    8888 })
class ClientTest {
    private final ClientAndServer client;

    ClientTest(ClientAndServer client) {

        this.client = client;
        TwitterClientImplementation.TWITTER_AUTH_ENDPOINT = "http://localhost:" + client.getPort() + "/oauth2/token";
    }

    @Test
    void testSomething() {
        client.when(request("/oauth2/token").withMethod("POST"))
              .respond(response().withBody("{\"token_type\": \"\", \"access_token\": \"\"}"));

        TwitterClient.newClient("XXX", "XXX")
                     .authenticate();
    }
}
