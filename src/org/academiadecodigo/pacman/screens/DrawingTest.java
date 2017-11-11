package org.academiadecodigo.pacman.screens;

import com.googlecode.lanterna.TerminalFacade;
import org.academiadecodigo.pacman.Constants;

/**
 * Created by codecadet on 11/11/2017.
 */
public class DrawingTest {

    public static void main(String[] args) {

        com.googlecode.lanterna.screen.Screen testScreen = TerminalFacade.createScreen();
        testScreen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        testScreen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        testScreen.startScreen();

        Screen screen = new Screen(testScreen);

        screen.drawScreen(ScreenType.WINNING_FINAL_SCREEN);
    }

}
