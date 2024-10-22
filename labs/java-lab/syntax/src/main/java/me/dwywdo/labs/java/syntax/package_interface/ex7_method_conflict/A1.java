package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

public interface A1 {
    public void styleA();

    default public void styleSame() {
        System.out.println("This is a default method from 'interface' A1");
    }
}

