package me.dwywdo.labs.java.syntax.package_interface.ex6_default_method;

class MyCalculator implements Calculator {
    @Override
    public int plus(int i, int j) {
        return i + j;
    }

    @Override
    public int multiple(int i, int j) {
        return i * j;
    }
}
