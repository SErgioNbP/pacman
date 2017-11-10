package org.academiadecodigo.pacman.objects.fruit;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

/**
 * Created by codecadet on 05/11/17.
 */
public class Fruit extends GameObject implements Edible {

    //Properties
    private int points;
    private boolean eaten;

    //Constructor
    public Fruit(Position position) {
        super(position, ObjectType.FRUIT);
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
