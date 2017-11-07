package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Grid;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.movables.Player;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private ObjectFactory factory;
    //private GameObject[] objects = factory.createObjects();
    private Grid grid;
    Player player;

    public void start() {

        grid = new Grid();
        grid.init();
        grid.drawGrid();
        player = new Player(grid);
        player.draw();
    }
}
