package completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

class ShopTest {
    @Test
    void useAsyncApi() {
        Shop shop = new Shop("AsyncShop");

        long start = System.nanoTime();

        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");

        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    @Test
    void useSyncApi() {
        Shop shop = new Shop("SyncShop");

        long start = System.nanoTime();

        double price = shop.getPrice("my favorite product");

        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        System.out.printf("Price is %.2f%n", price);

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    @Test
    void useHandyAsyncApi() {
        Shop shop = new Shop("AsyncShop");

        long start = System.nanoTime();

        Future<Double> futurePrice = shop.handyGetPriceAsync("my favorite product");

        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }

    private static void doSomethingElse() {
        System.out.println("I'm doing something else..!!");
    }
}
