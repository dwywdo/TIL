package me.dwywdo.labs.java.polymorphism.after;

public class Main {
    public static void main(String[] args) {
        final Driver driver = new Driver();

        driver.setCar(new K3Car());
        driver.drive();

        driver.setCar(new Model3Car());
        driver.drive();
    }
}
