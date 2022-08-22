package com.codecool.polishdraughts;

public class Board {
    Pawn[][] fields;

    public Board(int n) {
        fields = new Pawn[n][n];
        int numberOfPawns = 2 * n;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if ((x + y) % 2 == 1 && numberOfPawns > 0){
                    fields[x][y] = new Pawn("black", x, y);
                    fields[n - x - 1][n - y - 1] = new Pawn("white", n - x - 1, n - y - 1);
                    numberOfPawns--;
                }
            }
        }
    }
}
