package me.dwywdo.labs.java.networking.nio.server.selector;

import me.dwywdo.labs.java.networking.Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioMultiplexingEchoServer extends Demo {
    private static boolean active = true;

    public static void main(String[] args) throws IOException {
        final int ports = 8;
        final ServerSocketChannel[] serverSocketChannels = new ServerSocketChannel[ports];
        final Selector selector = Selector.open();

        for (int p=0; p < ports; p++) {
            final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannels[p] = serverSocketChannel;
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.bind(new InetSocketAddress("localhost", 8080 + p));
            logger.info("Echo server started: {}", serverSocketChannel);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        while (active) {
            final int selected = selector.select();
            logger.info("selected: {} key(s)", selected);
            final Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
            while (keysIterator.hasNext()) {
                final SelectionKey key = keysIterator.next();

                if (key.isAcceptable()) {
                    accept(selector, key);
                }

                if (key.isReadable()) {
                    keysIterator.remove();
                    read(selector, key);
                }

                if (key.isWritable()) {
                    keysIterator.remove();
                    write(key);
                }
            }
        }

        for (ServerSocketChannel serverSocketChannel : serverSocketChannels) {
            serverSocketChannel.close();
        }
        logger.info("Echo server finished");
    }

    private static void accept(Selector selector, SelectionKey key) throws IOException {
        final ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        final SocketChannel socketChannel = serverSocketChannel.accept(); // Can be non-blocking
        if (socketChannel != null) {
            logger.info("Connection is accepted: {}", socketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    private static void read(Selector selector, SelectionKey key) throws IOException {
        final SocketChannel socketChannel = (SocketChannel) key.channel();

        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        final int read = socketChannel.read(buffer);
        logger.info("Echo server read: {} byte(s)", read);

        buffer.flip();
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        final String message = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Echo server received: {}", message);
        if ("bye".equals(message.trim())) {
            active = false;
        }

        buffer.flip();
        socketChannel.register(selector, SelectionKey.OP_WRITE, buffer);
    }

    private static void write(SelectionKey key) throws IOException {
        final SocketChannel socketChannel = (SocketChannel) key.channel();

        final ByteBuffer buffer = (ByteBuffer) key.attachment();

        socketChannel.write(buffer); // Can be non-blocking
        socketChannel.close();

        buffer.flip();
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        final String message = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Echo server sent: {}", message);
    }
}
