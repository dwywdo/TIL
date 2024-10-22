package me.dwywdo.labs.java.syntax.package_abstract_class.ex2;

public class Figure extends Shape {
    public String name;

    public Figure(String type, String name) {
        super(type);
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println("Figure#Draw");
    }
}
