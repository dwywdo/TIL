package me.dwywdo.labs.java.polymorphism.before;

public class Main {
    public static void main(String[] args) {
        final Driver driver = new Driver();
        final K3Car k3Car = new K3Car();
        driver.setK3Car(k3Car);
        driver.drive();

        final Model3Car model3Car = new Model3Car();
        driver.setModel3Car(model3Car);
        driver.setK3Car(null);
        driver.drive();
    }
}
