package com.codecool.polishdraughts;

import java.util.Objects;

public class Pawn {
    String color;
    coordinatesPosition coordinates;

    public Pawn(String color, Integer row, Integer col) {
        this.color = color;
        this.coordinates = new coordinatesPosition(row, col);

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
        if (canAttack(fields, startingPosition)){
            return true;
        }
        // when white check if pawn can move diagonally one up
        if (Objects.equals(this.color, "white")) {
            if (startingPosition.col == fields.length - 1) {
                if (fields[startingPosition.row - 1][startingPosition.col - 1] == null) {
                    return true;
                }
            } else if (startingPosition.col == 0) {
                if (fields[startingPosition.row - 1][startingPosition.col + 1] == null) {
                    return true;
                }
            } else if (startingPosition.row - 1 >= 0 && startingPosition.col + 1 < fields.length) {
                if (fields[startingPosition.row - 1][startingPosition.col + 1] == null ||
                        fields[startingPosition.row - 1][startingPosition.col - 1] == null) {
                    return true;
                }
            }
            // when black check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if (startingPosition.col == fields.length - 1) {
                if (fields[startingPosition.row + 1][startingPosition.col - 1] == null) {
                    return true;
                }
            } else if (startingPosition.col == 0) {
                if (fields[startingPosition.row + 1][startingPosition.col + 1] == null) {
                    return true;
                }
            } else if (startingPosition.row + 1 < fields.length && startingPosition.col - 1 >= 0){
                if (fields[startingPosition.row + 1][startingPosition.col + 1] == null ||
                        fields[startingPosition.row + 1][startingPosition.col - 1] == null) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean canAttack(Pawn[][] fields, coordinatesPosition startingPosition) {
        if (startingPosition.row + 2 < fields.length && startingPosition.col + 2 < fields.length) {
            if (fields[startingPosition.row + 2][startingPosition.col + 2] == null) {
                if (fields[startingPosition.row + 1][startingPosition.col + 1] != null) {
                    if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col + 1].color, this.color)) {
                        return true;
                    }
                }
            }
        }
        if (startingPosition.row - 2 >= 0 && startingPosition.col + 2 < fields.length) {
            if (fields[startingPosition.row - 2][startingPosition.col + 2] == null) {
                if (fields[startingPosition.row - 1][startingPosition.col + 1] != null) {
                    if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col + 1].color, this.color)) {
                        return true;
                    }
                }
            }
        }
        if (startingPosition.row + 2 < fields.length && startingPosition.col - 2 >= 0) {
            if (fields[startingPosition.row + 2][startingPosition.col - 2] == null) {
                if (fields[startingPosition.row + 1][startingPosition.col - 1] != null) {
                    if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col - 1].color, this.color)) {
                        return true;
                    }
                }
            }
        }
        if (startingPosition.row - 2 >= 0 && startingPosition.col - 2 >= 0) {
            if (fields[startingPosition.row - 2][startingPosition.col - 2] == null) {
                if (fields[startingPosition.row - 1][startingPosition.col - 1] != null) {
                    if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col - 1].color, this.color)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validatePawnMove(Pawn[][] fields, coordinatesPosition moveToPosition) {
        coordinatesPosition startingPosition = this.coordinates;
        // check if movetoPosition is 2 tiles away and there's an opposing piece between
        if (validateAttackMove(fields, moveToPosition, startingPosition)) return true;


        // when black check if pawn can move diagonally one up
        if (Objects.equals(this.color, "white")) {
            if ((startingPosition.row - 1 == moveToPosition.row &&
                    (startingPosition.col - 1 == moveToPosition.col ||
                            startingPosition.col + 1 == moveToPosition.col) &&
                    fields[moveToPosition.row][moveToPosition.col] == null)) {
                return true;
            }
            // when white check if pawn can move diagonally one down
        } else if (Objects.equals(this.color, "black")) {
            if ((startingPosition.row + 1 == moveToPosition.row &&
                    (startingPosition.col - 1 == moveToPosition.col ||
                            startingPosition.col + 1 == moveToPosition.col) &&
                    fields[moveToPosition.row][moveToPosition.col] == null)) {
                return true;
            }

        }
        return false;
    }

    public boolean validateAttackMove(Pawn[][] fields, coordinatesPosition moveToPosition, coordinatesPosition startingPosition) {
        if ((startingPosition.row + 2 == moveToPosition.row && startingPosition.col + 2 == moveToPosition.col) &&
                fields[startingPosition.row + 1][startingPosition.col + 1] != null &&
                fields[startingPosition.row + 2][startingPosition.col + 2] == null) {
            if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col + 1].color, this.color)) {
                return true;
            }
        }
        if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col + 2 == moveToPosition.col) &&
                fields[startingPosition.row - 1][startingPosition.col + 1] != null &&
                fields[startingPosition.row - 2][startingPosition.col + 2] == null) {
            if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col + 1].color, this.color)) {
                return true;
            }
        }
        if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                fields[startingPosition.row - 1][startingPosition.col - 1] != null &&
                fields[startingPosition.row - 2][startingPosition.col - 2] == null) {
            if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col - 1].color, this.color)) {
                return true;
            }
        }
        if ((startingPosition.row + 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                fields[startingPosition.row + 1][startingPosition.col - 1] != null &&
                fields[startingPosition.row + 2][startingPosition.col - 2] == null) {
            if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col - 1].color, this.color)) {
                return true;
            }
        }
        return false;
    }
}
