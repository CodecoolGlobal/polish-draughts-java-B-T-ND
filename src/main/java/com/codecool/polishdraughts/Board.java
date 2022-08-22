package com.codecool.polishdraughts;

public class Board {
    int[][] Pawn;

    public Board(int n) {
        Pawn = new int[n][n];
        int whitePawns = 2 * n;
        int blackPawns = 2 * n;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if ((x + y) % 2 == 1 && blackPawns > 0){
                    Pawn[x][y] = 1;
                    Pawn[n - x - 1][n - y - 1] = 2;
                    blackPawns--;
                    whitePawns--;
                }
            }
        }
    }
}
