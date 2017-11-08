package org.academiadecodigo.pacman.objects;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Grid;
import org.academiadecodigo.pacman.grid.Position;

public abstract class GameObject {

    private Position position;

    public void draw(Grid grid, Position pos){
        grid.drawPosition(pos, Terminal.Color.GREEN);
    }

    public Position getPosition() {
        return position;
    }
}
