package org.academiadecodigo.gameplay.objects.movables;

import org.academiadecodigo.gameplay.grid.Position;

public class Enemy implements Movable {

    private Position position;
    private boolean alive;

    public Enemy(Position position) {
        this.position = position;
        alive = true;
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
