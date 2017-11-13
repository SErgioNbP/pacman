package org.academiadecodigo.gameplay.grid;

public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean comparePos(Position pos) {

        if (pos.getCol() == this.col && pos.getRow() == this.row) {

            return true;
        }

        return false;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
