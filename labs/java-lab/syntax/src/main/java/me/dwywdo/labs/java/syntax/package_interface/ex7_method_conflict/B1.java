package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

public interface B1 {
    public void styleB();

    default void styleSame() {
        System.out.println("This is a default method from 'interface' B1");
    }
}
