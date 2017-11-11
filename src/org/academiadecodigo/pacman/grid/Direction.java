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

    public static Direction randomDirection() {

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

    public static Direction changeGhostDirection(){
        return randomDirection();
    }

    /*
    public static Direction turnInDirection(Direction direction) {

        int randomNumber = (int) (Math.random() * 2);

        switch (direction) {

            case UP:

                if (randomNumber == 0) {
                    return RIGHT;
                } else {
                    return DOWN;
                }

            case DOWN:

                if (randomNumber == 0) {
                    return LEFT;
                } else {
                    return UP;
                }

            case LEFT:

                if (randomNumber == 0) {
                    return RIGHT;
                } else {
                    return UP;
                }

            case RIGHT:

                if (randomNumber == 0) {
                    return LEFT;
                } else {
                    return DOWN;
                }

            default:
                return UP;
        }
    }*/


    /*
    public static Direction changeDirection(Direction direction) {

        return Direction.turnInDirection(direction);
    }
    */

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
                return DOWN;
        }
    }

    public static Direction turnBack(Direction direction) {

        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return RIGHT;
        }
    }

}
