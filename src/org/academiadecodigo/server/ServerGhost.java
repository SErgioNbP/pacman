package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Utils;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;

public class ServerGhost {

    private Direction direction;
    private Direction nextDirection = Direction.UP;
    private Position position;

    public ServerGhost(Position position) {

        this.position = position;
        direction = Direction.UP;
    }

    public void move() {

        // TODO make ghosts move more coherently
        int randomNumber = (int) (Math.random() * 50);

        if (randomNumber < 5){
            nextDirection = Direction.turnBack(direction);
        }

        else if (randomNumber < 15){
            nextDirection = Direction.turnLeft(direction);
        }

        else if (randomNumber < 25){
            nextDirection = Direction.turnRight(direction);
        }

        // TODO change this to call method setPosition(col, row);
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

        for (Position p : Utils.path) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int col, int row) {
        position.setCol(col);
        position.setRow(row);
    }
}
