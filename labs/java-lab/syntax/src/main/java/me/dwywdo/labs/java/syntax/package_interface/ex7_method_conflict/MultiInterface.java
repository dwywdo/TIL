package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

public class MultiInterface implements A1, B1 {
    @Override
    public void styleA() {

    }

    @Override
    public void styleSame() {
        A1.super.styleSame();
    }

    @Override
    public void styleB() {

    }
}
