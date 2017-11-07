package org.academiadecodigo.pacman.objects.movables;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Grid;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Edible;
import org.academiadecodigo.pacman.objects.fruit.powers.Power;
import org.academiadecodigo.pacman.representation.Representation;

/**
 * Created by codecadet on 05/11/17.
 */
public class Player extends GameObject implements Movable, Interactable {

    private Direction direction;
    private Grid grid;
    private Position position;
    private Power power = null;
    private int points;

    public Player(Grid grid){
        points = 0;
        this.grid = grid;
        position = new Position(10, 10);
        //direction = direction.randomDirection();
    }

    public void draw(){
        grid.draw(position, Terminal.Color.GREEN);
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

    }

    @Override
    public void kill(Movable movable) {

    }

    public void eat(Edible e) {
        points += e.getPoints();
    }
}
