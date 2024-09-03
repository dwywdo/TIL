package me.dwywdo.labs.java.reactive;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class DelegateSub implements Subscriber<Integer> {
    Subscriber sub;

    public DelegateSub(Subscriber sub) {
        this.sub = sub;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        sub.onSubscribe(subscription);
    }

    @Override
    public void onNext(Integer item) {
        sub.onNext(item);
    }

    @Override
    public void onError(Throwable throwable) {
        sub.onError(throwable);
    }

    @Override
    public void onComplete() {
        sub.onComplete();
    }

}
