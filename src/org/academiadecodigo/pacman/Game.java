package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.screens.Representation;

import com.googlecode.lanterna.input.Key;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Position;

import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Player;
import org.academiadecodigo.server.Client;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private Client client;
    ExecutorService executorService;

    private Representation representation;

    private List<Ghost> gameGhosts;
    private List<Apple> gameApples;
    private List<Fruit> gameFruits;
    private List<Player> gamePlayers;

    private Player player1;
    private Player player2;

    public void init() {

        Utils.generateLists();

        this.client = new Client(this);
        representation = new Representation();
        representation.init();

        gameGhosts = Utils.createGhosts();
        gameApples = Utils.createApples();
        gameFruits = Utils.createFruits();
        gamePlayers = Utils.createPlayers();

        player1 = gamePlayers.get(0);
        player2 = gamePlayers.get(1);

        draw();

        this.executorService = Executors.newFixedThreadPool(5);

        start();
    }

    public void start() {

        executorService.submit(client);

        while (true) {

            Key key = representation.getScreen().readInput();

            if (key != null) {

                if (key.getKind() == Key.Kind.ArrowRight) {

                    player1.setNextDirection(Direction.RIGHT);
                }
                if (key.getKind() == Key.Kind.ArrowLeft) {

                    player1.setNextDirection(Direction.LEFT);
                }
                if (key.getKind() == Key.Kind.ArrowDown) {

                    player1.setNextDirection(Direction.DOWN);
                }
                if (key.getKind() == Key.Kind.ArrowUp) {

                    player1.setNextDirection(Direction.UP);
                }
            }

            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player1.move();
            eatFruits();
            checkDeaths();
            draw();
        }
    }

    public void draw() {

        representation.clear();

        for (Position pos : Utils.walls) {

            representation.drawWall(pos);
        }

        for (Apple apple : gameApples) {

            if (!apple.isEaten()) {
                representation.drawApples(apple.getPosition());
            }
        }

        for (Fruit fruit : gameFruits) {

            if (!fruit.isEaten()) {
                representation.drawFruit(fruit.getPosition());
            }
        }

        if (player1.isAlive()) {
            representation.drawPlayer(player1.getPosition());
        }

        for (Ghost ghost : gameGhosts) {

            if (ghost.isAlive()) {
                representation.drawGhost(ghost.getPosition());
            }

            representation.refresh();
        }
    }

    public void updateGhostsPosition(String ghostsPosition) {

        String[] ghostPositions = ghostsPosition.split("\n");

        for (int i = 0; i < ghostPositions.length; i++) {

            String[] strings = ghostPositions[i].split(" ");

            if (strings[0].equals("Ghost")) {

                gameGhosts.get(i).setPositionColRow(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            }
        }
    }

    public void eatFruits() {

        for (Fruit fruit : gameFruits) {

            if (!fruit.isEaten()) {

                if (player1.getPosition().comparePos(fruit.getPosition())) {

                    player1.eat(fruit);
                    client.setStringToSend("Fruit " + player1.getPosition().getCol() + " " + player1.getPosition().getRow());
                }
            }
        }

        for (Apple apple : gameApples) {

            if (!apple.isEaten()) {

                if (player1.getPosition().comparePos(apple.getPosition())) {

                    player1.eat(apple);
                    client.setStringToSend("Apple " + player1.getPosition().getCol() + " " + player1.getPosition().getRow());
                }

            }
        }
    }

    public void checkDeaths() {

        for (Ghost ghost : gameGhosts) {

            if (player1.getPosition().comparePos(ghost.getPosition())) {
                // if (player1.hasPowerUp()){

                player1.die();
            }
        }
    }
}





