package me.dwywdo.basic;

public abstract class MyAbstractClass implements MyInterface {
    @Override
    public void myFirstMethod() {
        System.out.println("I'll provide implementation for this");
    }

    @Override
    public abstract void mySecondMethod();
}
