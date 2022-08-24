package com.codecool.polishdraughts;

import java.util.Objects;

public class Pawn {
    String color;
    coordinatesPosition coordinates;

    public Pawn(String color, Integer row, Integer col) {
        this.color = color;
        this.coordinates = new coordinatesPosition(row, col);

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
            if (fields[startingPosition.row - 1][startingPosition.col + 1] == null ||
                fields[startingPosition.row - 1][startingPosition.col - 1] == null) {
                return true;
            }
            // when black check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if (fields[startingPosition.row + 1][startingPosition.col + 1] == null ||
                fields[startingPosition.row + 1][startingPosition.col - 1] == null) {
                return true;
            }
            // check if movetoPosition is 2 tiles away and these's an opposing piece between
        } else if (
                ((fields[startingPosition.row + 2][startingPosition.col + 2] == null) &&
                !Objects.equals(fields[startingPosition.row + 1][startingPosition.col + 1].color, this.color))
        ||
                ((fields[startingPosition.row - 2][startingPosition.col + 2] == null) &&
                !Objects.equals(fields[startingPosition.row - 1][startingPosition.col + 1].color, this.color))
        ||
                ((fields[startingPosition.row + 2][startingPosition.col - 2] == null) &&
                !Objects.equals(fields[startingPosition.row + 1][startingPosition.col - 1].color, this.color))
        ||
                ((fields[startingPosition.row - 2][startingPosition.col - 2] == null) &&
                !Objects.equals(fields[startingPosition.row - 1][startingPosition.col - 1].color, this.color))
        ) {
            return true;
        }
        return false;
    }

    public boolean validatePawnMove(Pawn[][] fields, coordinatesPosition moveToPosition) {
        coordinatesPosition startingPosition = this.coordinates;

        // when white check if pawn can move diagonally one up
        if (Objects.equals(this.color, "white")) {
            if ((startingPosition.row - 1 == moveToPosition.row &&
                    (startingPosition.col - 1 == moveToPosition.col ||
                            startingPosition.col + 1 == moveToPosition.col) &&
                    fields[moveToPosition.row][moveToPosition.col] == null)) {
                return true;
            }
            // when black check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if ((startingPosition.row + 1 == moveToPosition.row &&
                    (startingPosition.col - 1 == moveToPosition.col ||
                            startingPosition.col + 1 == moveToPosition.col) &&
                    fields[moveToPosition.row][moveToPosition.col] == null)) {
                return true;
            }
            // check if movetoPosition is 2 tiles away and these's an opposing piece between
        } else if ((startingPosition.row + 2 == moveToPosition.row && startingPosition.col + 2 == moveToPosition.col) &&
                !Objects.equals(fields[startingPosition.row + 1][startingPosition.col + 1].color, this.color)) {
            return true;
        } else if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col + 2 == moveToPosition.col) &&
                !Objects.equals(fields[startingPosition.row - 1][startingPosition.col + 1].color, this.color)) {
            return true;
        } else if ((startingPosition.row + 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                !Objects.equals(fields[startingPosition.row + 1][startingPosition.col - 1].color, this.color)) {
            return true;
        } else if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                !Objects.equals(fields[startingPosition.row - 1][startingPosition.col - 1].color, this.color)) {
            return true;
        }
        return false;
    }
}
