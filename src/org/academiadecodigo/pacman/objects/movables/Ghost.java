package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    private Position position;

    public Ghost(Position position, Terminal.Color color) {
        super(position, color);
    }

    @Override
    public void move() {

    }

    @Override
    public void kill(Movable movable) {

    }

}
