package me.dwywdo.labs.java.networking.io.server;

import me.dwywdo.labs.java.networking.Demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class IoEchoThreadPoolServer extends Demo {
    private static final AtomicBoolean active = new AtomicBoolean(true);

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8080);
        logger.info("Echo server started: {}", serverSocket);

        final ExecutorService executorService = Executors.newCachedThreadPool();

        while (active.get()) {
            final Socket socket = serverSocket.accept(); // Blocking
            executorService.submit(new Worker(socket));
        }

        logger.info("Echo server is finishing");
        executorService.shutdown();
        while (!executorService.isTerminated()) { }

        serverSocket.close();
        logger.info("Echo server finished");
    }

    private static class Worker implements Runnable {
        private final Socket socket;

        Worker(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try {
                logger.info("Connection accepted: {}", socket);
                final InputStream inputStream = socket.getInputStream();
                final OutputStream outputStream = socket.getOutputStream();

                int read;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) { // Blocking
                    logger.info("Echo server read: {} byte(s)", read);
                    final String message = new String(bytes, 0, read, StandardCharsets.UTF_8);
                    logger.info("Echo server received: {}", message);
                    if ("bye".equals(message.trim())) {
                        active.set(false);
                    }
                    sleep(60000);
                    outputStream.write(bytes, 0, read); // Blocking
                }
            } catch (IOException e) {
                logger.error("Exception during socket read / write", e);
            } finally {
                try {
                    socket.close();
                    logger.info("Connection closed");
                } catch (IOException e) {
                    logger.error("Exception during socket close", e);
                }
            }
        }
    }
}
