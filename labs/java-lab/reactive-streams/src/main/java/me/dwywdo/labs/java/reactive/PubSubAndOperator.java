package me.dwywdo.labs.java.reactive;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PubSubAndOperator {
    public static void main(String[] args) {
        final Iterable<Integer> iterable = Stream.iterate(1, value -> value + 1)
                                                 .limit(10)
                                                 .collect(Collectors.toList());

        final Publisher<Integer> publisher = iterPub(iterable);
        final Subscriber<Integer> subscriber = logSub();

        publisher.subscribe(subscriber);
    }

    private static Subscriber<Integer> logSub() {
        return new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.debug("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer item) {
                log.debug("onNext:{}", item);
            }

            @Override
            public void onError(Throwable throwable) {
                log.debug("onError", throwable);

            }

            @Override
            public void onComplete() {
                log.debug("onComplete");
            }
        };
    }

    private static Publisher<Integer> iterPub(Iterable<Integer> iterable) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        try {
                            iterable.forEach(value -> subscriber.onNext(value));
                            subscriber.onComplete();
                        } catch (Throwable t) {
                            subscriber.onError(t);
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
    }
}
