package org.academiadecodigo.pacman.objects.fruit.powers;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;
import org.academiadecodigo.pacman.objects.fruit.Edible;

public class Apple extends GameObject implements Power,Edible {

    //Properties
    private int points;
    private boolean eaten;
    private Power power;

    //Constructor
    public Apple(Position position) {
        super(position, ObjectType.POWERUP);
        points = 250;
        // power = randomPower;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean isEaten() {
        return eaten;
    }

    @Override
    public void setEaten() {
        eaten = !eaten;
    }

    @Override
    public Power getPower() {
        return power;
    }
}
