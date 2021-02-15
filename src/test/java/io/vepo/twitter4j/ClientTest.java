package io.vepo.twitter4j;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {
    8787,
    8888 })
@Disabled
class ClientTest {
    private final ClientAndServer client;

    ClientTest(ClientAndServer client) {
        this.client = client;
    }

    @Test
    void testSomething() {
        // ...
    }
}
