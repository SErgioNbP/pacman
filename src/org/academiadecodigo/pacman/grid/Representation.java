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

import java.util.List;

import static org.academiadecodigo.pacman.objects.ObjectType.FRUIT;
import static org.academiadecodigo.pacman.objects.ObjectType.POWERUP;

public class Representation {

    private Screen screen;
    //private ScreenWriter screenWriter;

    public void init() {

        screen = TerminalFacade.createScreen();
        //screenWriter = new ScreenWriter(screen);

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        screen.startScreen();

        FileHelper.generateLists();

        drawGrid(FileHelper.allPositions);
    }

    public void drawGrid(List<Position> positions) {

        screen.clear();

        drawEverything(positions);

        screen.refresh();
    }

    private void drawEverything(List<Position> positions) {

        for (Position pos : positions) {

            int col = pos.getCol();
            int row = pos.getRow();
            String label = " ";
            Terminal.Color stringColor = Terminal.Color.WHITE;
            Terminal.Color color = Terminal.Color.WHITE;

            if (FileHelper.players.contains(pos)) {
                color = Terminal.Color.YELLOW;
                stringColor = color;

            } else if (FileHelper.ghosts.contains(pos)) {
                color = Terminal.Color.BLUE;
                stringColor = color;

            } else if (FileHelper.apples.contains(pos)) {
                color = Terminal.Color.BLACK;
                stringColor = Terminal.Color.RED;
                label = "";

            } else if (FileHelper.points.contains(pos)) {
                color = Terminal.Color.BLACK;
                stringColor = Terminal.Color.YELLOW;
                label = ".";
            }

            screen.putString(col, row, label, stringColor, color);
        }
    }

    public Screen getScreen() {
        return screen;
    }

    /*
    public void drawGrid(List<GameObject> gameObjects, Player player) {

        screen.clear();

        drawWalls();

        drawObjects(gameObjects);

        drawPlayers(player);

        screen.refresh();
    }


    public void drawWalls() {

        for (Position position : FileHelper.walls) {

            screenWriter.drawString(position.getCol(), position.getRow(), " ");
            screenWriter.setBackgroundColor(Terminal.Color.WHITE);
        }
    }


    public void drawObjects(List<GameObject> gameObjects) {

        for (GameObject gameObject : gameObjects) {

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
    }

    // TODO THIS LOGIC SHOULD BE IN GAME CLASS

    private void drawPlayers(Player player) {

        if (!player.isKilled()) {
            screen.putString(player.getPosition().getCol(), player.getPosition().getRow(), " ", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        }
    }
    */
}
