package me.dwywdo.labs.java.networking.nio.server.channel;

import me.dwywdo.labs.java.networking.Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioBlockingEchoServer extends Demo {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        logger.info("Echo server is blocking: {}", serverSocketChannel.isBlocking());
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
        logger.info("Echo server started: {}", serverSocketChannel);

        boolean active = true;
        while (active) {
            final SocketChannel socketChannel = serverSocketChannel.accept(); // Blocking
            logger.info("Connection accepted: {}", socketChannel);
            logger.info("Connection is blocking: {}", socketChannel.isBlocking());

            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();

                final int read = socketChannel.read(buffer); // Blocking
                logger.info("Echo server read: {} byte(s)", read);

                if (read < 0) {
                    break;
                }

                buffer.flip();
                final byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                final String message = new String(bytes, StandardCharsets.UTF_8);
                logger.info("Echo server received: {}", message);

                if ("bye".equals(message.trim())) {
                    active = false;
                }

                buffer.flip();
                sleep(60000);
                socketChannel.write(buffer); // Blocking
            }

            socketChannel.close();
            logger.info("Connection closed");
        }

        serverSocketChannel.close();
        logger.info("Echo server finished");
    }
}
