package me.dwywdo.labs.java.syntax.package_interface.ex6_default_method;

import org.junit.jupiter.api.Test;

public class DefaultMethodTest {
    @Test
    void testDefaultMethod() {
        final MyCalculator myCalculator = new MyCalculator();

        final Calculator cal = (Calculator) myCalculator;

        final int value = cal.sub(5, 10);

        final int value2 = myCalculator.sub(5, 10);

        System.out.println("value = " + value);
        System.out.println("value2 = " + value2);
    }
}
