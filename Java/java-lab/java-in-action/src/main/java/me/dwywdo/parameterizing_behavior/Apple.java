package me.dwywdo.parameterizing_behavior;

public class Apple {
    String color;
    int weight;

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }
}
