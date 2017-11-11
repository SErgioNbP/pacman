package org.academiadecodigo.pacman;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.util.List;

public class Game {

    private Representation representation;

    private List<Ghost> gameGhosts;
    private List<Apple> gamApples;
    private List<Fruit> gameFruits;

    public void init() {

        representation = new Representation();
        representation.init();

        gameGhosts = FileHelper.createGhosts();
        gamApples = FileHelper.createApples();
        gameFruits = FileHelper.createFruits();

        Screen screen = TerminalFacade.createScreen();

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        while (true) {
            screen.clear();
            screen.startScreen();

            //FileHelper.generateLists();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            screen.refresh();
        }
        /*
        objects = new LinkedList<>();
        objects.addAll(ObjectFactory.createGameObjects());

        players = new LinkedList<>();
        player = new Player(new Position(42, 7));
        players.add(player);

        representation.drawGrid(objects, player);

        start();
        */
    }

    public void updatePositions(String newPositions) {

        for (Ghost ghost : gameGhosts) {

            FileHelper.decode(newPositions, ghost);
        }
    }


    /*
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

            representation.drawGrid(FileHelper.allPositions);
            //representation.drawGrid(objects, player);
        }
    }
    */


}


