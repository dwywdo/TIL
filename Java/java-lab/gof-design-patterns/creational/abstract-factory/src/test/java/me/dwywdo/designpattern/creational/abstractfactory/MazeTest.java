package me.dwywdo.designpattern.creational.abstractfactory;

import org.junit.jupiter.api.Test;

import me.dwywdo.designpattern.creational.sample.Maze;
import me.dwywdo.designpattern.designpattern.abstractfactory.BombedMazeFactory;
import me.dwywdo.designpattern.designpattern.abstractfactory.EnchantedMazeFactory;
import me.dwywdo.designpattern.designpattern.abstractfactory.MazeFactory;
import me.dwywdo.designpattern.designpattern.abstractfactory.MazeGame;

public class MazeTest {

    @Test
    void testNormalMaze() {
        MazeGame mazeGame = new MazeGame();
        Maze normalMaze = mazeGame.createMaze(new MazeFactory());
        Maze enchantedMaze = mazeGame.createMaze(new EnchantedMazeFactory());
        Maze bombedMaze = mazeGame.createMaze(new BombedMazeFactory());
    }
}
