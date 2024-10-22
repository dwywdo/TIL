package me.dwywdo.labs.java.syntax.package_interface.ex3_partial_overriding;

public abstract class Mammalia implements Animal {

    @Override
    public void walk() {
        System.out.println("Walk by Mammalia");
    }

    @Override
    public void run() {
        System.out.println("Run by Mammalia");
    }

    // breed()는 구현되지 않음. 자식 클래스에서 구체적으로 구현하도록 추상 메서드로 유지
    // 따라서 클래스도 추상 클래스가 됨
}
