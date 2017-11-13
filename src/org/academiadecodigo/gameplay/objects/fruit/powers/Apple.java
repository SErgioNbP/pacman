package org.academiadecodigo.gameplay.objects.fruit.powers;

import org.academiadecodigo.gameplay.grid.Position;
import org.academiadecodigo.gameplay.objects.fruit.Edible;

public class Apple implements Edible {

    private Position position;
    private int points;
    private volatile boolean eaten;
    private PowerType powerType;

    public Apple(Position position) {
        this.position = position;
        points = 50;
        eaten = false;
        powerType = PowerType.randomPowerType();
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

    public PowerType getPowerType() {
        return powerType;
    }
}
