package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    //TODO EDIT THIS CLASS
    private Direction direction;

    public Ghost(Position position, Terminal.Color color) {
        super(position, color);
        direction = Direction.UP;
    }

    @Override
    public void move() {

    }

    @Override
    public void kill(Movable movable) {

    }

}
