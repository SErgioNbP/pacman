package org.academiadecodigo.pacman.objects.fruit;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Edible extends GameObject {

    //TODO EDIT THIS CLASS
    private int points;

    public Edible(Position position, Terminal.Color color) {
        super(position, color);
    }

    public int getPoints() {
        return points;
    }
}
