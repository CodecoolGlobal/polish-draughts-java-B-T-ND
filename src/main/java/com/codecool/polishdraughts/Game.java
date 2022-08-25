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
        System.out.println("Please give a board size (between 10 and 20): ");
        while (true) {
            try {
                inputBoardSize = Integer.parseInt(myVar.nextLine());
                if (inputBoardSize >= 10 && inputBoardSize <= 20) {
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Please choose a NUMBER between 10 and 20!");
                continue;
            }
            System.out.println("Error! Board size must be between 10 and 20!");
        }
        boardLength = inputBoardSize;
        return inputBoardSize;
    }

    public String checkForWinner(Pawn[][] fields, String currentPlayer) {
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
        // if the other player (currentPlayer) can't move and the !current player can, current player lost
        if (currentPlayer.equals("white")){
            if (whiteThatCanMove == 0) {
                return "black";
            }
        } else if (currentPlayer.equals("black")){
            if (blackThatCanMove == 0){
                return "white";
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
        coordinatesPosition moveToPosition;
        while (true) {
            System.out.println("Please choose a piece to move with: ");
            userInput = myVar.nextLine().toUpperCase();
            if (userInputValidation(userInput)) {
                col = abc.indexOf(userInput.charAt(0));
                row = Integer.parseInt(userInput.substring(1)) - 1;
                if (fields[row][col] != null) {
                    if (Objects.equals(fields[row][col].color, currentPlayer) && fields[row][col].canPawnMoveAnywhere(fields)) {
                        break;
                    }
                }
            }
            System.out.println("Please choose a piece that's yours and can move!");
        }
        System.out.println("Selected pawn at: " + userInput);
        while (true) {
            System.out.println("Please choose where to move: ");
            userInput = myVar.nextLine().toUpperCase();
            moveToPosition = new coordinatesPosition(0, 0);
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
        int jumpLength = Math.abs(row - moveToPosition.row);
        if (jumpLength == 2) {
            while (true){
                row = moveToPosition.row;
                col = moveToPosition.col;
                if (fields[row][col].canAttack(fields, new coordinatesPosition(row,col))){
                    System.out.println(board);
                    System.out.println(currentPlayer + ", you can take another piece!");
                    System.out.println("Please choose where to move: ");
                    userInput = myVar.nextLine().toUpperCase();
                    moveToPosition = new coordinatesPosition(0, 0);
                    if (userInputValidation(userInput)) {
                        moveToPosition.col = abc.indexOf(userInput.charAt(0));
                        moveToPosition.row = Integer.parseInt(userInput.substring(1)) - 1;
                        if (fields[row][col].validateAttackMove(fields, moveToPosition, new coordinatesPosition(row,col))) {
                            board.movePawn(new coordinatesPosition(row, col), moveToPosition);
                            if (fields[row][col].canAttack(fields, new coordinatesPosition(row,col))){
                                continue;
                            }
                            break;
                        }
                        else{
                            System.out.println("Please choose a valid tile!");
                            moveToPosition.col = col;
                            moveToPosition.row = row;
                            continue;
                        }
                    }
                    System.out.println("Please choose a valid tile! ");

                } else {
                    break;
                }

            }
        }

        System.out.println(board);
        if (Objects.equals(checkForWinner(fields, currentPlayer), "white")) {
            System.out.println("White won!");
            System.exit(0);
        } else if (Objects.equals(checkForWinner(fields, currentPlayer), "black")) {
            System.out.println("Black won!");
            System.exit(0);
        } else if (Objects.equals(checkForWinner(fields, currentPlayer), "tie")) {
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
