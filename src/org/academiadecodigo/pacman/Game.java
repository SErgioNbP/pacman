package org.academiadecodigo.pacman;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Movable;
import org.academiadecodigo.pacman.objects.movables.Player;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private GameObject[] objects;
    private Representation representation;
    private Player player;
    private Ghost ghost;
    private Ghost ghost2;

    public void init() {

        representation = new Representation();
        representation.init();
        player = new Player(new Position(42, 7), Terminal.Color.GREEN);
        ghost = new Ghost(new Position(42, 8), Terminal.Color.RED);
        ghost2 = new Ghost(new Position(22, 13), Terminal.Color.RED);

        objects = new GameObject[]{
                player,
                ghost,
                ghost2
        };

        representation.drawGrid(objects);

        start();
    }

    public void start() {

        while (true) {

            Key key = representation.getScreen().readInput();

            if (key != null) {

                if (key.getKind() == Key.Kind.ArrowRight) {

                    player.setNextDirection(Direction.RIGHT);
                }
                if (key.getKind() == Key.Kind.ArrowLeft) {

                    player.setNextDirection(Direction.LEFT);
                }
                if (key.getKind() == Key.Kind.ArrowDown) {

                    player.setNextDirection(Direction.DOWN);
                }
                if (key.getKind() == Key.Kind.ArrowUp) {

                    player.setNextDirection(Direction.UP);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (GameObject gameObject : objects) {
                if (gameObject instanceof Movable) {
                    ((Movable) gameObject).move();
                }
            }

            for (GameObject gameObject : objects) {
                if (gameObject instanceof Player) {
                    ((Player) gameObject).kill(objects);
                }
            }
            representation.drawGrid(objects);
        }
    }
}
