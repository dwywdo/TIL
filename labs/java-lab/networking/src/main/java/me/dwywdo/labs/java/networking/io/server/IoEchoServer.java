package me.dwywdo.labs.java.networking.io.server;

import me.dwywdo.labs.java.networking.Demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class IoEchoServer extends Demo {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8080);
        boolean active = true;

        logger.info("Echo server started: {}", serverSocket);

        while (active) {
            final Socket socket = serverSocket.accept(); // Blocking
            logger.info("Echo client connected: {}", socket);
            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();

            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) { // Blocking
                logger.info("Echo server read: {} byte(s)", read);
                logger.info("Echo server received: {}", new String(bytes, StandardCharsets.UTF_8));
                sleep(60000);
                outputStream.write(bytes, 0, read); // Blocking
            }
            socket.close();
            logger.info("Echo client disconnected");
        }

        serverSocket.close();
        logger.info("Echo server diconnected");
    }
}



