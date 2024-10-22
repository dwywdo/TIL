package me.dwywdo.labs.java.syntax.package_interface.ex6_default_method;

interface Calculator {
    int plus(int i, int j);
    int multiple(int i, int j);

    default int sub(int i, int j) {
        return i - j;
    }
}
