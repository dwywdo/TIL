package me.dwywdo.labs.java.reactive;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.BiFunction;
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
 * 2. sum (d1,d1,d2 -> f -> d1+d2+d3)
 *   <--- Upstream | publisher -> [Data1, Data2, Data3] -> sumPublisher -> [Data1+2+3] -> logSub | ---> Downstream
 *                                                       <- subscribe(logSub)
 *                                                       -> onSubscribe(s)
 *                                                       -> onNext
 *                                                       -> onNext
 *                                                       -> onComplete
 */
@Slf4j
public class PubSubAndOperator {
    public static void main(String[] args) {
        final Iterable<Integer> iterable = Stream.iterate(1, value -> value + 1)
                                                 .limit(10)
                                                 .collect(Collectors.toList());

        final Publisher<Integer> publisher = iterPub(iterable);
        // final Publisher<Integer> sumPublisher = sumPub(publisher);
        /**
         * Lambda만 쓰면 어떤 타입으로 해석해야 할 지 모르기 때문에 BiFunction으로 캐스팅해주어야 한다.
         * 안해줘도 알아서 해석하긴 할 것이다.
         */
        final Publisher<String> reducePublisher = reducePub(publisher, "", (BiFunction<String, Integer, String>)(a, b) -> a + '-' + b);
        reducePublisher.subscribe(logSub());

        // final Publisher<String> mapPub = mapPub(publisher, s -> "[" + s + ']');
        // final Publisher<List> listMapPub = mapPub(publisher, Arrays::asList);

    }

    /**
     * 1,2,3,4,5
     * 0 -> (0, 1) => 0 + 1 = 1
     * 1 -> (1, 2) => 1 + 2 = 3
     * 3 -> (3, 3) => 3 + 3 = 6
     * ...
     */
    /**
     *
     */
    private static Publisher<String> reducePub(Publisher<Integer> publisher,
                                                String init,
                                                BiFunction<String, Integer, String> bf
    ) {
        return new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> sub) {
                publisher.subscribe(new DelegateSub<Integer, String>(sub) {
                    String result = init;

                    @Override
                    public void onNext(Integer item) {
                        result = bf.apply(result, item);
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(result);
                        sub.onComplete();
                    }
                });
            }
        };

    }

    /*private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                pub.subscribe(new DelegateSub(sub) {
                    int sum = 0;

                    @Override
                    public void onNext(Integer item) {
                        sum += item;
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(sum);
                        sub.onComplete();
                    }
                });
            }
        };
    }
*/

    // T -> R
    private static <T, R> Publisher<R> mapPub(Publisher<T> pub,
                                           Function<T, R> f) {
        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> sub) {
                pub.subscribe(new DelegateSub<T, R>(sub) {
                    @Override
                    public void onNext(T item) {
                        sub.onNext(f.apply(item));
                    }
                });
            }
        };
    }

    private static <T> Subscriber<T> logSub() {
        return new Subscriber<T>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.debug("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(T item) {
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
