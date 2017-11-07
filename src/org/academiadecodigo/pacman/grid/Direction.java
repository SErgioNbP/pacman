package org.academiadecodigo.pacman.grid;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int moveCol;
    private int moveRow;

    Direction(int col, int row) {
        moveCol = col;
        moveRow = row;
    }

    public Direction randomDirection() {

        int random = (int) (Math.random() * values().length);
        Direction direction = values()[random];

        return direction;
    }
}
