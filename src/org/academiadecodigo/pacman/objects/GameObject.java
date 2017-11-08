package org.academiadecodigo.pacman.objects;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;

public abstract class GameObject {

    protected Position position;
    private Terminal.Color color;

    public GameObject(Position position, Terminal.Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Terminal.Color getColor() {
        return color;
    }
}
