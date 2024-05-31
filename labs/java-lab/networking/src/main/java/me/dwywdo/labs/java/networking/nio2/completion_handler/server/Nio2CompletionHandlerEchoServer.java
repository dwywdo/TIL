package me.dwywdo.labs.java.networking.nio2.completion_handler.server;

import me.dwywdo.labs.java.networking.Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class Nio2CompletionHandlerEchoServer extends Demo {

    public static void main(String[] args) throws IOException {
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(7000));
        logger.info("Echo server started");

        final AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(serverSocketChannel);
        serverSocketChannel.accept(null, acceptCompletionHandler);

        System.in.read();
        logger.info("Echo server finished");
    }
}
