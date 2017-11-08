package org.academiadecodigo.pacman.objects.fruit.powers;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.fruit.Edible;

public class p1 extends Edible implements Power {
    public p1(Position position, Terminal.Color color) {
        super(position, color);
    }
}
