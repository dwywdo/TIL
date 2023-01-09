package me.dwywdo.io.echo.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EchoClientTest {
    private static EchoClient client;

    @BeforeAll
    public static void setup() throws IOException {
        client = new EchoClient();
        client.startConnection("127.0.0.1", 4444);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        String response1 = client.sendMessage("hello");
        String response2 = client.sendMessage("world");
        String response3 = client.sendMessage("!");
        String response4 = client.sendMessage(".");

        assertEquals("hello", response1);
        assertEquals("world", response2);
        assertEquals("!", response3);
        assertEquals("good bye", response4);
    }
}
