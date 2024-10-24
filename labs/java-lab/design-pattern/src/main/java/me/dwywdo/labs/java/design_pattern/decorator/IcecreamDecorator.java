package me.dwywdo.labs.java.design_pattern.decorator;

public abstract class IcecreamDecorator implements Icecream {
    protected Icecream specialIcecream;

    public IcecreamDecorator(Icecream specialIcecream) {
        this.specialIcecream = specialIcecream;
    }

    @Override
    public String makeIcecream() {
        return specialIcecream.makeIcecream();
    }
}
