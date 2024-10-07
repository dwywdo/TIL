package me.dwywdo.labs.java.reactive.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInternalEx {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub -> {
            sub.onSubscribe(new Subscription() {
                int no = 0;
                boolean cancelled = false;

                @Override
                public void request(long l) {
                    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
                    exec.scheduleAtFixedRate(
                            () -> {
                                sub.onNext(no++);
                                if (cancelled) {
                                    exec.shutdown();
                                    return;
                                }
                            },
                            0,
                            500,
                            TimeUnit.MILLISECONDS
                    );
                }

                @Override
                public void cancel() {
                    cancelled = true;
                }
            });
        };


        Publisher<Integer> takePub = sub -> {
            pub.subscribe(new Subscriber<Integer>() {
                Subscription subscription;
                int count = 0;

                @Override
                public void onSubscribe(Subscription subscription) {
                    this.subscription = subscription;
                    sub.onSubscribe(subscription);
                }

                @Override
                public void onNext(Integer integer) {
                    sub.onNext(integer);
                    if (++count >= 10) {
                        subscription.cancel();
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    sub.onError(throwable);
                }

                @Override
                public void onComplete() {
                    sub.onComplete();
                }
            });
        };

        takePub.subscribe(new Subscriber<Integer>() {
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
    }
}
