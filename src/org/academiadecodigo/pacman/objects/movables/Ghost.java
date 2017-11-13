package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;

public class Ghost implements Movable {

    private Direction direction;
    private Direction nextDirection = Direction.UP;
    private Position position;
    private boolean alive;

    public Ghost(Position position) {
        this.position = position;
        alive = true;
        direction = Direction.UP;
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
}

