package me.dwywdo.labs.java.polymorphism.after;

// Car의 추가에 따라 변경될 필요가 없음
public class Driver {
    private Car car;

    public void setCar(Car car) {
        System.out.println("Driver.setCar");
        this.car = car;
    }

    public void drive() {
        car.startEngine();
        car.pressAccelerator();
        car.offEngine();
    }
}
