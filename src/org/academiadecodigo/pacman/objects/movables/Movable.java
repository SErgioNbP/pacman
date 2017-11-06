package org.academiadecodigo.pacman.objects.movables;

/**
 * Created by codecadet on 05/11/17.
 */
public interface Movable {

    void move();

    void kill(Movable movable);
}
