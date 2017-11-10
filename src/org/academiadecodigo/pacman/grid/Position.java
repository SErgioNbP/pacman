package org.academiadecodigo.pacman.grid;

public class Position {

    private int col;
    private int row;
    private char c;

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
}
