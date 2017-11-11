package org.academiadecodigo.pacman.objects.fruit;

import org.academiadecodigo.pacman.grid.Position;

public interface Edible {

    int getPoints();

    boolean isEaten();

    void eat();

    Position getPosition();
}
