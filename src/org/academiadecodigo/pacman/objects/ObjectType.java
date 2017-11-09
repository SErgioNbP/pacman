package org.academiadecodigo.pacman.objects;

import com.googlecode.lanterna.terminal.Terminal;

public enum ObjectType {
    GHOST(Terminal.Color.BLUE, " "),
    PACMAN(Terminal.Color.YELLOW, " "),
    FRUIT(Terminal.Color.YELLOW, "."),
    POWERUP(Terminal.Color.RED, "");

    private Terminal.Color color;
    private String label;

    ObjectType(Terminal.Color color, String label) {
        this.color = color;
        this.label = label;
    }

    public Terminal.Color getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }
}
