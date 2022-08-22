package com.codecool.polishdraughts;

public class Pawn{
    String color;
    coordinatesPosition coordinates;
    public Pawn(String color, Integer x, Integer y) {
        this.color = color;
        this.coordinates = new coordinatesPosition(x,y);

    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color='" + color + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
