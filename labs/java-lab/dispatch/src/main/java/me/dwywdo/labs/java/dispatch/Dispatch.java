package me.dwywdo.labs.java.dispatch;

import java.util.Arrays;
import java.util.List;

public class Dispatch {
    static class Service {
        void run() {
            System.out.println("run()");
        }

        void run(int number) {
            System.out.println("run(" + number + ')');
        }

        void run(String msg) {
            System.out.println("run(" + msg + ')');
        }
    }

    static abstract class DynamicService {
        abstract void run();
    }

    static class MyService1 extends DynamicService {
        @Override
        void run() {
            System.out.println("run1()");
        }
    }

    static class MyService2 extends DynamicService {

        @Override
        void run() {
            System.out.println("run2()");
        }
    }


    public static void main(String[] args) {
        // run() 메서드는 컴파일 시점에 어떤 메서드가 실행될지 알고 있다.
        // 컴파일러도 알고, 컴파일된 바이트 코드에도 그 정보가 그대로 남아 있다.
        // 이런 경우를 Static Dispatch라 한다.
        // Static Dispatch는 컴파일 시점에 어떤 메서드가 실행될지 정확히 아는 것!
        // 이름을 같지만 사실은 전혀 다른 메서드
        System.out.println("=========Static Dispatch==========");
        new Service().run();
        new Service().run(1);
        new Service().run("Dispatch");

        final MyService1 myService1 = new MyService1();
        final MyService2 myService2 = new MyService2();
        myService1.run();
        myService2.run();

        System.out.println("=========Dynamic Dispatch=========");
        // myService의 타입은 DynamicService이다.
        // run()을 여기서만 보면 MyService1 / MyService2 둘 중 무엇의 run()을 실행할 것인지 결정되어 있지 않다.
        // new MyService1()이라고 나와있지만 이것으로 결정하는 것은 아니다.
        // 단순히 추상 클래스의 메서드를 호출하는 것
        // 컴파일 시점에는 결정하지 못하고 있다.
        // 이 메서드가 실제로 어느 구현 메서드를 호출할 지는,
        // 런타임 시점에 myService에 할당되어 있는 객체가 무엇인지를 보고 (Receiver Parameter로) 이것에 의해 결정하는 것
        final DynamicService myService = new MyService1();
        myService.run(); // Receiver Parameter: myService라는 객체의 this에 해당하는 것이 Receiver Parameter로 들어가 있다.

        final List<DynamicService> svcs = Arrays.asList(new MyService1(), new MyService2());
        svcs.forEach(DynamicService::run); // s -> s.run(). Method Reference 메서드 타입이 일치하면 가져다 쓸 수 있다.;

    }
}
