package me.dwywdo.lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import me.dwywdo.parameterizing_behavior.Apple;

public class Practice {
    List<Apple> inventory = Arrays.asList(
            new Apple("Red", 50),
            new Apple("Green", 160),
            new Apple("Yellow", 80)
    );

    @Test
    void step1() {
        inventory.sort(new AppleComparator());
    }

    @Test
    void step2() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
    }

    @Test
    void step3() {
        inventory.sort((Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        inventory.sort((a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        /**
         * Comparator는 헬퍼 메서드로 comparing 이라는 메서드를 가지고 있는데,
         * 비교에 사용될 키를 뽑아내서 Comparator 객체를 만들어주는 역할을 한다.
         */
        inventory.sort(Comparator.comparing((apple -> apple.getWeight())));
    }

    @Test
    void step4() {
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }
}
