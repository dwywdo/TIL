package me.dwywdo.parameterizing_behavior;

public class FancyAppleFormatter implements AppleFormatter {
    @Override
    public String format(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
