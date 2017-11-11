package org.academiadecodigo.pacman.screens;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.grid.Position;

public class Representation {

    private Screen screen;

    public void init() {

        screen = TerminalFacade.createScreen();

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

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

}
