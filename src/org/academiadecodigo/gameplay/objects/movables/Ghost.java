package org.academiadecodigo.gameplay.objects.movables;

import org.academiadecodigo.gameplay.grid.Direction;
import org.academiadecodigo.gameplay.grid.Position;

public class Ghost implements Movable {

    private Direction direction;
    private Direction nextDirection = Direction.UP;
    private Position position;
    private boolean alive;
    private int points;

    public Ghost(Position position) {
        this.position = position;
        alive = true;
        direction = Direction.UP;
        points = 200;
    }

    @Override
    public void move() {
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void die() {
        alive = false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(int col, int row) {
        position.setCol(col);
        position.setRow(row);
    }

    public int getPoints() {
        return points;
    }
}

