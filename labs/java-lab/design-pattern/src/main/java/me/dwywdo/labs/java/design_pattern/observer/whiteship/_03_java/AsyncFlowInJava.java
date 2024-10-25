package me.dwywdo.labs.java.design_pattern.observer.whiteship._03_java;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class AsyncFlowInJava {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> submissionPublisher = new SubmissionPublisher<>();

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Sub!");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("onNext!");
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                System.out.println(item);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");

            }
        };

        submissionPublisher.subscribe(subscriber);
        submissionPublisher.submit("Hello Java");
        System.out.println("이게 출력이 되기 전에 처리가 다 끝남.");
        Thread.sleep(5000);
    }
}
