package org.academiadecodigo.gameplay.objects.fruit;

import org.academiadecodigo.gameplay.grid.Position;

public interface Edible {

    int getPoints();

    boolean isEaten();

    void eat();

    Position getPosition();
}
