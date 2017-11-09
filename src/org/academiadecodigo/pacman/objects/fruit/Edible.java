package org.academiadecodigo.pacman.objects.fruit;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

/**
 * Created by codecadet on 05/11/17.
 */
public class Edible extends GameObject {

    //Properties
    private int points;
    private boolean eaten;

    //Constructor
    public Edible(Position position, ObjectType type) {
        super(position, type);
        points = 100;
    }

    //Methods
    public int getPoints() {
        return points;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten() {
        eaten = true;
    }
}
