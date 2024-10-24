package me.dwywdo.labs.java.design_pattern.observer.ex2;

public class CurrentConditionDisplay implements Observer, DisplayElement {
    Observable observable;

    private float temperature;
    private float humidity;

    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("temperature = " + temperature + ", humidity = " + humidity + '%');
    }

    @Override
    public void update(Observable o) {
        if (o instanceof WeatherData) {
            final WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }

    }
}
