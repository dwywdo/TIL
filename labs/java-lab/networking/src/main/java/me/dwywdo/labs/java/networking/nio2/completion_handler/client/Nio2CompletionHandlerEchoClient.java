package me.dwywdo.labs.java.networking.nio2.completion_handler.client;

import me.dwywdo.labs.java.networking.Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousSocketChannel;

public class Nio2CompletionHandlerEchoClient extends Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String message;
        while ((message = stdIn.readLine()) != null) {
            final AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

            socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
            socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);

            final Attachment attachment = new Attachment(message, true);
            final AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(socketChannel);
            socketChannel.connect(new InetSocketAddress("localhost", 8080), attachment, acceptCompletionHandler);

            while (attachment.getActive().get()) {

            }

            socketChannel.close();
            logger.info("Echo client finished");
        }
    }
}
