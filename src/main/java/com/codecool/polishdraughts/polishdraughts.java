package com.codecool.polishdraughts;

import java.util.Arrays;

public class polishdraughts {
    public static void main(String[] args) {
        Game game = new Game();
//        game.getValidBoardSize();
        Board board = new Board(10);
        System.out.println(Arrays.deepToString(board.Pawn));
    }
}
