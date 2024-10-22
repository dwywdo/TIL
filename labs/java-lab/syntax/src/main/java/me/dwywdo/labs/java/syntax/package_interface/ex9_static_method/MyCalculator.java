package me.dwywdo.labs.java.syntax.package_interface.ex9_static_method;

class MyCalculator implements Calculator {
    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}
