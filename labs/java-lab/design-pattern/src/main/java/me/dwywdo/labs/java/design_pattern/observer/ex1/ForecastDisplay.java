package me.dwywdo.labs.java.design_pattern.observer.ex1;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement {
    Observable observable;

    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.lastPressure = currentPressure;
            this.currentPressure = weatherData.getPressure();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("기압:");
        if (currentPressure > lastPressure) {
            System.out.println("기압 증가");
        } else if (lastPressure == currentPressure) {
            System.out.println("기압 변동 없음");
        } else {
            System.out.println("기압 하강");
        }
    }
}
