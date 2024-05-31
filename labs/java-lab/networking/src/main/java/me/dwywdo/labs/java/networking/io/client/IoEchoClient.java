package me.dwywdo.labs.java.networking.io.client;


import me.dwywdo.labs.java.networking.Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class IoEchoClient extends Demo {

    public static void main(String[] args) throws IOException {
        final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String message;
        while ((message = stdIn.readLine()) != null) {
            final Socket socket = new Socket("localhost", 8080);
            logger.info("Echo client started: {}", socket);

            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();

            final byte[] bytes = message.getBytes();
            outputStream.write(bytes);

            int totalRead = 0;
            while (totalRead < bytes.length) {
                final int read = inputStream.read(bytes, totalRead, bytes.length - totalRead);
                if (read <= 0) {
                    break;
                }

                totalRead += read;
                logger.info("Echo client read: {} byte(s)", read);
            }

            logger.info("Echo client received: {}", new String(bytes, StandardCharsets.UTF_8));

            socket.close();
            logger.info("Echo client disconnected");
        }
    }
}
