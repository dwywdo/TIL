package me.dwywdo.labs.java.syntax.package_interface.ex8_default_super;

public interface IPrint {
    default void print() {
        System.out.println("This is a default method from 'interface' IPrint");
    }
}
