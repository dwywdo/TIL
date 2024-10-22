package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

public class MultiClassInterface extends C1 implements A1 {
    @Override
    public void styleA() {
        System.out.println("This is a overriding method for styleA()");
    }
}
