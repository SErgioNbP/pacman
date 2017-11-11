package org.academiadecodigo.pacman.objects.movables;

import javafx.geometry.Pos;
import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

import java.util.List;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost implements Movable {

    private Direction direction;
    private Direction nextDirection = Direction.UP;
    private Position position;

    public Ghost(Position position) {
        this.position = position;
        direction = Direction.UP;
    }
    @Override
    public void move() {
/*
        int randomNumber = (int) (Math.random() * 50);
        if (isWalkable(newPosition)) {
            setPosition(newPosition);
            return;
        }

        direction = Direction.changeGhostDirection();
    */}

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

    public Position getPosition() {
        return position;
    }

    public void setPositionColRow(int col, int row){

        this.position = new Position(col, row);
    }
}
