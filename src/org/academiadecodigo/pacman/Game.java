package org.academiadecodigo.pacman;

import com.googlecode.lanterna.input.Key;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Grid;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.movables.Player;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private ObjectFactory factory;
    private GameObject[] objects;
    //private GameObject[] objects = factory.createObjects();
    private Grid grid;
    Player player;

    public void init() {

        grid = new Grid();
        grid.init();
        player = new Player();

        objects = new GameObject[]{player
        };

        grid.drawGrid(objects);

        start();
    }

    public void start() {

        while (true) {

            Key key = grid.getScreen().readInput();

            if (key != null) {

                if (key.getKind() == Key.Kind.ArrowRight) {

                    player.setDirection(Direction.RIGHT);
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.move();
        }
    }
}
