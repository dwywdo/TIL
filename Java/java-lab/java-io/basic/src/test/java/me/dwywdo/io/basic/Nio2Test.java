package me.dwywdo.io.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * https://www.baeldung.com/java-nio-vs-nio-2
 */
public class Nio2Test {
    @Test
    public void readFromFileUsingNIO2() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/test/resources/nio-vs-nio2-multiline.txt"));
        assertEquals(strings.get(0), "Hello from file!");
        assertEquals(strings.get(1), "Hello2 from file!");
    }
}
