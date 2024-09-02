package me.dwywdo.labs.java.reactive;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Publisher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;

public class PubSub {
    public static void main(String[] args) {
        // Publisher <- Observable
        // Subscriber <- Observer

        final Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5);

        final Flow.Publisher<Integer> p = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {
                final Iterator<Integer> it = itr.iterator();
                subscriber.onSubscribe(
                        new Subscription() {
                            @Override
                            public void request(long n) {
                                try {
                                    while (n-- > 0) {
                                        if (it.hasNext()) {
                                            subscriber.onNext(it.next());
                                        } else {
                                            subscriber.onComplete();
                                            break;
                                        }
                                    }
                                } catch (RuntimeException e) {
                                    subscriber.onError(e);
                                }
                            }

                            @Override
                            public void cancel() {

                            }
                });
            }
        };

        final Subscriber<Integer> s = new Subscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe " + subscription);
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // Publisher가 데이터를 주면 Subscriber가 받으면서 이것으로 처리하기 위한 메서드
                System.out.println("onNext " + item);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                // Exception 발생시키지 않고, Exception에 해당하는 객체를 만들어서 onError를 통해 전달
                // Try-catch 구문이 필요가 없다.
                System.out.println("onError" + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
                // Publisher가 줄 데이터가 이제 없어서 완료되었다고 하는 것
            }
        };

        p.subscribe(s);

    }
}
