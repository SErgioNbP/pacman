package org.academiadecodigo.gameplay.game_objects.fruit;

import org.academiadecodigo.gameplay.grid.Position;

public interface Edible {

    int getPoints();

    boolean isEaten();

    void eat();

    Position getPosition();
}
