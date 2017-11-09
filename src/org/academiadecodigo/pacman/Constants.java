package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;

public final class Constants {

    //TODO FILL THIS CLASS
    public static final int GRID_COLS = 55;
    public static final int GRID_ROWS = 30;

    public static int NUM_GHOSTS = 3;
    public static int NUM_POWERS = 4;

    public static Position[] GHOSTS = new Position[] {
            new Position(23, 13),
            new Position(25, 13),
            new Position(27, 13)
    };

    public static Position[] POWERUPS = new Position[] {
            new Position(2, 2),
            new Position(2, 26),
            new Position(52, 2),
            new Position(52, 26),
    };

}
