package org.academiadecodigo.pacman;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.TerminalPosition;

/**
 * Created by codecadet on 05/11/17.
 */
public class Player implements Movable, Interactable {

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
    public void kill() {

    }
}
