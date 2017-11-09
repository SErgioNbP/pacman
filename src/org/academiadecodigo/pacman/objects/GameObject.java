package org.academiadecodigo.pacman.objects;

import org.academiadecodigo.pacman.grid.Position;

public abstract class GameObject {

    protected Position position;
    protected ObjectType type;

    public GameObject(Position position, ObjectType type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ObjectType getType() {
        return type;
    }
}
