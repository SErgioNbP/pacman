package org.academiadecodigo.pacman.objects.fruit.powers;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.fruit.Edible;

public class Apple implements Power, Edible {

    private Position position;
    private int points;
    private volatile boolean eaten;
    private Power power;

    public Apple(Position position) {
        this.position = position;
        points = 250;
        eaten = false;
        // power = randomPower;
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

    public Power getPower() {
        return power;
    }
}
