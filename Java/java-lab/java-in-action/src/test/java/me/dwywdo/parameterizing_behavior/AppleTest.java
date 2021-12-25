package me.dwywdo.parameterizing_behavior;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AppleTest {
    List<Apple> inventory = Arrays.asList(
            new Apple("Red", 50),
            new Apple("Green", 160),
            new Apple("Yellow", 80)
    );

    @Test
    void useSimpleFormatter() {
        prettyPrintApple(inventory, new SimpleAppleFormatter());
    }

    @Test
    void useFancyFormatter() {
        prettyPrintApple(inventory,new FancyAppleFormatter());
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String output = appleFormatter.format(apple);
            System.out.println(output);
        }
    }
}
