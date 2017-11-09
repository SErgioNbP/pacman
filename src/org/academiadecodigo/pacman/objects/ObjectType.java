package org.academiadecodigo.pacman.objects;

import com.googlecode.lanterna.terminal.Terminal;

public enum ObjectType {
    GHOST(Terminal.Color.BLUE, " "),
    FRUIT(Terminal.Color.BLACK, "."),
    POWERUP(Terminal.Color.BLACK, "");

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