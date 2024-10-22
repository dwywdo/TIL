package me.dwywdo.labs.java.syntax.package_abstract_class.ex3;

/*public class Marine {
    int x, y;
    void move(int x, int y) {}
    void stop() {}
    void stimPack() {}
}*/

public class Marine extends Unit{
    @Override
    public void move(int x, int y) {
        System.out.println("Move by Walk");
    }

    void stimPack() {}
}
