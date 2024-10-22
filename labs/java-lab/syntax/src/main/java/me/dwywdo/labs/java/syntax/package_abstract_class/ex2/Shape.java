package me.dwywdo.labs.java.syntax.package_abstract_class.ex2;

public abstract class Shape {
    public String type;
    public String subtype = "Subtype";

    protected Shape(String type) {
        System.out.println("Shape Constructor Invoked");
        this.type = type;
    }

    public abstract void draw();
}
