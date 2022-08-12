package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import completablefuture.Discount.Code;

public class Shop {
    private final String name;

    public String getName() {
        return name;
    }

    public Shop(String name) {
        this.name = name;
    }

    private final Random random = new Random();

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceAsString(String product) {
        double price = calculatePrice(product);
        Code code = Code.values()[random.nextInt(Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public String getPriceAsStringWithRandomDelay(String product) {
        double price = calculatePriceWithRandomDelay(product);
        Code code = Code.values()[random.nextInt(Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                /**
                 * (만약에 completeExceptionally와 같은 예외 처리 로직이 없는 상태에서)
                 * 별도의 쓰레드에서 수행되는 아래의 작업이 Exception을 발생시킨다면,
                 * 이 메서드를 호출하는 Client는 영원히 기다리게 될 것이다. (왜? 예외가 이 쓰레드 내에서만 남기 때문에)
                 * 이런 일을 방지하기 위해 Future.get() 메서드에는 Timeout 값을 줄 수 있다.
                 *
                 * 하지만 Timeout을 사용하면 영원히 기다리는 일은 방지할 수 있지만, TimeoutException으로만 전달받기 때문에
                 * 정확히 어떤 문제가 쓰레드 내에서 발생했는지 알기 어렵다.
                 *
                 * 따라서, Thread 내에 정의한 작업에서 작업에 대한 예외처리를 한 후, 발생한 Exception을 Propagation 해주어야 한다.
                 * 이 때 사용하는 것이 CompletableFuture.completeExceptionally() 메서드이다.
                 */
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    /**
     * getPriceAsync를 CompletableFuture에서 제공하는 Factory method들을 통해 간단히 구현할 수 있다.
     * getPriceAsync에서 추가해준 에러 처리 로직도 내부적으로 구현되어 있다!
     */
    public Future<Double> handyGetPriceAsync(String product) {
        /**
         * 아래의 Supplier, 즉 () -> calculatePrice(product);는 ForkJoinPool 내의 Executor 중 하나에 의해 실행된다.
         * 두번째 인자로 다른 Executor를 넘겨주면서 실행 주체를 정해줄 수도 있다.
         * 좀 더 일반적으로 말하면, 모든 CompletableFuture의 Factory methods에는 Executor를 넘겨줄 수 있다.
         */
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private double calculatePriceWithRandomDelay(String product) {
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
    }

    private void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
