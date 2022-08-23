package com.codecool.polishdraughts;


public class polishdraughts {
    public static void main(String[] args) {
        Game game = new Game();
//        game.getValidBoardSize();
        Board board = new Board(10);
        System.out.println(board);
    }
}
