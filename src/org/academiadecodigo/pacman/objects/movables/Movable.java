package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.grid.Position;

public interface Movable {

    void move();

    boolean isAlive();

    void die();

    Position getPosition();

    void setPosition(int col, int row);
}
