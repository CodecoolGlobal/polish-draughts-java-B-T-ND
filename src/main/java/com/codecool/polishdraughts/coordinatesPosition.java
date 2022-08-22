package com.codecool.polishdraughts;

public class coordinatesPosition {
    Integer x;
    Integer y;

    public coordinatesPosition(Integer x, Integer y) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "coordinatesPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
