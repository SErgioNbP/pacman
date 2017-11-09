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

    public int getMoveCol() {
        return moveCol;
    }

    public int getMoveRow() {
        return moveRow;
    }


    public static Direction turnRight(Direction direction) {

        switch (direction) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                return UP;
        }
    }

    public static Direction turnLeft(Direction direction) {

        switch (direction) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                return UP;
        }
    }
}
