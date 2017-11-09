package org.academiadecodigo.pacman.objects.fruit;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Edible extends GameObject {

    //TODO EDIT THIS CLASS
    //Properties
    private int points;
    private boolean eaten;

    //Constructor
    public Edible(Position position, Terminal.Color color) {
        super(position, color);
        points = 100;
    }

    //Methods
    public int getPoints() {
        return points;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
