package org.academiadecodigo.pacman.objects.fruit.powers;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.GameObject;
import org.academiadecodigo.pacman.objects.ObjectType;

public class p1 extends GameObject implements Power {

    //Constructor
    public p1(Position position) {
        super(position, ObjectType.POWERUP);
    }
}
