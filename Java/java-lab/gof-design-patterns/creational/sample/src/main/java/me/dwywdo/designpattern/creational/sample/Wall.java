package me.dwywdo.designpattern.creational.sample;

public class Wall extends MapSite{

    public Wall() {};

    @Override
    void enter() {
        System.out.println("Enter from Wall");
    }
}
