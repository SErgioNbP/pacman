package org.academiadecodigo.pacman.objects;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.grid.Representation;

import java.util.LinkedList;

public class ObjectFactory {

    private LinkedList<Position> edibles = Representation.walkablePositions;
    private GameObject[] objects = new GameObject[getTotalObjects()];

    public GameObject[] createObjects() {

        for (ObjectType type : ObjectType.values()) {

        }
    }

    public int getTotalObjects() {

        return objects.length;
    }
}
