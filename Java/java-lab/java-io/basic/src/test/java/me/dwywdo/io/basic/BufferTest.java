package me.dwywdo.io.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.jupiter.api.Test;

/**
 * https://www.baeldung.com/java-nio-vs-nio-2
 */
public class BufferTest {
    @Test
    public void readFromFileUsingFileChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/test/resources/nio-vs-nio2-oneline.txt", "r");
        FileChannel channel = file.getChannel();
        StringBuilder content = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = channel.read(buffer);

        while(bytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                content.append((char) buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        file.close();
        assertEquals(content.toString(), "Hello from file!");
    }
}
