package me.dwywdo.labs.java.syntax.package_interface.ex4_polymorph;

public class MyObject implements Controllable {
    @Override
    public void change() {
        System.out.println("Method to change channel");
    }

    @Override
    public void power(boolean b) {
        System.out.println("Method to turn on/off power");
    }
}
