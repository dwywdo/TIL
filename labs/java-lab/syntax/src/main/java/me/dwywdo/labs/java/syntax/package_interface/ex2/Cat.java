package me.dwywdo.labs.java.syntax.package_interface.ex2;

public class Cat extends Tail implements Animal, Pet {

    @Override
    public void cry() {
        System.out.println("Meow");
    }

    @Override
    public void play() {
        System.out.println("Playeow");
    }
}
