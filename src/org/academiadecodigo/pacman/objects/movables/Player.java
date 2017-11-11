package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.TerminalPosition;
import org.academiadecodigo.pacman.Utils;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.fruit.powers.Power;

import java.util.List;

/**
 * Created by codecadet on 05/11/17.
 */
public class Player implements Movable, Interactable {

    private Direction direction = Direction.LEFT;
    private Direction nextDirection = Direction.UP;
    private Position position;

    private List<Position> walkablePositions;
    private Power power = null;
    private int points;
    private boolean alive = true;

    public Player(Position position) {

        this.position = position;
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

        System.out.println(walkablePositions.size());

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
        //nextDirection = Direction.changeDirection(direction);
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
    public void kill(List<GameObject> gameObjects) {
       /* for (GameObject gameObject : gameObjects) {

            if (position.comparePos(gameObject.getPosition()) && gameObject instanceof Ghost) {
                alive = false;
            }
        }*/
    }

    public void eat(GameObject e) {
        Fruit fruit = (Fruit) e;
        if (position.comparePos(fruit.getPosition())) {
            points += fruit.getPoints();
            fruit.eat();
        }
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

    public boolean isKilled() {
        return !alive;
    }

    public Position getPosition() {
        return position;
    }
}
