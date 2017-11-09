package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.terminal.Terminal;
import javafx.geometry.Pos;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Ghost extends GameObject implements Movable {

    private Direction direction = Direction.UP;

    public Ghost(Position position, Terminal.Color color) {
        super(position, color);
        direction = Direction.UP;
    }

    @Override
    public void move() {


        int col = getPosition().getCol() + direction.getMoveCol();
        int row = getPosition().getRow() + direction.getMoveRow();

        Position newPosition = new Position(col, row);

        if (isWalkable(newPosition)) {
            setPosition(newPosition);
            return;
        }
        direction = Direction.changeDirection(direction);
    }

    //TODO EDIT THIS METHOD
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
