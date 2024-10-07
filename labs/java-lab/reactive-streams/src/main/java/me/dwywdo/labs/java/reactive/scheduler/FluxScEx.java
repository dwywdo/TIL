package me.dwywdo.labs.java.reactive.scheduler;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxScEx {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .publishOn(Schedulers.newSingle("pub"))
                 .log()
                .subscribeOn(Schedulers.newSingle("sub"))
                .subscribe(System.out::println);
        // Subscriber 객체를 통째로 만들지 않고, 하나만 쓰면 onNext를 처리하는 람다식을 하나 받는다.
        // Method Reference를 넣어서 넘어온 데이터를 그냥 출력하도록 하기만 한 것.

        System.out.println("Exit");
    }
}
