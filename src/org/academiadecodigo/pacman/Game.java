package org.academiadecodigo.pacman;

import com.googlecode.lanterna.input.Key;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectFactory;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Movable;
import org.academiadecodigo.pacman.objects.movables.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 05/11/17.
 */
public class Game {

    private List<GameObject> objects;
    private List<Player> players;
    private Player player;
    private Representation representation;

    public void init() {

        representation = new Representation();
        representation.init();

        objects = new LinkedList<>();
        objects.addAll(ObjectFactory.createGameObjects());

        players = new LinkedList<>();
        player = new Player(new Position(42, 7));
        players.add(player);

        representation.drawGrid(objects, player);

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

            player.move();

            for (GameObject gameObject : objects) {
                player.kill(objects);
                if(gameObject instanceof Fruit) {
                    player.eat(gameObject);
                }
            }

            representation.drawGrid(objects, player);
        }
    }
}

