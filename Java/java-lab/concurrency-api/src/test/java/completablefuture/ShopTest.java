package completablefuture;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ShopTest {
    @Test
    void checkThreadPoolSize() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

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

    @Test
    void useSyncApiForMultipleShop() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopWithParallelStream() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
        long start = System.nanoTime();
        System.out.println(findPricesWithParallelStream("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopWithAsyncRequests() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("NewShop"),
                new Shop("PowerShop"),
                new Shop("RunnerShop"),
                new Shop("TwitchShop"),
                new Shop("MachineShop"),
                new Shop("PowerShop"),
                new Shop("RunnerShop"),
                new Shop("TwitchShop")
        );
        long start = System.nanoTime();
        System.out.println(findPricesWithAsyncRequests("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopWithAsyncRequestsOnSinglePipeline() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
        long start = System.nanoTime();
        System.out.println(findPricesWithAsyncRequestOnSinglePipeline("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopWithAsyncRequestsOnCustomExectuor() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );

        long start = System.nanoTime();
        System.out.println(findPricesWithAsyncRequestsOnCustomExecutor("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopAndApplyDiscount() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("DontWaitJustBuy")
        );

        long start = System.nanoTime();
        System.out.println(findDiscountedPrices("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    void useSyncApiForMultipleShopAndApplyDiscountWithFutures() {
        List<Shop> shops = Arrays.asList(
                new Shop("BestShop"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );

        long start = System.nanoTime();
        System.out.println(findDiscountedPricesWithFutures("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * 일반적인 스트림을 통해 여러 Shop에 호출 (Sequentially)
     */
    private static List<String> findPrices(String product, List<Shop> shops) {
        return shops.stream()
                    .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                    .collect(toList());
    }

    /**
     * 병렬 스트림을 이용해 여러 Shop에 호출
     * 하지만 여전히 Sync 방식의 호출이다.
     */
    private static List<String> findPricesWithParallelStream(String product, List<Shop> shops) {
        return shops.parallelStream()
                    .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                    .collect(toList());
    }

    /**
     * Sync API를 Async한 방식으로 호출하는 방법
     * 일반 스트림을 사용하여도 괜찮다. 왜냐면 supplyAsync자체가 바로 CompletableFuture를 Return하기 때문에!
     * List<String>을 얻기 위해 한번 더 스트림을 사용해서 Map operation을 수행한다.
     */
    private static List<String> findPricesWithAsyncRequests(String product, List<Shop> shops) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(
                             () -> shop.getName() + " price is " + shop.getPrice(product)))
                     .collect(toList());

        /**
         * 1. CompletableFuture.join과 get의 차이점은 단 하나이다.
         * - join() 메서드는 checked exception을 던지지 않는다. 따라서 map()에 try/catch block이 필요 없다.
         *
         * 2. 별개의 스트림에서 map operation을 한번 더 수행하는 이유?
         * - Lazy Nature of intermediate stream operations
         *   - 하나의 파이프라인에서 실행했다면, 모든 shop에 대한 request가 sync/sequentially하게 성공해야만 한다.
         *   - 무슨 말이냐면, 하나의 파이프라인에서 실행할 경우 하나의 shop에 대한 CompletableFuture 획득, join 식에 대한
         *     평가까지 이루어지고 나서야 다음 Shop에 대한 요청으로 넘어간다.
         *   - 별개의 파이프라인에서 실행하면, 일단 CompletableFuture를 획득하는걸 바로 수행한 후 획득한 CompletableFuture
         *     들에 대한 join 작업도 바로 진행할 수 있다.
         */
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    private static List<String> findPricesWithAsyncRequestsOnCustomExecutor(String product, List<Shop> shops) {
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                                             new ThreadFactory() {
                                                 @Override
                                                 public Thread newThread(Runnable r) {
                                                     Thread t = new Thread(r);
                                                     t.setDaemon(true);
                                                     return t;
                                                 }
                                             });

        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(
                             () -> shop.getName() + " price is " + shop.getPrice(product), executor))
                     .collect(toList());

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    private static List<String> findPricesWithAsyncRequestOnSinglePipeline(String product, List<Shop> shops) {
        return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(
                            () -> shop.getName() + " price is " + shop.getPrice(product)))
                    .map(CompletableFuture::join)
                    .collect(toList());
    }

    private static List<String> findDiscountedPrices(String product, List<Shop> shops) {
        return shops.stream()
                .map(shop -> shop.getPriceAsString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    private static List<String> findDiscountedPricesWithFutures(String product, List<Shop> shops) {
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                                             new ThreadFactory() {
                                                 @Override
                                                 public Thread newThread(Runnable r) {
                                                     Thread t = new Thread(r);
                                                     t.setDaemon(true);
                                                     return t;
                                                 }
                                             });

        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(
                             () -> shop.getPriceAsString(product), executor
                     ))
                     .map(future -> future.thenApply(Quote::parse))
                     .map(future -> future.thenCompose(quote ->
                                                               CompletableFuture.supplyAsync(
                                                                       () -> Discount.applyDiscount(quote),
                                                                       executor
                                                               )))
                     .collect(toList());

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }
    private static void doSomethingElse() {
        System.out.println("I'm doing something else..!!");
    }
}
