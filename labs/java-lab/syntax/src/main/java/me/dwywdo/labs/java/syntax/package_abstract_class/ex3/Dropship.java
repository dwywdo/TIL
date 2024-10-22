package me.dwywdo.labs.java.syntax.package_abstract_class.ex3;

/*public class Dropship {
    int x, y;
    void move(int x, int y) {}
    void stop() {}
    void loadUnload() {}
}*/

public class Dropship extends Unit{
    @Override
    public void move(int x, int y) {
        System.out.println("Move by Fly");
    }

    void loadUnload() {}
}
