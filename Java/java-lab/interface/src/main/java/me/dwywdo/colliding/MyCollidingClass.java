package me.dwywdo.colliding;

public class MyCollidingClass implements MyCollidingInterfaceOne, MyCollidingInterfaceTwo{
    @Override
    public void collide() {
        System.out.println("Let's resolve collision for default method!");
    }

    public void chooseDefault() {
        MyCollidingInterfaceOne.super.collide();
        MyCollidingInterfaceTwo.super.collide();
    }
}
