package org.academiadecodigo.pacman.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Player;

import java.util.LinkedList;
import java.util.List;

import static org.academiadecodigo.pacman.objects.ObjectType.FRUIT;
import static org.academiadecodigo.pacman.objects.ObjectType.POWERUP;

public class Representation {

    private Screen screen;
    private ScreenWriter screenWriter;
    private int cols = Constants.GRID_COLS;
    private int rows = Constants.GRID_ROWS;
    private String[] mapRow;
    public static LinkedList<Position> walkablePositions = new LinkedList<>();

    public void init() {

        screen = TerminalFacade.createScreen();

        screen.getTerminal().getTerminalSize().setColumns(cols);
        screen.getTerminal().getTerminalSize().setRows(rows);

        screen.setCursorPosition(null);

        screenWriter = new ScreenWriter(screen);

        screen.startScreen();

        mapRow = FileHelper.readFromFile().split("\\n");

        createWalkablePositions();

    }

    public void drawGrid(List<GameObject> gameObjects, Player player) {

        screen.clear();

        drawWalls();


        for (GameObject gameObject : gameObjects) {

            if(gameObject.getType().equals(FRUIT)) {
                Fruit fruit = (Fruit)gameObject;
                if(fruit.isEaten()){
                    continue;
                }
            }

            int col = gameObject.getPosition().getCol();
            int row = gameObject.getPosition().getRow();
            String label = gameObject.getType().getLabel();
            Terminal.Color color = gameObject.getType().getColor();
            Terminal.Color stringColor;

            if (gameObject.getType().equals(POWERUP)) {
                stringColor = Terminal.Color.RED;

            } else {
                stringColor = Terminal.Color.YELLOW;
            }

            screen.putString(col, row, label, stringColor, color);
        }
        if (!player.isKilled()) {
            screen.putString(player.getPosition().getCol(), player.getPosition().getRow(), " ", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        }

        screen.refresh();
    }

    public Screen getScreen() {
        return screen;
    }

    public void createWalkablePositions() {
        for (int i = 0; i < mapRow.length; i++) {

            char[] mapColumn = mapRow[i].toCharArray();

            for (int j = 0; j < mapColumn.length; j++) {

                if (mapColumn[j] == '0') {
                    walkablePositions.add(new Position(j, i));
                }
            }
        }
    }


    public void drawWalls() {

        for (int i = 0; i < mapRow.length; i++) {

            char[] mapColumn = mapRow[i].toCharArray();

            for (int j = 0; j < mapColumn.length; j++) {

                if (mapColumn[j] == '1') {
                    screenWriter.drawString(j, i, " ");
                    screenWriter.setBackgroundColor(Terminal.Color.WHITE);
                }
            }
        }
    }
}
