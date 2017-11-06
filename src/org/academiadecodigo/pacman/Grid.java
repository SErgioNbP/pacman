package org.academiadecodigo.pacman;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 05/11/17.
 */
public class Grid {

    private int cols;
    private int rows;
    private Screen screen;
    private ScreenWriter screenWriter;

    public Grid() {

        cols = 55;
        rows = 30;
    }

    public void init() {

        screen = TerminalFacade.createScreen();

        screen.getTerminal().getTerminalSize().setColumns(cols);
        screen.getTerminal().getTerminalSize().setRows(rows);

        screen.getTerminal().setCursorVisible(false);
        screen.setCursorPosition(null);

        screenWriter = new ScreenWriter(screen);
        screenWriter.setBackgroundColor(Terminal.Color.BLACK);
        screenWriter.setForegroundColor(Terminal.Color.BLUE);

        screen.startScreen();
    }
/*
    public void draw(GameObjects[] objects) {

        for (GameObject object : objects) {

            if (GameObject instanceof Edibles) {

                screenWriter.drawString(object.getPos().getCol(), object.getPos().getRow(), object.toString());

            } else {

                screenWriter.drawString(object.getPos().getCol(), object.getPos().getRow(), object.toString());
            }

        }
    }
    */
}
