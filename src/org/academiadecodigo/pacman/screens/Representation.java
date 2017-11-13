package org.academiadecodigo.pacman.screens;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.grid.Position;

public class Representation {

    private Screen screen;
    private Graphics currentGraphics;

    public void init() {

        screen = TerminalFacade.createScreen();

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        currentGraphics = new Graphics(screen);

        screen.startScreen();
    }

    public void drawWall(Position pos){
        screen.putString(pos.getCol(), pos.getRow(), " ", Terminal.Color.WHITE, Terminal.Color.WHITE);
    }

    public void drawFruit(Position pos){
        screen.putString(pos.getCol(), pos.getRow(), ".", Terminal.Color.YELLOW, Terminal.Color.BLACK);
    }

    public void drawApples(Position pos) {
        screen.putString(pos.getCol(), pos.getRow(), "", Terminal.Color.RED, Terminal.Color.BLACK);
    }

    public void drawPlayer(Position pos){
        screen.putString(pos.getCol(), pos.getRow(), " ", Terminal.Color.WHITE, Terminal.Color.YELLOW);
    }
    public void drawEnemy(Position position) {
        screen.putString(position.getCol(), position.getRow(), " ", Terminal.Color.WHITE, Terminal.Color.GREEN);
    }

    public void drawGhost(Position pos){
        screen.putString(pos.getCol(), pos.getRow(), " ", Terminal.Color.WHITE, Terminal.Color.BLUE);
    }

    public void clear(){
        screen.clear();
    }

    public void refresh(){
        screen.refresh();
    }

    public Screen getScreen() {
        return screen;
    }

    public Graphics getCurrentGraphics() {
        return currentGraphics;
    }

    public void drawScore(int playerScore, int enemyScore) {
        screen.putString(58, 5, "Player 1: " + playerScore, Terminal.Color.GREEN, Terminal.Color.BLACK);
        screen.putString(58, 10, "Player 2: " + enemyScore, Terminal.Color.GREEN, Terminal.Color.BLACK);
    }

    public void drawPowerUp(String player) {
        screen.putString(60, 7, "PowerUp:".toUpperCase(), Terminal.Color.GREEN, Terminal.Color.BLACK);
        screen.putString(57, 8, player, Terminal.Color.GREEN, Terminal.Color.BLACK);
    }
}
