package org.academiadecodigo.gameplay;
import org.academiadecodigo.keyboard.KeyboardHandler;
import org.academiadecodigo.gameplay.game_objects.fruit.powers.PowerType;
import org.academiadecodigo.gameplay.game_objects.movables.Enemy;
import org.academiadecodigo.graphics.Representation;
import org.academiadecodigo.gameplay.grid.Position;
import org.academiadecodigo.gameplay.game_objects.fruit.Fruit;
import org.academiadecodigo.gameplay.game_objects.movables.Ghost;
import org.academiadecodigo.gameplay.game_objects.movables.Player;
import org.academiadecodigo.graphics.GraphicsType;
import org.academiadecodigo.server.Client;
import org.academiadecodigo.gameplay.game_objects.fruit.powers.Apple;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private ExecutorService executorService;
    private Client client;

    private KeyboardHandler keyboardHandler;
    private Representation representation;
    private Timer timer;

    private List<Ghost> gameGhosts;
    private List<Fruit> gameFruits;
    private List<Apple> gameApples;

    private Player player;
    private Enemy enemy;
    private int enemyScore;

    void init() {

        Utils.generateLists();

        this.client = new Client(this);

        representation = new Representation();
        representation.init();

        gameGhosts = Utils.createGhosts();
        gameApples = Utils.createApples();
        gameFruits = Utils.createFruits();

        //client.sendServer("START");
        //client.startListening();

        player = new Player(new Position(10, 10));
        enemy = new Enemy(new Position(10, 10));

        this.executorService = Executors.newFixedThreadPool(5);
        keyboardHandler = new KeyboardHandler(representation.getScreen(), player, this);
        timer = new Timer();

        executorService.submit(keyboardHandler);

        representation.clear();
        representation.getCurrentGraphics().drawScreen(GraphicsType.INITIAL_SCREEN);
    }

    public void start() {

        GameThread gameThread = new GameThread();
        timer.scheduleAtFixedRate(gameThread, 0, 200);
    }

    public void sendReadyMessage() {
        client.sendServer("START");
        client.startListening();
    }

    class GameThread extends TimerTask {

        @Override
        public void run() {

            player.move();
            eatFruits();
            checkDeaths();
            draw();
        }
    }

    private void draw() {

        representation.clear();

        if (gameOver()) {

            checkWinner();
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
        int numLines = messageLines.length;

        switch (type) {

            case "Ghost":

                if (numLines < gameGhosts.size()) {
                    gameGhosts.remove(0);
                }

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

            case "Score":

                enemyScore = Integer.parseInt(words[1]);
                break;

            case "GameStart":

                start();

            default:
                break;
        }
    }

    private void eatFruits() {

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
                    player.eat(apple);
                    client.sendServer("Apple " + player.getPosition().getCol() + " " + player.getPosition().getRow());
                }
            }
        }

        client.sendServer("Score " + player.getScore());
    }

    private void checkDeaths() {

        for (Ghost ghost : gameGhosts) {

            if (player.getPosition().comparePos(ghost.getPosition())) {

                if(!ghost.isAlive()){
                    return;
                }

                if (player.getPower() != null) {

                    if (player.getPower().equals(PowerType.DOUBLE_POINTS)) {
                        player.die();
                    }

                    if (player.getPower().equals(PowerType.EDIBLE_GHOSTS)) {
                        ghost.die();
                        player.givePoints(ghost.getPoints());
                        player.setPower(null);
                        client.sendServer("DeadGhost " + player.getPosition().getCol() + " " + player.getPosition().getRow());

                    } else if (player.getPower().equals(PowerType.IMMUNE)) {
                        player.setPower(null);
                    }

                } else {
                    player.die();
                }
            }
        }
    }

    private boolean noMoreEdibles() {

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

    private boolean gameOver() {

        return (!player.isAlive()) || noMoreEdibles();
    }

    private void checkWinner() {

        representation.clear();

        if (enemyScore > player.getScore() || !player.isAlive()) {
            representation.getCurrentGraphics().drawScreen(GraphicsType.LOSING_FINAL_SCREEN);

        } else {
            representation.getCurrentGraphics().drawScreen(GraphicsType.WINNING_FINAL_SCREEN);
        }

        representation.refresh();
    }
}