package org.academiadecodigo.pacman;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Movable;
import org.academiadecodigo.pacman.objects.movables.Player;

import java.util.LinkedList;
import java.util.List;


public class Game {

    //private List<GameObject> objects;
    //private List<Player> players;
    //private Player player;
    private Representation representation;
    private Ghost[] ghosts;

    public void init() {

        getGhosts();
        representation = new Representation();
        representation.init();


        Screen screen = TerminalFacade.createScreen();

        screen.setCursorPosition(null);

        screen.getTerminal().getTerminalSize().setColumns(Constants.GRID_COLS);
        screen.getTerminal().getTerminalSize().setRows(Constants.GRID_ROWS);

        while (true) {
            screen.clear();
            screen.startScreen();

            //FileHelper.generateLists();


            for (Ghost ghost : ghosts) {
                screen.putString(ghost.getPosition().getCol(), ghost.getPosition().getRow(), " ", Terminal.Color.WHITE, Terminal.Color.BLUE);
            }

            screen.refresh();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        for (Ghost ghost : ghosts) {

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

    public void getGhosts() {

        ghosts = new Ghost[5];

        for (int i = 0; i < 5; i++) {

            ghosts[i] = new Ghost(new Position(42, 7 + i));
        }

    }
}


