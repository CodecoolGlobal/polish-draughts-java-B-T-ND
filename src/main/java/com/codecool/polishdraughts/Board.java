package com.codecool.polishdraughts;

public class Board {
    int[][] Pawn;

    public Board(int n) {
        Pawn = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                Pawn[x][y] = 0;
            }
        }
    }
}
