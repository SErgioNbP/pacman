package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Grid;
import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private GameObject[] objects;
    private Grid grid;

    public void start() {

        grid = new Grid();
        grid.init();
        grid.draw();
    }
}
