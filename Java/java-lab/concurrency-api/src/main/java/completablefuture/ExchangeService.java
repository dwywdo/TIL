package completablefuture;

public final class ExchangeService {
    public enum Money {
        USD(1.0),
        EUR(1.2),
        KOR(1.5);

        private final double rate;

        Money(double rate) { this.rate = rate;}

    }

    public static double getRate(Money from, Money to) {
        delay();
        return from.rate / to.rate;
    }

    private static void delay() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
