package me.dwywdo.labs.java.design_pattern.decorator;

import org.junit.jupiter.api.Test;

public class TestDecorator {

    @Test
    void executeDecorator() {
        final Icecream icecream = new HoneyDecorator(new NuttyDecorator(new SimpleIcecream()));
        System.out.println("icecream.makeIcecream() = " + icecream.makeIcecream());
    }
}
