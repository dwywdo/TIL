package me.dwywdo.io.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EchoClientWithSelectorTest {
    private Process server;
    private EchoClientWithSelector client;

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        server = EchoServerWithSelector.start();
        client = EchoClientWithSelector.start();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() {
        String response1 = client.sendMessage("hello");
        String response2 = client.sendMessage("world");
        assertEquals("hello", response1);
        assertEquals("world", response2);
    }

    @AfterEach
    public void tearDown() throws IOException {
        EchoClientWithSelector.stop();
        server.destroy();
    }
}
