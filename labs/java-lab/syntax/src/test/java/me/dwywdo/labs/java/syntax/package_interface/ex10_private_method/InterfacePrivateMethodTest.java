package me.dwywdo.labs.java.syntax.package_interface.ex10_private_method;

import org.junit.jupiter.api.Test;

public class InterfacePrivateMethodTest {
    @Test
    void interfacePrivateMethodTest() {
        final Calculator c = new MyCalculator();
        c.callPrivate();

        Calculator.callPrivateStatic();
    }
}
