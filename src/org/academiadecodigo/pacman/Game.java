package org.academiadecodigo.pacman;

import org.academiadecodigo.keyboard.KeyboardHandler;
import org.academiadecodigo.pacman.objects.fruit.powers.PowerType;
import org.academiadecodigo.pacman.objects.movables.Enemy;
import org.academiadecodigo.pacman.screens.Representation;

import org.academiadecodigo.pacman.grid.Position;

import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Player;
import org.academiadecodigo.pacman.screens.ScreenType;
import org.academiadecodigo.server.Client;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private Client client;
    private ExecutorService executorService;

    private Representation representation;
    private Timer timer;

    private List<Ghost> gameGhosts;
    private List<Apple> gameApples;
    private List<Fruit> gameFruits;
    private List<Player> gamePlayers;

    private Player player;
    private Enemy enemy;
    private int enemyScore;

    private boolean ready = false;
    private boolean playerReady = false;

    private KeyboardHandler keyboardHandler;

    public void init() {

        Utils.generateLists();

        this.client = new Client(this);
        representation = new Representation();
        representation.init();

        gameGhosts = Utils.createGhosts();
        gameApples = Utils.createApples();
        gameFruits = Utils.createFruits();
        gamePlayers = Utils.createPlayers();

        client.startListening();
        client.sendServer("whatever");

        player = gamePlayers.get(0);
        enemy = new Enemy(new Position(42, 7));

        this.executorService = Executors.newCachedThreadPool();

        keyboardHandler = new KeyboardHandler(representation.getScreen(), player, this);

        timer = new Timer();

        executorService.submit(keyboardHandler);

        representation.clear();

        representation.getCurrentScreen().drawScreen(ScreenType.INITIAL_SCREEN);

    }

    public void start() {
        playerReady = true;
        client.sendServer("START");
        GameThread gameThread = new GameThread();
        timer.scheduleAtFixedRate(gameThread, 0, 200);
    }

    public boolean playerReady() {
        return playerReady;
    }

    class GameThread extends TimerTask {

        @Override
        public void run() {
            System.out.println(ready);
            if (!ready) {
                return;
            }
            player.move();
            eatFruits();
            checkDeaths();
            draw();
        }
    }

    public void draw() {

        representation.clear();

        if (!player.isAlive()) {
            gameOverScreen();
            return;
        }

        if (noMoreEdibles()) {
            representation.drawWinningScreen();
            return;
        }

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

        representation.drawScore(player.getScore(), enemyScore);

        if (player.getPower() != null) {
            representation.drawPowerUp(player.getPower().toString());
        }

        representation.refresh();

    }

    public synchronized void updatePosition(String positions) {

        String[] messageLines = positions.split("\n");

        String[] words = messageLines[0].split(" ");

        String type = words[0];

        switch (type) {

            case "Ghost":

                for (Ghost ghost : gameGhosts) {

                    String[] strings = messageLines[gameGhosts.indexOf(ghost)].split(" ");

                    ghost.setPosition(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));

                }

                break;

            case "Fruit":

                for (Fruit fruit : gameFruits) {

                    if (fruit.getPosition().comparePos(new Position(Integer.parseInt(words[1]), Integer.parseInt(words[2])))) {
                        fruit.eat();
                    }
                }

                break;

            case "Apple":

                for (Apple apple : gameApples) {

                    if (apple.getPosition().comparePos(new Position(Integer.parseInt(words[1]), Integer.parseInt(words[2])))) {
                        apple.eat();
                    }
                }
                break;

            case "Enemy":
                enemy.setPosition(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                break;

            case "FirstPos":
                enemy = new Enemy(new Position(Integer.parseInt(words[1]), Integer.parseInt(words[2])));
                break;

            case "Score":
                enemyScore = Integer.parseInt(words[1]);
                break;
            case "GAMESTART":
                ready = true;
                System.out.println("game is ready");

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

                    player.setPower(apple.getPowerType());
                    System.out.println(player.getPower().toString());

                    player.eat(apple);
                    client.sendServer("Apple " + player.getPosition().getCol() + " " + player.getPosition().getRow());
                }

            }
        }
        client.sendServer("Score " + player.getScore());
    }

    public void checkDeaths() {

        for (Ghost ghost : gameGhosts) {

            if (player.getPosition().comparePos(ghost.getPosition())) {

                if (player.getPower() != null) {

                    if (player.getPower().equals(PowerType.EDIBLE_GHOSTS)) {
                        ghost.die();
                        player.setPower(null);

                    } else if (player.getPower().equals(PowerType.IMMUNE)) {

                        player.setPower(null);
                        continue;

                    }
                }

                player.die();
            }
        }
    }

    public void gameOverScreen() {

        representation.clear();

        representation.drawLosingScreen();

        representation.refresh();
    }

    public boolean noMoreEdibles() {

        for (Fruit fruit : gameFruits) {

            if (!fruit.isEaten()) {
                return false;
            }
        }

        for (Apple apple : gameApples) {

            if (!apple.isEaten()) {
                return false;
            }
        }

        return true;
    }
}






