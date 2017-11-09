package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.objects.GameObject;

/**
 * Created by codecadet on 05/11/17.
 */
public interface Movable {

    void move();

    void kill(GameObject gameObject);
}
