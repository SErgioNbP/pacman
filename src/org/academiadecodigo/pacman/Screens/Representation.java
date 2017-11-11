package org.academiadecodigo.pacman.Screens;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Player;

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
