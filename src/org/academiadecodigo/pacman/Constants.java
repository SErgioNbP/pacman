package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.util.List;

public final class Constants {

    //TODO FILL THIS CLASS
    public static final int GRID_COLS = 55;
    public static final int GRID_ROWS = 30;

    public static int NUM_GHOSTS = FileHelper.ghosts.size();
    public static int NUM_POWERS = FileHelper.apples.size();

    public static List<Position> ghosts;
    public static Ghost[] GHOSTS = new Ghost[NUM_GHOSTS];



}
