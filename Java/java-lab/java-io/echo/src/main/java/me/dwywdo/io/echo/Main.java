package me.dwywdo.io.echo;

import java.io.IOException;

import me.dwywdo.io.echo.server.EchoMultiServer;
import me.dwywdo.io.echo.server.EchoServer;

public class Main {
    public static void main(String[] args) throws IOException {
        EchoMultiServer server = new EchoMultiServer();
        server.start(5555);
    }
}
