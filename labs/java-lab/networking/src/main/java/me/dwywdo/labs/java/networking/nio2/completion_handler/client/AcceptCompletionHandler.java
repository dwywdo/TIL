package me.dwywdo.labs.java.networking.nio2.completion_handler.client;

import me.dwywdo.labs.java.networking.Demo;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

class AcceptCompletionHandler extends Demo implements CompletionHandler<Void, Attachment> {

    private final AsynchronousSocketChannel socketChannel;

    AcceptCompletionHandler(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Void result, Attachment attachment) {
        logger.info("Connection accepted: {}", socketChannel);

        final String message = attachment.getMessage();
        logger.info("Echo client sent: {}", message);

        final ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        final WriteCompletionHandler writeCompletionHandler = new WriteCompletionHandler(socketChannel);
        socketChannel.write(buffer, attachment, writeCompletionHandler);
    }

    @Override
    public void failed(Throwable t, Attachment attachment) {
        logger.error("Exception during connection accepting", t);
    }
}
