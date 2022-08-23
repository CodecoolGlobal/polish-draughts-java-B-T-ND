package com.codecool.polishdraughts;

import java.util.Objects;

public class Pawn {
    String color;
    coordinatesPosition coordinates;

    public Pawn(String color, Integer x, Integer y) {
        this.color = color;
        this.coordinates = new coordinatesPosition(x, y);

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

    public boolean canPawnMoveAnywhere(Pawn[][] fields) {
        coordinatesPosition startingPosition = this.coordinates;
        // when white check if pawn can move diagonally one up
        if (Objects.equals(this.color, "white")) {
            if (fields[startingPosition.x - 1][startingPosition.y + 1] == null ||
                fields[startingPosition.x - 1][startingPosition.y - 1] == null) {
                return true;
            }
            // when black check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if (fields[startingPosition.x + 1][startingPosition.y + 1] == null ||
                fields[startingPosition.x + 1][startingPosition.y - 1] == null) {
                return true;
            }
            // check if movetoPosition is 2 tiles away and these's an opposing piece between
        } else if (
                ((fields[startingPosition.x + 2][startingPosition.y + 2] == null) &&
                !Objects.equals(fields[startingPosition.x + 1][startingPosition.y + 1].color, this.color))
        ||
                ((fields[startingPosition.x - 2][startingPosition.y + 2] == null) &&
                !Objects.equals(fields[startingPosition.x - 1][startingPosition.y + 1].color, this.color))
        ||
                ((fields[startingPosition.x + 2][startingPosition.y - 2] == null) &&
                !Objects.equals(fields[startingPosition.x + 1][startingPosition.y - 1].color, this.color))
        ||
                ((fields[startingPosition.x - 2][startingPosition.y - 2] == null) &&
                !Objects.equals(fields[startingPosition.x - 1][startingPosition.y - 1].color, this.color))
        ) {
            return true;
        }
        return false;
    }

    public boolean validatePawnMove(Pawn[][] fields, coordinatesPosition moveToPosition) {
        coordinatesPosition startingPosition = this.coordinates;

        // when white check if pawn can move diagonally one up
        if (Objects.equals(this.color, "white")) {
            if ((startingPosition.x - 1 == moveToPosition.x &&
                    (startingPosition.y - 1 == moveToPosition.y ||
                            startingPosition.y + 1 == moveToPosition.y) &&
                    fields[moveToPosition.x][moveToPosition.y] == null)) {
                return true;
            }
            // when black check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if ((startingPosition.x + 1 == moveToPosition.x &&
                    (startingPosition.y - 1 == moveToPosition.y ||
                            startingPosition.y + 1 == moveToPosition.y) &&
                    fields[moveToPosition.x][moveToPosition.y] == null)) {
                return true;
            }
            // check if movetoPosition is 2 tiles away and these's an opposing piece between
        } else if ((startingPosition.x + 2 == moveToPosition.x && startingPosition.y + 2 == moveToPosition.y) &&
                !Objects.equals(fields[startingPosition.x + 1][startingPosition.y + 1].color, this.color)) {
            return true;
        } else if ((startingPosition.x - 2 == moveToPosition.x && startingPosition.y + 2 == moveToPosition.y) &&
                !Objects.equals(fields[startingPosition.x - 1][startingPosition.y + 1].color, this.color)) {
            return true;
        } else if ((startingPosition.x + 2 == moveToPosition.x && startingPosition.y - 2 == moveToPosition.y) &&
                !Objects.equals(fields[startingPosition.x + 1][startingPosition.y - 1].color, this.color)) {
            return true;
        } else if ((startingPosition.x - 2 == moveToPosition.x && startingPosition.y - 2 == moveToPosition.y) &&
                !Objects.equals(fields[startingPosition.x - 1][startingPosition.y - 1].color, this.color)) {
            return true;
        }
        return false;
    }
}
