package me.dwywdo.lambdas;

import java.util.Comparator;

import me.dwywdo.parameterizing_behavior.Apple;

public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple a1, Apple a2) {
        return Integer.compare(a1.getWeight(), a2.getWeight());
    }
}
