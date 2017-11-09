package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.terminal.Terminal;
import javafx.geometry.Pos;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    private Direction direction = Direction.UP;
    private Direction nextDirection = Direction.UP;


    public Ghost(Position position, ObjectType type) {
        super(position, type);
        direction = Direction.UP;
    }

    @Override
    public void move() {
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
    }

    @Override
    public void kill(GameObject[] gameObjects) {

    }

    public boolean isWalkable(Position position) {
        for (Position p : Representation.getWalkablePositions()) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }
}
