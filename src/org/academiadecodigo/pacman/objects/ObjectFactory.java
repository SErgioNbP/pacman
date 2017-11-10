package org.academiadecodigo.pacman.objects;

import org.academiadecodigo.pacman.Constants;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.util.LinkedList;
import java.util.List;

public class ObjectFactory {

    private LinkedList<Position> edibles = Representation.walkablePositions;
    private static List<GameObject> objects;

    public int getTotalObjects() {

        return objects.size();
    }

    public static List<GameObject> createGameObjects() {

        objects = new LinkedList<>();
        for (int i = 0; i < Representation.walkablePositions.size(); i++) {
            objects.add(new Fruit(Representation.walkablePositions.get(i)));
        }

        for (int i = 0; i < Constants.NUM_GHOSTS; i++) {
            objects.add(new Ghost(Constants.GHOSTS[i]));
        }

        for (int i = 0; i < Constants.NUM_POWERS; i++) {
            objects.add(new Apple(Constants.POWERUPS[i]));
        }
        return objects;
    }


}
