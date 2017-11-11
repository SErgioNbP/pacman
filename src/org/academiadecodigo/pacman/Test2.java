package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;

public class Test2 {

    public static void main(String[] args) {

        FileHelper.generateLists();

        for (Position pos : FileHelper.allPositions) {
            System.out.println("ALL_POSITIONS : (" + pos.getCol() + "," + pos.getRow() + ")");
            System.out.println(FileHelper.getCurrentChar(pos.getCol(), pos.getRow()));
        }

        for (Position pos : FileHelper.players) {
            System.out.println("Player : (" + pos.getCol() + "," + pos.getRow() + ")");
        }

        for (Position pos : FileHelper.ghostsPos) {
            System.out.println("Ghost : (" + pos.getCol() + "," + pos.getRow() + ")");
        }

        for (Position pos : FileHelper.applesPos) {
            System.out.println("Apple : (" + pos.getCol() + "," + pos.getRow() + ")");
        }

        for (Position pos : FileHelper.fruitsPos) {
            System.out.println("Point : (" + pos.getCol() + "," + pos.getRow() + ")");
        }

        for (Position pos : FileHelper.walls) {
            System.out.println("Wall : (" + pos.getCol() + "," + pos.getRow() + ")");
        }

        for (Position pos : FileHelper.path) {
            System.out.println("Path : (" + pos.getCol() + "," + pos.getRow() + ")");
        }
    }
}
