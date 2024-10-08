package me.dwywdo.labs.java.reactive.spring;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NoviceReactiveSpringApplication {
    @RestController
    public static class Controller {
        @RequestMapping("/hello") // localhost:8080/hello?name=Reactive
        public Publisher<String> hello(String name) {
            return new Publisher<String>() {
                @Override
                public void subscribe(Subscriber<? super String> subscriber) {
                    subscriber.onSubscribe(new Subscription() {
                        @Override
                        public void request(long l) {
                            subscriber.onNext("Hello, " + name);
                            subscriber.onComplete();
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                }
            };
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(NoviceReactiveSpringApplication.class, args);
    }
}
