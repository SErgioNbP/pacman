package org.academiadecodigo.keyboard;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.pacman.Game;
import org.academiadecodigo.pacman.grid.Direction;
import org.academiadecodigo.pacman.objects.movables.Player;

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

                if (key.getKind() == Key.Kind.Enter) {
                    game.start();
                }
            }
        }
    }
}