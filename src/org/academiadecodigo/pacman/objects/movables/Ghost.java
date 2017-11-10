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
/*
        int randomNumber = (int) (Math.random() * 50);

        if (randomNumber < 20){
            nextDirection = Direction.changeGhostDirection();
        }

        int col = getPosition().getCol() + nextDirection.getMoveCol();
        int row = getPosition().getRow() + nextDirection.getMoveRow();

        Position newPosition = new Position(col, row);


        if (isWalkable(newPosition)) {
            setPosition(newPosition);
            direction = nextDirection;
            return;
        }

        col = getPosition().getCol() + direction.getMoveCol();
        row = getPosition().getRow() + direction.getMoveRow();

        newPosition = new Position(col, row);

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

    public void setPositionColRow(int col, int row){

        this.position = new Position(col, row);
    }
}
