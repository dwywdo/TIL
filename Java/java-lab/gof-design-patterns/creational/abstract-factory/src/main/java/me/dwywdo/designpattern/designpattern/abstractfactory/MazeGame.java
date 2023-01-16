package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Direction;
import me.dwywdo.designpattern.creational.sample.Door;
import me.dwywdo.designpattern.creational.sample.Maze;
import me.dwywdo.designpattern.creational.sample.Room;

public class MazeGame {
    public Maze createMaze(MazeFactory mazeFactory) {
        Maze maze = mazeFactory.makeMaze();
        Room r1 = mazeFactory.makeRoom(1);
        Room r2 = mazeFactory.makeRoom(2);
        Door door = mazeFactory.makeDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.NORTH, mazeFactory.makeWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, mazeFactory.makeWall());
        r1.setSide(Direction.WEST, mazeFactory.makeWall());

        r2.setSide(Direction.NORTH, mazeFactory.makeWall());
        r2.setSide(Direction.EAST, mazeFactory.makeWall());
        r2.setSide(Direction.SOUTH, mazeFactory.makeWall());
        r2.setSide(Direction.WEST, door);

        return maze;
    }
}
