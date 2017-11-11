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
    public void die() {
        alive = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPositionColRow(int col, int row) {
        position.setCol(col);
        position.setRow(row);
    }

    public boolean isAlive() {
        return alive;
    }
}
