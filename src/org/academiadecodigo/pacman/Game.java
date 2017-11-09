package org.academiadecodigo.pacman;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.movables.Player;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private ObjectFactory factory;
    private GameObject[] objects;
    //private GameObject[] objects = factory.createObjects();
    private Representation representation;
    Player player;

    public void init() {

        representation = new Representation();
        representation.init();
        player = new Player(new Position(42, 7), Terminal.Color.GREEN);

        objects = new GameObject[]{player
        };

        representation.drawGrid(player);

        start();
    }

    public void start() {


        while (true) {

            Key key = representation.getScreen().readInput();

            if (key != null) {

                if (key.getKind() == Key.Kind.ArrowRight) {

                    player.setDirection(Direction.RIGHT);
                }
                if (key.getKind() == Key.Kind.ArrowLeft) {

                    player.setDirection(Direction.LEFT);
                }
                if (key.getKind() == Key.Kind.ArrowDown) {

                    player.setDirection(Direction.DOWN);
                }
                if (key.getKind() == Key.Kind.ArrowUp) {

                    player.setDirection(Direction.UP);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.move();
            representation.drawGrid(player);
        }
    }
}
