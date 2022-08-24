package com.codecool.polishdraughts;

public class coordinatesPosition {
    Integer row;
    Integer col;

    public coordinatesPosition(Integer row, Integer col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "coordinatesPosition{" +
                "x=" + row +
                ", y=" + col +
                '}';
    }
}
