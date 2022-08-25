package com.codecool.polishdraughts;

import java.util.Hashtable;
import java.util.Objects;

public class Board {
    Pawn[][] fields;

    private final Hashtable<String, String> translateBoardDict;

    public Board(int n, int mode) {
        fields = new Pawn[n][n];
        if (mode == 0) {
            // default
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
        } else if (mode == 1) {
            //multiple takedown case
            fields[0][0] = new Pawn("white", 0, 0);
            fields[1][1] = new Pawn("black", 1, 1);
            fields[3][3] = new Pawn("black", 3, 3);
            fields[5][5] = new Pawn("black", 5, 5);
            fields[5][3] = new Pawn("black", 5, 3);
            fields[3][5] = new Pawn("black", 3, 5);
            fields[2][6] = new Pawn("black", 2, 6);
//            fields[9][9] = new Pawn("black", 9, 9);

        } else if (mode == 2) {
            // winning case 1
            fields[4][4] = new Pawn("black", 4, 4);
            fields[5][5] = new Pawn("white", 5, 5);

        } else if (mode == 3) {
            // winning case 2
            fields[4][0] = new Pawn("black", 4, 0);
            fields[4][2] = new Pawn("black", 4, 2);
            fields[4][4] = new Pawn("black", 4, 4);
            fields[4][6] = new Pawn("black", 4, 6);
            fields[4][8] = new Pawn("black", 4, 8);
            fields[3][1] = new Pawn("black", 3, 1);
            fields[3][3] = new Pawn("black", 3, 3);
            fields[3][5] = new Pawn("black", 3, 5);
            fields[3][7] = new Pawn("black", 3, 7);
            fields[3][9] = new Pawn("black", 3, 9);

            fields[5][1] = new Pawn("white", 5, 1);
            fields[5][3] = new Pawn("white", 5, 3);
            fields[5][5] = new Pawn("white", 5, 5);
            fields[5][7] = new Pawn("white", 5, 7);
            fields[5][9] = new Pawn("white", 5, 9);
            fields[6][0] = new Pawn("white", 6, 0);
            fields[6][2] = new Pawn("white", 6, 2);
            fields[6][4] = new Pawn("white", 6, 4);
            fields[6][6] = new Pawn("white", 6, 6);
            fields[6][8] = new Pawn("white", 6, 8);
            fields[8][8] = new Pawn("white", 8, 8);

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
