package me.dwywdo.labs.java.syntax.package_interface.ex10_private_method;

interface Calculator {
    public int plus(int a, int b);
    public int multiply(int a, int b);

    private void printf() {
        System.out.println("Private method can be called within default method");
    }

    private static void printfStatic() {
        System.out.println("Private static method can be within static method");
    }

    default void callPrivate() {
        printf();
    }

    static void callPrivateStatic() {
        printfStatic();
    }

}
