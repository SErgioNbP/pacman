package org.academiadecodigo.keyboard;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.pacman.Game;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.objects.movables.Player;

/**
 * Created by codecadet on 12/11/17.
 */
public class KeyboardHandler implements Runnable{

    Screen screen;
    Player player;
    Game game;

    public KeyboardHandler (Screen screen, Player player, Game game){

        this.screen = screen;
        this.player = player;
        this.game = game;
    }


    @Override
    public void run() {

        while (true){

            Key key = screen.readInput();

            if (key != null) {

                if (key.getKind() == Key.Kind.ArrowRight) {
                    System.out.println("EEEEE");
                    player.setNextDirection(Direction.RIGHT);

                }
                if (key.getKind() == Key.Kind.ArrowLeft) {

                    System.out.println("EEEEE");
                    player.setNextDirection(Direction.RIGHT);
                    player.setNextDirection(Direction.LEFT);
                }
                if (key.getKind() == Key.Kind.ArrowDown) {

                    System.out.println("EEEEE");
                    player.setNextDirection(Direction.RIGHT);
                    player.setNextDirection(Direction.DOWN);
                }
                if (key.getKind() == Key.Kind.ArrowUp) {

                    System.out.println("EEEEE");
                    player.setNextDirection(Direction.RIGHT);
                    player.setNextDirection(Direction.UP);
                }
                if (key.getKind() == Key.Kind.Enter) {

                    System.out.println("EEEEE");
                    player.setNextDirection(Direction.RIGHT);
                    game.start();
                }

                if(key.getKind() == Key.Kind.Escape){
                    game.setSinglePlayerMode();
                }

            }

        }
    }
}
