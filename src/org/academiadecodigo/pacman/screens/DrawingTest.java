package org.academiadecodigo.pacman.screens;

import com.googlecode.lanterna.TerminalFacade;
import org.academiadecodigo.pacman.Constants;

public class DrawingTest {

    public static void main(String[] args) {

        com.googlecode.lanterna.screen.Screen testScreen = TerminalFacade.createScreen();
        testScreen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        testScreen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        testScreen.startScreen();

        Graphics graphics = new Graphics(testScreen);

        graphics.drawScreen(GraphicsType.INITIAL_SCREEN);
    }
}
