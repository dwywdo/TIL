package me.dwywdo.labs.java.syntax.package_interface.ex8_default_super;

public class MyClass implements IPrint {
    @Override
    public void print() {
        IPrint.super.print();
        System.out.println("This is a overriding method from IPrint");
    }
}
