package org.academiadecodigo.pacman.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Edible;

import java.io.*;

/**
 * Created by codecadet on 05/11/17.
 */
public class Grid {

    private int cols;
    private int rows;
    private Position[] walkablePositions;
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
        // screenWriter.setBackgroundColor(Terminal.Color.WHITE);
        //screenWriter.setForegroundColor(Terminal.Color.BLUE);

        screen.startScreen();
    }

    public String readFromFile() {

        String path = "Map";
        String result = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                result += line + "\n";
                line = reader.readLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
/*
    public void draw(GameObject[] objects) {

        for (GameObject object : objects) {

            if (object instanceof Edible) {

                // screenWriter.drawString(object.getCol(), object.getPos().getRow(), object.toString());

            } else {

                // screenWriter.drawString(object.getPos().getCol(), object.getPos().getRow(), object.toString());
            }

        }
    }
*/

    public void drawGrid() {

        screen.clear();

        String[] rows = readFromFile().split("\\n");

        for (int i = 0; i < rows.length; i++) {

            char[] row = rows[i].toCharArray();

            for (int j = 0; j < row.length; j++) {
/*
                if (row[j] == '|' || row[j] == '_') {

                    screenWriter.drawString(j, i, " ");
                    screenWriter.setBackgroundColor(Terminal.Color.GREEN);
*/
                if (!(row[j] == '1')) {

                    screenWriter.drawString(j, i, " ");
                    screenWriter.setBackgroundColor(Terminal.Color.WHITE);
                }
            }
        }

        screen.refresh();
    }
}
