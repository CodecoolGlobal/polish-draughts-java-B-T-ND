package com.codecool.polishdraughts;

import java.util.Hashtable;
import java.util.Objects;

public class Board {
    Pawn[][] fields;

    private final Hashtable<String, String> translateBoardDict;

    public Board(int n) {
        fields = new Pawn[n][n];
        int numberOfPawns = 2 * n;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if ((x + y) % 2 == 1 && numberOfPawns > 0) {
                    fields[x][y] = new Pawn("black", x, y);
                    fields[n - x - 1][n - y - 1] = new Pawn("white", n - x - 1, n - y - 1);
                    numberOfPawns--;
                }
            }
        }
        translateBoardDict = new Hashtable<>();
        if (System.getProperty("os.name").equals("Mac OS X")) {
            translateBoardDict.put("null", ".");
            translateBoardDict.put("black", "⚫");
            translateBoardDict.put("white", "⚪");
        } else {
            translateBoardDict.put("null", ".");
            translateBoardDict.put("black", "⚪");
            translateBoardDict.put("white", "⚫");
        }
    }

    public void removePawn(int row, int col) {
        fields[row][col] = null;
    }

    @Override
    public String toString() {
        String osName = System.getProperty("os.name");
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // start board with 3 spaces
        StringBuilder boardToPrint = new StringBuilder("   ");

        // build letters for the columns
        for (int col = 0; col < fields.length; col++) {
            boardToPrint.append(abc.charAt(col)).append("  ");
        }
        boardToPrint.append("\n");
//         for loop build rows with their letters & change numbers 012 to .XO
        for (int row = 1; row <= fields.length; row++) {
            int rowIndex = row - 1;
            if (row < 10) {
                boardToPrint.append(row).append("  ");
            } else {
                boardToPrint.append(row).append(" ");
            }
            for (int col = 0; col < fields.length; col++) {
                if (fields[rowIndex][col] == null) {
                    boardToPrint.append(translateBoardDict.get("null")).append("  ");
                } else {
                    if (Objects.equals(fields[rowIndex][col].color, "black")) {
                        boardToPrint.append(translateBoardDict.get("black"));
                    } else if (Objects.equals(fields[rowIndex][col].color, "white")) {
                        boardToPrint.append(translateBoardDict.get("white"));
                    }
                    if (Objects.equals(osName, "Mac OS X")) {
                        boardToPrint.append(" ");
                    } else {
                        boardToPrint.append("  ");
                    }
                }
            }
            boardToPrint.append("\n");
        }
        return boardToPrint.toString();
    }

    public void movePawn(coordinatesPosition startPosition, coordinatesPosition endPosition) {
        if (Math.abs(startPosition.row - endPosition.row) == 2) {
            removePawn((startPosition.row + endPosition.row) / 2, (startPosition.col + endPosition.col) / 2);
        }
        Pawn currentPawn = fields[startPosition.row][startPosition.col];
        currentPawn.coordinates = endPosition;
        fields[endPosition.row][endPosition.col] = currentPawn;
        removePawn(startPosition.row, startPosition.col);
    }
}
