package me.dwywdo.labs.java.design_pattern.decorator;

public class NuttyDecorator extends IcecreamDecorator {

    public NuttyDecorator(Icecream specialIcecream) {
        super(specialIcecream);
    }

    @Override
    public String makeIcecream() {
        return specialIcecream.makeIcecream() + addNuts();
    }

    private String addNuts() {
        return " + cruncy nuts";
    }
}
