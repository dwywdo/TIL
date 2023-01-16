package me.dwywdo.designpattern.creational.sample;

public class Door extends MapSite{
    private final Room r1;
    private final Room r2;

    private boolean isOpen;

    public Door(Room r1, Room r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    void enter() {
        System.out.println("Enter from Door between room " + r1.getRoomNumber() + ", " + r2.getRoomNumber());
    }

    Room otherSideFrom(Room r) {
        if (r1 == null || r2 == null) {
            throw new IllegalStateException("This door doesn't have 2 rooms connected");
        }

        int roomNumber = r.getRoomNumber();

        if (roomNumber == r1.getRoomNumber()) return r2;
        else if (roomNumber == r2.getRoomNumber()) return r1;
        else throw new IllegalArgumentException("This door has no room connected with number " + roomNumber);
    }
}
