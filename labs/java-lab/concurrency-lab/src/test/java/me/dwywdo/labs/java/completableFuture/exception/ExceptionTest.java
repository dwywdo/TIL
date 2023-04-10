package me.dwywdo.labs.java.completableFuture.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {
    public static final Logger log = LoggerFactory.getLogger(ExceptionTest.class);
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

    @Test
    public void testCompletionException() throws InterruptedException {
        executorService.submit(() -> {
           completableFuture.completeExceptionally(new Exception("Something bad happened"));
        });

        Thread.sleep(500);

        try {
            completableFuture.join();
        } catch (CompletionException ce) {
            log.warn("CompletionException has been thrown. cause: [" + ce.getCause() + ']');
        } finally {
            executorService.shutdown();
        }
    }

    @Test
    public void testExecutionException() throws InterruptedException {
        executorService.submit(() -> {
            completableFuture.completeExceptionally(new Exception("Something bad happened"));
        });

        Thread.sleep(500);

        try {
            completableFuture.get();
        } catch (CompletionException ce) {
            log.warn("CompletionException has been thrown. cause: [" + ce.getCause() + ']');
        } catch (ExecutionException ee) {
            log.warn("ExecutionException has been thrown. cause: [" + ee.getCause() + ']');
        } finally {
            executorService.shutdown();
        }
    }
}
