package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.objects.GameObject;

import java.util.List;

/**
 * Created by codecadet on 05/11/17.
 */
public interface Movable {

    void move();

    void kill(List<GameObject> gameObjects);
}
