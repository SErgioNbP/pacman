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

        for (Position pos : Representation.getWalkablePositions()) {

            if (pos.comparePos(new Position(col, row))) {
                setPosition(pos);
            }
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
}
