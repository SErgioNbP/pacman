package org.academiadecodigo.graphics;

import com.googlecode.lanterna.TerminalFacade;
import org.academiadecodigo.gameplay.Constants;

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
