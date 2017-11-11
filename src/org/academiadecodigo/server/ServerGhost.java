package org.academiadecodigo.server;

import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.ObjectType;

/**
 * Created by codecadet on 10/11/17.
 */
public class ServerGhost {

    private Direction direction;
    private Direction nextDirection = Direction.UP;
    private Position position;

    public ServerGhost(Position position) {

        this.position = position;
        direction = Direction.UP;
    }

    public void move() {

        int randomNumber = (int) (Math.random() * 50);

        if (randomNumber < 10){
            nextDirection = Direction.turnBack(direction);
        }

        if (randomNumber >= 10 && randomNumber < 35){
            nextDirection = Direction.turnLeft(direction);
        }

        if (randomNumber >= 35){
            nextDirection = Direction.turnRight(direction);
        }

        int col = position.getCol() + nextDirection.getMoveCol();
        int row = position.getRow() + nextDirection.getMoveRow();

        Position newPosition = new Position(col, row);


        if (isWalkable(newPosition)) {
            position =  newPosition;
            direction = nextDirection;
            return;
        }

        col = position.getCol() + direction.getMoveCol();
        row = position.getRow() + direction.getMoveRow();

        newPosition = new Position(col, row);

        if (isWalkable(newPosition)) {
            position = newPosition;
        }
    }


    public boolean isWalkable(Position position) {

        /*for (Position p : FileHelper.path) {

            if (p.comparePos(position))
                return true;
        }
        return false;*/ return true;
    }

    public Position getPosition() {
        return position;
    }
}
