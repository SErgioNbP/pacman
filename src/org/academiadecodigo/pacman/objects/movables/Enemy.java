package org.academiadecodigo.pacman.objects.movables;

import org.academiadecodigo.pacman.grid.Position;


public class Enemy implements Movable {

    private Position position;
    private boolean alive;

    public Enemy(Position position){
        this.position = position;
        alive = true;
    }

   public Position getPosition(){
        return position;
   }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void move() {

    }

    public void die(){
       alive = false;
    }

    public void setPosition(int col, int row) {
       position.setCol(col);
       position.setRow(row);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
