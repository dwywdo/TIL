package me.dwywdo.backward;

public interface MyInterface {
    default void defaultMethod() {
        System.out.println("This is default");
    }
}
