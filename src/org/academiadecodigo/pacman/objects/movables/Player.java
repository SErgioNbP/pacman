package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Edible;
import org.academiadecodigo.pacman.objects.fruit.powers.Power;

/**
 * Created by codecadet on 05/11/17.
 */
public class Player extends GameObject implements Movable, Interactable {

    private Direction direction = Direction.UP;
    private Direction nextDirection = Direction.UP;
    private Position position;
    private Power power = null;
    private int points;
    private boolean alive = true;

    public Player(Position position, Terminal.Color color) {
        super(position, color);
        points = 0;
    }

    @Override
    public Result keyboardInteraction(Key key) {
        return null;
    }

    @Override
    public void onEnterFocus(FocusChangeDirection focusChangeDirection) {

    }

    @Override
    public void onLeaveFocus(FocusChangeDirection focusChangeDirection) {

    }

    @Override
    public TerminalPosition getHotspot() {
        return null;
    }


    @Override
    public void move() {

        if(!alive){
            return;
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
        nextDirection = Direction.changeDirection(direction);
    }

    /*
    public void moveUp() {
        Position newPosition = new Position(getPosition().getCol(), getPosition().getRow() - 1);
        if (isWalkable(newPosition)) {
            setPosition(newPosition);
        }
    }

    public void moveDown() {
        Position newPosition = new Position(getPosition().getCol(), getPosition().getRow() + 1);
        if (isWalkable(newPosition)) {
            setPosition(newPosition);
        }
    }

    public void moveLeft() {
        Position newPosition = new Position(getPosition().getCol() - 1, getPosition().getRow());
        if (isWalkable(newPosition)) {
            setPosition(newPosition);
        }
    }

    public void moveRight() {
        Position newPosition = new Position(getPosition().getCol() + 1, getPosition().getRow());
        if (isWalkable(newPosition)) {

            setPosition(newPosition);
        }
    }
    */

    @Override
    public void kill(GameObject gameObject) {
        if (getPosition().comparePos(gameObject.getPosition())) {
            alive = false;
            System.out.println("player dead");
        }
    }

    public void eat(Edible e) {
        if (getPosition().comparePos(e.getPosition())) {
            points += e.getPoints();
        }
    }

    public void setNextDirection(Direction direction) {
        nextDirection = direction;
    }


    public boolean isWalkable(Position position) {
        for (Position p : Representation.getWalkablePositions()) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }
}
