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
    private Position position;
    private Power power = null;
    private int points;

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

        int col = getPosition().getCol() + direction.getMoveCol();
        int row = getPosition().getRow() + direction.getMoveRow();

        Position newPosition = new Position(col, row);

        if (isWalkable(newPosition)) {
            setPosition(newPosition);
            return;
        } else {
            direction = changeDirection(direction);
        }
    }

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


    @Override
    public void kill(Movable movable) {

    }

    public void eat(Edible e) {
        points += e.getPoints();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public static Direction changeDirection(Direction direction) {

        int randomNumber = (int) (Math.random() * 2);

        if (randomNumber == 0) {

            return Direction.turnRight(direction);
        } else {
            return Direction.turnLeft(direction);
        }
    }

    public boolean isWalkable(Position position) {
        for (Position p : Representation.getWalkablePositions()) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }
}
