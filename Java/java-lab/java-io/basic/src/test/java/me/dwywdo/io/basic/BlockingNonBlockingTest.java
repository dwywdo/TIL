package me.dwywdo.io.basic;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

/**
 * https://www.baeldung.com/java-io-vs-nio
 */
@WireMockTest
public class BlockingNonBlockingTest {
    private static String REQUESTED_RESOURCE = "/test.json";

    @Test
    public void blockingCallTest(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.register(
                stubFor(
                        get(urlEqualTo(REQUESTED_RESOURCE))
                                .willReturn(
                                        aResponse()
                                                .withStatus(200)
                                                .withBody("{\"response\":\"It worked!\"}")
                                )
                )
        );

        Socket socket = new Socket("localhost", wmRuntimeInfo.getHttpPort());

        OutputStream clientOutput = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientOutput));
        writer.println("GET " + REQUESTED_RESOURCE + " HTTP/1.0\r\n\r\n");
        writer.flush();

        InputStream serverInput = socket.getInputStream();
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(serverInput));
        StringBuilder ourStore = new StringBuilder();

        for (String line; (line = bufReader.readLine()) != null; ) {
            ourStore.append(line);
            ourStore.append(System.lineSeparator());
        }
        System.out.println(ourStore);
    }

    @Test
    public void nonBlockingCallTest(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.register(
                stubFor(
                        get(urlEqualTo(REQUESTED_RESOURCE))
                                .willReturn(
                                        aResponse()
                                                .withStatus(200)
                                                .withBody("{\"response\":\"It worked!\"}")
                                )
                )
        );

        InetSocketAddress address = new InetSocketAddress("localhost", wmRuntimeInfo.getHttpPort());
        SocketChannel socketChannel = SocketChannel.open(address);

        Charset charset = StandardCharsets.UTF_8;
        socketChannel.write(charset.encode(CharBuffer.wrap("GET " + REQUESTED_RESOURCE + " HTTP/1.0\r\n\r\n")));

        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(8192);
        StringBuilder ourStore = new StringBuilder();

        while (socketChannel.read(byteBuffer) != -1 || byteBuffer.position() > 0) {
            byteBuffer.flip();
            storeBufferContents(byteBuffer, charBuffer, charsetDecoder, ourStore);
            byteBuffer.compact();
        }

        socketChannel.close();
    }

    private void storeBufferContents(
            ByteBuffer byteBuffer,
            CharBuffer charBuffer,
            CharsetDecoder charsetDecoder,
            StringBuilder ourStore
    ) {
        charsetDecoder.decode(byteBuffer, charBuffer, true);
        charBuffer.flip();
        ourStore.append(charBuffer);
        charBuffer.clear();
    }
}
