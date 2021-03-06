package org.academiadecodigo.pacman.objects.fruit;

import org.academiadecodigo.pacman.grid.Position;

/**
 * Created by codecadet on 05/11/17.
 */
public class Fruit implements Edible {

    private Position position;
    private int points;
    private volatile boolean eaten;

    public Fruit(Position position) {
        this.position = position;
        eaten = false;
        points = 10;
    }

    public int getPoints() {
        return points;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
        eaten = true;
    }

    public Position getPosition() {
        return position;
    }
}
