package org.academiadecodigo.pacman.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.movables.Player;

import java.util.List;

import static org.academiadecodigo.pacman.objects.ObjectType.POWERUP;

public class Representation {

    private Screen screen;
    private ScreenWriter screenWriter;
    private String[] mapRow;

    public void init() {

        screen = TerminalFacade.createScreen();
        screenWriter = new ScreenWriter(screen);

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        screen.startScreen();

        FileHelper.generateLists();
    }

    //public void drawGrid(List<Position> positions, Player player) {
    public void drawGrid(List<GameObject> gameObjects, Player player) {

        screen.clear();

        drawWalls();

        drawObjects(gameObjects);

        drawPlayers(player);

       // drawEverything(positions);

        screen.refresh();
    }
/*
    private void drawEverything(List<Position> positions) {

        for (Position pos : positions) {

            int col = pos.getCol();
            int row = pos.getRow();
            Character label = FileHelper.currentChar;
            Terminal.Color stringColor;
            Terminal.Color color;

            if (FileHelper.walls.contains(pos)) {
                color = Terminal.Color.WHITE;

            } else if (FileHelper.players.contains(pos)) {
                color = Terminal.Color.YELLOW;

            } else if (FileHelper.ghosts.contains(pos)) {
                color = Terminal.Color.BLUE;

            } else {
                color = Terminal.Color.BLACK;
            }

            if (FileHelper.apples.contains(pos)) {
                stringColor = Terminal.Color.RED;

            } else if (FileHelper.points.contains(pos)) {
                stringColor = Terminal.Color.YELLOW;

            } else if (FileHelper.movables.contains(pos)) {
                stringColor = color;
            }

        }
    }
*/
    public Screen getScreen() {
        return screen;
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

    private void drawPlayers(Player player) {

        if (!player.isKilled()) {
            screen.putString(player.getPosition().getCol(), player.getPosition().getRow(), " ", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        }
    }
}
