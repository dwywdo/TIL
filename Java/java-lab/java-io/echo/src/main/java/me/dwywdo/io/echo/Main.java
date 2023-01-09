package me.dwywdo.io.echo;

import java.io.IOException;

import me.dwywdo.io.echo.server.EchoServer;

public class Main {
    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.start(4444);
    }
}
