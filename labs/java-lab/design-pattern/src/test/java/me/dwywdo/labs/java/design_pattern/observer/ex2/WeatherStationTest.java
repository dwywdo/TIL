package me.dwywdo.labs.java.design_pattern.observer.ex2;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class WeatherStationTest {
    static final WeatherData weatherData = new WeatherData(new ArrayList<>());
    static ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
    static CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);

    @Test
    void testWeatherStation() {
        System.out.println("날씨가 변한다.");
        changeWeather(40, 50, 10);
        System.out.println("\n날씨가 변한다.");
        changeWeather(50, 60, 20);
        System.out.println();
    }

    private static void changeWeather(float temperature, float humidity, float pressure) {
        weatherData.setMeasurements(temperature, humidity, pressure);
    }
}
