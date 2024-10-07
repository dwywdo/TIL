package me.dwywdo.labs.java.reactive.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchedulerEx {

    // Reactive Streams
    public static void main(String[] args) {

        // publisher
        final Publisher<Integer> publisher = sub -> {
            sub.onSubscribe(new Subscription() {
                @Override
                public void request(long l) {
                    log.info("request()");
                    sub.onNext(1);
                    sub.onNext(2);
                    sub.onNext(3);
                    sub.onNext(4);
                    sub.onNext(5);
                    sub.onComplete();
                }

                @Override
                public void cancel() {
                    sub.onComplete();
                }
            });
        };

        // Operator (subOnPub)를 하나 추가하자

        final Publisher<Integer> subOnPub = sub -> {
            ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory() {
                @Override
                public String getThreadNamePrefix() {
                    return "subOn-";
                }
            });
            es.execute(() -> publisher.subscribe(sub));
        };

        Publisher<Integer> pubOnPub = sub -> {
            subOnPub.subscribe(new Subscriber<Integer>() {
                ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory() {
                    @Override
                    public String getThreadNamePrefix() {
                        return "pubOn-";
                    }
                });

                @Override
                public void onSubscribe(Subscription subscription) {
                    sub.onSubscribe(subscription);
                }

                @Override
                public void onNext(Integer integer) {
                    executorService.execute(() -> sub.onNext(integer));
                }

                @Override
                public void onError(Throwable throwable) {
                    executorService.execute(() -> sub.onError(throwable));
                    executorService.shutdown();
                }

                @Override
                public void onComplete() {
                    executorService.execute(() -> sub.onComplete());
                    executorService.shutdown();
                }
            });
        };

        // subscriber
        pubOnPub.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.info("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext: {}", integer);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError: {}", throwable);
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
        log.info("EXIT");
    }
}
