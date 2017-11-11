package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

import java.util.List;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    private Direction direction;
    private Direction nextDirection = Direction.UP;

    public Ghost(Position position) {
        super(position, ObjectType.GHOST);
        direction = Direction.UP;
    }

    @Override
    public void move() {

    }

    @Override
    public void kill(List<GameObject> gameObjects) {

    }

    public boolean isWalkable(Position position) {
        for (Position p : FileHelper.path) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }
}
