package me.dwywdo.lambdas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class FunctionalInterfaceTest {
    @Test
    void usePredicate() {
        List<String> listOfStrings = Arrays.asList("Hi", "", "Bye");
        Predicate<String> nonEmptyStringPredicte = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicte);
        System.out.println(nonEmpty);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list) {
            if(p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    @Test
    void useConsumer() {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }

    private static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i: list) {
            c.accept(i);
        }
    }

    @Test
    void useFunction() {
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(l);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s: list) {
            result.add(f.apply(s));
        }
        return result;
    }
}
