package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    private Position position;

    public Ghost(Position position) {
        this.position = position;
    }

    @Override
    public void move() {

    }

    @Override
    public void kill(Movable movable) {

    }

}
