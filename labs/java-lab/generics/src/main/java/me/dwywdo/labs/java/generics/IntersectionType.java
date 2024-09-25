package me.dwywdo.labs.java.generics;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

public class IntersectionType {
    interface Hello {
        default void hello() {
            System.out.println("Hello");
        }
    }

    interface Hi {
        default void hi() {
            System.out.println("Hi");
        }
    }

    interface Printer {
        default void print(String str) {
            System.out.println("str = " + str);
        }
    }

    // Intersection Type and Delegate
    interface DelegateTo<T> {
        T delegate();
    }

    interface HelloDelegate extends DelegateTo<String> {
        default void hello() {
            System.out.println("Hello " + delegate());
        }
    }

    interface UpperCaseDelegate extends DelegateTo<String> {
        default void upperCase() {
            System.out.println(delegate().toUpperCase());
        }
    }

    // Example
    interface Pair<T> {
        T getFirst();
        T getSecond();
        void setFirst(T first);
        void setSecond(T second);
    }

    static class Name implements Pair<String> {
        String firstName;
        String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String getFirst() {
            return this.firstName;
        }

        @Override
        public String getSecond() {
            return this.lastName;
        }

        @Override
        public void setFirst(String first) {
            this.firstName = first;
        }

        @Override
        public void setSecond(String second) {
            this.lastName = second;
        }
    }

    interface ForwardingPair<T> extends DelegateTo<Pair<T>>, Pair<T> {
        default T getFirst() { return delegate().getFirst(); }
        default T getSecond() { return delegate().getSecond(); }
        default void setFirst(T first) { delegate().setFirst(first); }
        default void setSecond(T second) { delegate().setSecond(second); }
    }

    // 기능을 추가해보자
    interface Convertable<T> extends DelegateTo<Pair<T>> {
        default void convert(Function<T, T> mapper) {
            final Pair<T> pair = delegate();
            pair.setFirst(mapper.apply(pair.getFirst()));
            pair.setSecond(mapper.apply(pair.getSecond()));
        }
    }

    interface Printable<T> extends DelegateTo<Pair<T>> {
        default void print() {
            System.out.println("delegate().getFirst() + delegate().getSecond() = " + delegate().getFirst()
                               + delegate().getSecond());
        }
    }

    static <T> void print(Pair<T> pair) {
        System.out.println("pair.getFirst() + pair.getSecond() = " + pair.getFirst() + pair.getSecond());
    }


    public static void main(String[] args) {
        final Function<String, String> f = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };

        hello(f);
        hello(s -> s);
        hello((Function)s -> s);
        hello((Function & Serializable & Cloneable) s->s);
        hello((Function & Hello & Hi)s -> s);
        hello2((Function & Hello & Hi)s -> s);

        run((Function & Hello & Hi)s->s, o -> {
            o.hello();
            o.hi();
        });

        run((Function & Hello & Hi & Printer)s -> s, o -> {
           o.hello();
           o.hi();
           o.print("Lambda");
        });

        // Intersection Type and Delegate
        runDelegate((DelegateTo<String> & HelloDelegate & UpperCaseDelegate)() -> "Lee Loustler", o -> {
            o.hello();
            o.upperCase();
        });

        // Example
        Pair<String> name = new Name("Toby", "Lee");
        runDelegate((ForwardingPair<String> & Convertable<String> & Printable<String>)() -> name, o -> {
            System.out.println("o.getFirst() = " + o.getFirst());
            System.out.println("o.getSecond() = " + o.getSecond());
            o.print();
            o.convert(s -> s.toUpperCase());
            o.print();
            o.convert(s -> s.substring(0, 2));
            o.print();
        });

        // 기능을 추가해보자.

    }

    private static <T extends DelegateTo<S>, S> void runDelegate(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    private static void hello(Function o) {

    }

    private static <T extends Function & Hello & Hi> void hello2(T o) {
        o.hello();
        o.hi();
    }

    private static <T extends Function> void run(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
}
