package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.objects.movables.Enemy;
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
    private List<Position> positionList;

    private Player player;
    private Enemy enemy;

    public void init() {

        Utils.generateLists();

        this.client = new Client(this);
        representation = new Representation();
        representation.init();

        gameGhosts = Utils.createGhosts();
        gameApples = Utils.createApples();
        gameFruits = Utils.createFruits();
        gamePlayers = Utils.createPlayers();

        player = gamePlayers.get(0);
        enemy = new Enemy(new Position(42, 7));

        draw();

        this.executorService = Executors.newFixedThreadPool(5);

        start();
    }

    public void start() {

        client.sendServer("START");
        client.startListening();
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

        if (enemy.isAlive()) {
            representation.drawEnemy(enemy.getPosition());
        }

        if (player.isAlive()) {
            client.sendServer("Enemy " + player.getPosition().getCol() + " " + player.getPosition().getRow());
            representation.drawPlayer(player.getPosition());
        }

        for (Ghost ghost : gameGhosts) {

            if (ghost.isAlive()) {
                representation.drawGhost(ghost.getPosition());
            }

        }
        representation.refresh();

    }

    public void updatePosition(String positions) {

        String[] typePosition = positions.split("\n");
        System.out.println(typePosition.length);

        String[] type = typePosition[0].split(" ");

        switch (type[0]) {

            case "Ghost":

                for (int i = 0; i < typePosition.length; i++) {

                    String[] strings = typePosition[i].split(" ");

                    gameGhosts.get(i).setPositionColRow(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                }

                break;

            case "Fruit":

                for (Fruit fruit : gameFruits) {

                    if (fruit.getPosition().comparePos(new Position(Integer.parseInt(type[1]), Integer.parseInt(type[2])))) {
                        fruit.eat();
                    }
                }

                break;

            case "Apple":

                for (Apple apple : gameApples) {

                    if (apple.getPosition().comparePos(new Position(Integer.parseInt(type[1]), Integer.parseInt(type[2])))) {
                        apple.eat();
                    }
                }

                break;

            case "Enemy":

                enemy.setPosition(Integer.parseInt(type[1]), Integer.parseInt(type[2]));
                break;

            case "FirstPos":
                enemy = new Enemy(new Position(Integer.parseInt(type[1]), Integer.parseInt(type[2])));
                break;
            default:
                break;
        }
    }

    public void eatFruits() {

        for (Fruit fruit : gameFruits) {

            if (!fruit.isEaten()) {

                if (player.getPosition().comparePos(fruit.getPosition())) {

                    player.eat(fruit);
                    client.sendServer("Fruit " + player.getPosition().getCol() + " " + player.getPosition().getRow());
                }
            }
        }

        for (Apple apple : gameApples) {

            if (!apple.isEaten()) {

                if (player.getPosition().comparePos(apple.getPosition())) {

                    player.eat(apple);
                    client.sendServer("Apple " + player.getPosition().getCol() + " " + player.getPosition().getRow());
                }

            }
        }
    }

    public void checkDeaths() {

        for (Ghost ghost : gameGhosts) {

            if (player.getPosition().comparePos(ghost.getPosition())) {
                // if (player.hasPowerUp()){

                player.die();
            }
        }
    }
}





