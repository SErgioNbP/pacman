package org.academiadecodigo.pacman.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.objects.GameObject;

import java.util.LinkedList;

/**
 * Created by codecadet on 05/11/17.
 */
public class Grid {

    private Screen screen;
    private ScreenWriter screenWriter;
    private int cols;
    private int rows;
    public static LinkedList<Position> walkablePositions = new LinkedList<>();

    public Grid() {

        cols = 55;
        rows = 30;
    }

    public void init() {

        screen = TerminalFacade.createScreen();

        screen.getTerminal().getTerminalSize().setColumns(cols);
        screen.getTerminal().getTerminalSize().setRows(rows);

        //screen.getTerminal().setCursorVisible(false);
        //screen.setCursorPosition(null);

        screenWriter = new ScreenWriter(screen);

        screen.startScreen();

    }

    public void drawGrid(GameObject[] objects) {

        screen.clear();

        String[] rows = FileHelper.readFromFile().split("\\n");

        for (int i = 0; i < rows.length; i++) {

            char[] row = rows[i].toCharArray();

            for (int j = 0; j < row.length; j++) {

                if (!(row[j] == '1')) {

                    walkablePositions.add(new Position(j, i));
                    screenWriter.drawString(j, i, " ");
                    screenWriter.setBackgroundColor(Terminal.Color.WHITE);
                }
            }
        }
/*
        for (GameObject gameObject : objects){
            screenWriter.drawString(gameObject.getPosition().getCol(), gameObject.getPosition().getRow()," ");
            screenWriter.setForegroundColor(Terminal.Color.GREEN);
        }
*/
        screen.refresh();
    }

    public static LinkedList<Position> getWalkablePositions() {
        return walkablePositions;
    }

    public Position getWalkablePos(int col, int row) {

        Position pos = new Position(col, row);
        if (walkablePositions.contains(pos)) {
            return pos;
        }

        return null;
    }

    public Screen getScreen() {
        return screen;
    }

    public ScreenWriter getScreenWriter() {
        return screenWriter;
    }

    /*
    void clear() {
    }
*/

}
