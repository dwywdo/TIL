package me.dwywdo.labs.java.reactive;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Reactive Streams - Operators
 * <p>
 * 기본 구조:
 * Publisher -> Data -> Subscriber
 * <p>
 * Operator를 추가하게 되면 데이터의 가공 및 변경이 가능해진다.
 * <p>
 * Operator 적용 구조:
 * Publisher -> **Data** -> Data Operator#1 -> **Data2** -> Data Operator#2 -> **Data3** -> Subscriber
 *
 * 1. map (d1 -> f -> d2)
 *   <--- Upstream | publisher -> [Data1] -> mapPublisher -> [Data2] -> logSub | ---> Downstream
 *                                         <- subscribe(logSub)
 *                                         -> onSubscribe(s)
 *                                         -> onNext
 *                                         -> onNext
 *                                         -> onComplete
 */
@Slf4j
public class PubSubAndOperator {
    public static void main(String[] args) {
        final Iterable<Integer> iterable = Stream.iterate(1, value -> value + 1)
                                                 .limit(10)
                                                 .collect(Collectors.toList());

        final Publisher<Integer> publisher = iterPub(iterable);
        final Publisher<Integer> mapPublisher = mapPub(publisher, s -> s * 10);
        final Publisher<Integer> mapPublisher2 = mapPub(mapPublisher, s -> -s);
        mapPublisher2.subscribe(logSub());
    }

    private static Publisher<Integer> mapPub(Publisher<Integer> pub,
                                             Function<Integer, Integer> f) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                pub.subscribe(new DelegateSub(sub) {
                    @Override
                    public void onNext(Integer item) {
                        sub.onNext(f.apply(item));
                    }
                });
            }
        };
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
