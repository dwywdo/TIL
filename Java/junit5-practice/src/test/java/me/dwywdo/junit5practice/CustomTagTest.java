package me.dwywdo.junit5practice;

public class CustomTagTest {
    @FastTest
    void fastTest() {
        // Do some fast test
    }
    @SlowTest
    void slowTest() {
        // Do some slow test
    }
}
