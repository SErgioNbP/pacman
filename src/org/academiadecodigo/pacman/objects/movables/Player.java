package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.TerminalPosition;
import org.academiadecodigo.pacman.Utils;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.fruit.Edible;
import org.academiadecodigo.pacman.objects.fruit.powers.Power;

import java.util.List;

public class Player implements Movable, Interactable {

    private Direction direction = Direction.LEFT;
    private Direction nextDirection = Direction.UP;
    private Position position;

    private List<Position> walkablePositions;
    private Power power = null;
    private int points;
    private boolean alive;

    public Player(Position position) {

        this.position = position;
        alive = true;
        points = 0;

        walkablePositions = Utils.path;
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

        if (!alive) {
            return;
        }

        int col = position.getCol() + nextDirection.getMoveCol();
        int row = position.getRow() + nextDirection.getMoveRow();

        Position newPosition = new Position(col, row);

        if (isWalkable(newPosition)) {
            position = newPosition;
            direction = nextDirection;
            return;
        }

        col = position.getCol() + direction.getMoveCol();
        row = position.getRow() + direction.getMoveRow();

        newPosition = new Position(col, row);

        if (isWalkable(newPosition)) {
            position = newPosition;

            return;
        }


    }

    @Override
    public void die() {
        alive = false;
    }

    public void eat(Edible e) {

        e.eat();
        points += e.getPoints();
    }

    public void setNextDirection(Direction direction) {
        nextDirection = direction;
    }

    public boolean isWalkable(Position position) {

        for (Position p : walkablePositions) {

            if (p.comparePos(position))
                return true;
        }
        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getPosition() {
        return position;
    }
}
