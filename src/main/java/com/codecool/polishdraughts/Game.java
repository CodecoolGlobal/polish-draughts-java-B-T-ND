package com.codecool.polishdraughts;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int boardLength;
    Board board;

    public Game() {
        int boardSize = getValidBoardSize();
        this.board = new Board(boardSize);
        this.boardLength = boardSize;
    }

    public int getValidBoardSize() {
        Scanner myVar = new Scanner(System.in);
        int inputBoardSize;
        while (true) {
            System.out.println("Please give a board size (between 10 and 20): ");
            inputBoardSize = Integer.parseInt(myVar.nextLine());
            if (inputBoardSize >= 10 && inputBoardSize <= 20) {
                break;
            }
            System.out.println("Error! Board size must be between 10 and 20!");
        }
        boardLength = inputBoardSize;
        return inputBoardSize;
    }

    public String checkForWinner(Pawn[][] fields) {
        int pawnsThatCanMove = 0;
        int white = 0;
        int whiteThatCanMove = 0;
        int black = 0;
        int blackThatCanMove = 0;
        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields.length; col++) {
                if (fields[row][col] != null) {
                    if (Objects.equals(fields[row][col].color, "white")) {
                        white++;
                    } else {
                        black++;
                    }
                    if (fields[row][col].canPawnMoveAnywhere(fields)) {
                        pawnsThatCanMove++;
                        if (Objects.equals(fields[row][col].color, "white")) {
                            whiteThatCanMove++;
                        } else {
                            blackThatCanMove++;
                        }
                    }
                }
            }
        }

        if (white == 0) {
            return "black";
        } else if (black == 0) {
            return "white";
        } else if (pawnsThatCanMove == 0) {
            return "tie";
        }
        return "none";
    }

    public boolean userInputValidation(String userInput) {
        if (userInput.matches("^[a-zA-Z]\\d+$")) {
            if (abc.indexOf(userInput.charAt(0)) < boardLength) {
                if (Integer.parseInt(userInput.substring(1)) <= boardLength) {
                    return true;
                }
            }
        }
        return false;
    }

    public void playRound(String currentPlayer) {
        Pawn[][] fields = board.fields;
        int row;
        int col;
        System.out.println(board.toString());
        Scanner myVar = new Scanner(System.in);
        System.out.println("It's " + currentPlayer + "'s turn! ");
        String userInput;
        while (true) {
            System.out.println("Please choose a draught to move with: ");
            userInput = myVar.nextLine().toUpperCase();
            if (userInputValidation(userInput)) {
                col = abc.indexOf(userInput.charAt(0));
                row = Integer.parseInt(userInput.substring(1)) - 1;
                if (Objects.equals(fields[row][col].color, currentPlayer) && fields[row][col].canPawnMoveAnywhere(fields)) {
                    break;
                }
            }
            System.out.println("Please choose a draught that's yours and can move!");
        }
        System.out.println("Selected pawn at: " + userInput);
        while (true) {
            System.out.println("Please choose where to move: ");
            userInput = myVar.nextLine().toUpperCase();
            coordinatesPosition moveToPosition = new coordinatesPosition(0, 0);
            if (userInputValidation(userInput)) {
                moveToPosition.col = abc.indexOf(userInput.charAt(0));
                moveToPosition.row = Integer.parseInt(userInput.substring(1)) - 1;
                if (fields[row][col].validatePawnMove(fields, moveToPosition)) {
                    board.movePawn(new coordinatesPosition(row, col), moveToPosition);
                    break;
                }
            }
            System.out.println("Please choose a valid tile! ");
        }
        if (Objects.equals(checkForWinner(fields), "white")) {
            System.out.println("White won!");
            System.exit(0);
        } else if (Objects.equals(checkForWinner(fields), "black")) {
            System.out.println("Black won!");
            System.exit(0);
        } else if (Objects.equals(checkForWinner(fields), "tie")) {
            System.out.println("It's a tie!");
            System.exit(0);
        }
    }

    public void start() {
        String currentPlayer = "white";
        while (true) {
            playRound(currentPlayer);
            if (currentPlayer.equals("white")) {
                currentPlayer = "black";
            } else {
                currentPlayer = "white";
            }
        }
    }
}
