package me.dwywdo.parameterizing_behavior;

public class SimpleAppleFormatter implements AppleFormatter {
    @Override
    public String format(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
