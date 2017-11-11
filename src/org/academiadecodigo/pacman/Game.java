package org.academiadecodigo.pacman;

import com.googlecode.lanterna.input.Key;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Edible;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Movable;
import org.academiadecodigo.pacman.objects.movables.Player;
import org.academiadecodigo.server.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Game {

    //private List<GameObject> objects;
    //private List<Player> players;
    private Player player;
    private List<Ghost> ghosts;
    private List<Edible> edibles;
    private List<Position> walls;

    private Representation representation;
    private Client client;
    ExecutorService executorService;

    public void init() {

        client = new Client(this);

        ghosts = new LinkedList<>();

        for (int i = 0; i < 5; i++) {

            ghosts.add(new Ghost(new Position(25, 13)));
        }

        walls = FileHelper.getAllPositions();

        this.client = new Client(this);
        representation = new Representation();
        representation.init();

        /*
        objects = new LinkedList<>();
        objects.addAll(ObjectFactory.createGameObjects());

        players = new LinkedList<>();
        players.add(player);
        */
        player = new Player(new Position(42, 7));

        draw();
        //representation.drawPlayer(player);
        this.executorService = Executors.newFixedThreadPool(5);

        start();
    }



    public void start() {

        executorService.submit(client);

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


            player.move();

            draw();
/*
            for (GameObject gameObject : objects) {
                if (gameObject instanceof Movable) {
                    ((Movable) gameObject).move();
                }
            }


            for (GameObject gameObject : objects) {
                player.kill(objects);
                if(gameObject instanceof Fruit) {
                    player.eat(gameObject);
                }
            }

            representation.drawGrid(FileHelper.allPositions);
            //representation.drawGrid(objects, player);
        }*/
        }
    }

    public void draw() {

        representation.clear();

        representation.drawPlayer(player);

        for (Ghost ghost : ghosts) {

            representation.drawGhost(ghost);
        }

        //representation.drawGrid(walls);

        representation.refresh();
    }

    public void updateGhostsPosition(String ghostsPosition) {

        String[] ghostPositions = ghostsPosition.split("\n");

        for (int i = 0; i < ghostPositions.length; i++) {

            String[] strings = ghostPositions[i].split(" ");

            if (strings[0].equals("Ghost")) {

                ghosts.get(i).setPosition(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            }
        }
    }
}
