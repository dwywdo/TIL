package me.dwywdo.labs.java.syntax.package_abstract_class.ex3;

/*public class Tank {
    int x, y;
    void move(int x, int y) {}
    void stop() {}
    void siegeMode() {}
}*/

public class Tank extends Unit{
    @Override
    public void move(int x, int y) {
        System.out.println("Move by Drive");
    }

    void siegeMode() {}
}
