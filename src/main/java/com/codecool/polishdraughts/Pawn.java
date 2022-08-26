package com.codecool.polishdraughts;

import java.util.Objects;

public class Pawn {
    String color;

    boolean isCrowned;
    coordinatesPosition coordinates;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Pawn(String color, Integer row, Integer col) {
        setColor(color);
        this.isCrowned = false;
        this.coordinates = new coordinatesPosition(row, col);

    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color='" + getColor() + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    public boolean canPawnMoveOneInDirection(Pawn[][] fields, coordinatesPosition vector) {
        coordinatesPosition startingPosition = this.coordinates;
        if ((startingPosition.col + vector.col < fields.length && startingPosition.col + vector.col >= 0) &&
                (startingPosition.row + vector.row < fields.length && startingPosition.row + vector.row >= 0)) {
            return fields[startingPosition.row + vector.row][startingPosition.col + vector.col] == null;
        }
        return false;
    }

    public boolean canPawnMoveAnywhere(Pawn[][] fields) {
        coordinatesPosition startingPosition = this.coordinates;
        if (canAttack(fields, startingPosition)) {
            return true;
        }
        // when white check if pawn can move diagonally one up
        if (Objects.equals(this.getColor(), "white")) {
            if (canPawnMoveOneInDirection(fields, new coordinatesPosition(-1, -1))) {
                return true;
            } else if (canPawnMoveOneInDirection(fields, new coordinatesPosition(-1, 1))) {
                return true;
            }
        }
        // when black check if pawn can move diagonally one down
        else if (Objects.equals(this.getColor(), "black")) {
            if (canPawnMoveOneInDirection(fields, new coordinatesPosition(1, -1))) {
                return true;
            } else if (canPawnMoveOneInDirection(fields, new coordinatesPosition(1, 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean canAttackInDirectionOf(Pawn[][] fields, coordinatesPosition vector) {
        boolean isCrowned = this.isCrowned;
        String color = this.getColor();
        coordinatesPosition startingPosition = this.coordinates;
        coordinatesPosition normalisedVector = new coordinatesPosition(vector.row / Math.abs(vector.row), vector.col / Math.abs(vector.col));
        // if on board
        if (startingPosition.row + vector.row < fields.length && startingPosition.row + vector.row >= 0 &&
                startingPosition.col + vector.col < fields.length && startingPosition.col + vector.col >= 0) {
            // if moveto tile is null
            if (fields[startingPosition.row + vector.row][startingPosition.col + vector.col] == null) {
                for (int i = 1; i < Math.abs(vector.row); i++) {
                    Pawn iThFieldBetweenTheTwoPositions = fields[startingPosition.row + (i * normalisedVector.row)][startingPosition.col + (i * normalisedVector.col)];
                    if (iThFieldBetweenTheTwoPositions != null) {
                        // if not null, check if the color is the same, if yes, the pawn cant move there
                        if (Objects.equals(iThFieldBetweenTheTwoPositions.getColor(), color)) {
                            return false;
                        }
                    } else if (!isCrowned) {
                        if (Math.abs(vector.row) == 2) {
                            // if it's null and the given vector is 2, return false, bc there has to be one
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean canAttack(Pawn[][] fields, coordinatesPosition startingPosition) {
        if (canAttackInDirectionOf(fields, new coordinatesPosition(2, 2))) {
            return true;
        }
        if (canAttackInDirectionOf(fields, new coordinatesPosition(-2, 2))) {
            return true;
        }
        if (canAttackInDirectionOf(fields, new coordinatesPosition(2, -2))) {
            return true;
        }
        if (canAttackInDirectionOf(fields, new coordinatesPosition(-2, -2))) {
            return true;
        }
        return false;
    }

    public boolean validatePawnMove(Pawn[][] fields, coordinatesPosition moveToPosition) {
        coordinatesPosition startingPosition = this.coordinates;
        // check if movetoPosition is 2 tiles away and there's an opposing piece between
        if (validateAttackMove(fields, moveToPosition, startingPosition)) return true;


        // when black check if pawn can move diagonally one up
        if (Objects.equals(this.getColor(), "white")) {
            if ((startingPosition.row - 1 == moveToPosition.row &&
                    (startingPosition.col - 1 == moveToPosition.col ||
                            startingPosition.col + 1 == moveToPosition.col) &&
                    fields[moveToPosition.row][moveToPosition.col] == null)) {
                return true;
            }
            // when white check if pawn can move diagonally one down
        } else if (Objects.equals(this.getColor(), "black")) {
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
            if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col + 1].getColor(), this.getColor())) {
                return true;
            }
        }
        if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col + 2 == moveToPosition.col) &&
                fields[startingPosition.row - 1][startingPosition.col + 1] != null &&
                fields[startingPosition.row - 2][startingPosition.col + 2] == null) {
            if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col + 1].getColor(), this.getColor())) {
                return true;
            }
        }
        if ((startingPosition.row - 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                fields[startingPosition.row - 1][startingPosition.col - 1] != null &&
                fields[startingPosition.row - 2][startingPosition.col - 2] == null) {
            if (!Objects.equals(fields[startingPosition.row - 1][startingPosition.col - 1].getColor(), this.getColor())) {
                return true;
            }
        }
        if ((startingPosition.row + 2 == moveToPosition.row && startingPosition.col - 2 == moveToPosition.col) &&
                fields[startingPosition.row + 1][startingPosition.col - 1] != null &&
                fields[startingPosition.row + 2][startingPosition.col - 2] == null) {
            if (!Objects.equals(fields[startingPosition.row + 1][startingPosition.col - 1].getColor(), this.getColor())) {
                return true;
            }
        }
        return false;
    }
}
