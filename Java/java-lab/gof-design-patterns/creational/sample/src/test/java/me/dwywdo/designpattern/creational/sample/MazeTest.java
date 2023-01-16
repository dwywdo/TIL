package me.dwywdo.designpattern.creational.sample;

import org.junit.jupiter.api.Test;

public class MazeTest {
    MazeGame mazeGame = new MazeGame();
    @Test
    void createMaze() {
        Maze maze = mazeGame.createMaze();
        System.out.println("Maze is created..!");
    }

    @Test
    void findRoomInMaze() {
        int targetRoomNumber = 1;
        Maze maze = mazeGame.createMaze();
        Room foundRoom = maze.roomNo(targetRoomNumber);
        System.out.println("Found Room: " + targetRoomNumber);
    }

    @Test
    void findOtherSideRoom() {
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        Door door = new Door(r1, r2);
        Room notLinkedRoom = new Room(2);
        Room foundRoom = door.otherSideFrom(notLinkedRoom);
        System.out.println("Found Room: " + foundRoom.getRoomNumber());
    }
}
