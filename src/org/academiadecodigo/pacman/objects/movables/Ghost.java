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

    public Position getPosition() {
        return position;
    }

    public void setPosition(int col, int row){

        position.setCol(col);
        position.setRow(row);
    }
}
