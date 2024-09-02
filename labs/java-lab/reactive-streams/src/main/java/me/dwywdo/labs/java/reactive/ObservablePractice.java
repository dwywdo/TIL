package me.dwywdo.labs.java.reactive;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public final class ObservablePractice {

    static class IntObservable extends Observable implements Runnable {
        @Override
        public void run() {
            for (int i=1; i<=10; i++) {
                setChanged();
                notifyObservers(i);
                // Pull 방식은
                // int i = it.next();
            }
        }
    }

    public static void main(String[] args) {
        // Observable 이라는 클래스가 자바 1 버전부터 존재했다.
        // 9 버전부터는 Deprecated.
        // addObserver() / notifyObservers() / ...

        // Observable = Source (Event/Data Source) -> Observer
        final Observer ob = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(Thread.currentThread().getName() + ' ' + arg);
            }
        };

        final IntObservable io = new IntObservable();
        io.addObserver(ob);

        // 메인쓰레드에서만 동작하도록 하려면
        // io.run();

        // 독립된 멸도 쓰레드에서 동작하도록 하려면
        final ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io);
        System.out.println(Thread.currentThread().getName() + ' ' + "EXIT");
        es.shutdown();
    }
}
