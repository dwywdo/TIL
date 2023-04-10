package me.dwywdo.labs.java.completableFuture.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandlingTest {

    public static final Logger log = LoggerFactory.getLogger(ExceptionHandlingTest.class);

    @Test
    public void testHandleMethod() {
        final CompletableFuture<String> completableFuture =
                CompletableFuture.failedFuture(new RuntimeException("OOPS"));
        final CompletableFuture<String> handledCompletableFuture = completableFuture.handle(
                (str, ex) -> {
                    if (ex != null) {
                        return "Recovered from \"" + ex.getMessage() + '"';
                    } else {
                        return str;
                    }
                }
        );
        log.info(handledCompletableFuture.join());
    }

    @Test
    public void testWhenCompleteMethod() {
        final CompletableFuture<String> completableFuture =
                CompletableFuture.failedFuture(new RuntimeException("OOPS"));
        final CompletableFuture<String> handledCompletableFuture = completableFuture.whenComplete(
                (str, ex) -> {
                    if (ex != null) {
                        log.warn("Exception Occurred: " + ex.getMessage());
                    } else {
                        log.info(str);
                    }
                }
        );

        try {
            handledCompletableFuture.join();
        } catch (CompletionException ce) {
            log.error("Error: " + ce.getMessage());
        }
    }

    @Test
    public void testExceptionallyMethod() {
        final CompletableFuture<String> completableFuture =
                CompletableFuture.failedFuture(new RuntimeException("OOPS"));

        final CompletableFuture<String> handledCompletableFuture =
                completableFuture.exceptionally(
                        ex -> "Recovered from \"" + ex.getMessage() + '\"'
                );

        log.info(handledCompletableFuture.join());
    }
}
