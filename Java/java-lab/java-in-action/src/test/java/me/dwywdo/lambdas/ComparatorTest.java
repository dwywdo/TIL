package me.dwywdo.lambdas;

import java.util.Comparator;
import java.util.*;

import org.junit.jupiter.api.Test;

import me.dwywdo.parameterizing_behavior.Apple;

public class ComparatorTest {
    @Test
    void comparatorTest() {
        /**
         * Below 2 versions of comparator is exactly same.
         */
        // With anonymous class
        Comparator<Apple> byWeightVerboseVersion = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return Integer.compare(a1.getWeight(), a2.getWeight());
            }
        };

        // With lambdas
        Comparator<Apple> byWeightConciseVersion = (Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight());
    }

    @Test
    void comparatorWithMethodReference() {
        List<Apple> inventory = Arrays.asList(
            new Apple("Red", 50),
            new Apple("Green", 160),
            new Apple("Yellow", 80)
        );
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }
}
