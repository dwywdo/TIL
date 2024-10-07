package me.dwywdo.labs.java.reactive.scheduler;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxExamplePublishers {
    public static void main(String[] args) throws InterruptedException {
        // Flux.interval(Duration.ofMillis(500)).subscribe(s -> log.info("onNext: {}", s));
        // 위 코드 자체로는 실행이 되지 않는다. 사실 별개의 쓰레드에서 실행되게 되는데... 마치 subscribeOn 처럼
        // 그래서 찾아보면 Sleep을 걸라고 보통 되어 있다.
        // 근데 왜 걸어야 되나?
        // 많은 사람들이 Main 쓰레드가 종료되었으니까 종료되어야지 라고 생각하는데 사실 그렇지 않다.
        // 아까 subscribeOn 걸었는데 종료 안되었잖아... 좀 이상한데?
        // User가 만든 쓰레드는 Main 쓰레드가 종료가 되어도 종료되지 않는다.
        // 그럼 이를 통해 우리는 뭘 알 수 있는가?
        // Interval 같은게 사용하는 쓰레드는 User Thread가 아니고 Demon Thread를 만든다.
        // Demon Thread의 특징? JVM은 User Thread가 하나도 남아있지 않고 Demon 만 남아있으면 그냥 강제로 종료해버린다.
        // User Thread는 한 개 만이라도 남아있으면 종료가 되지 않는다.

        // User Thread의 예시
        /*Executors.newSingleThreadExecutor().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            log.info("Hello");
        });*/


        Flux.interval(Duration.ofMillis(500))
            .take(10)
            .subscribe(s -> log.info("onNext: {}", s));

        TimeUnit.SECONDS.sleep(10);
    }
}
