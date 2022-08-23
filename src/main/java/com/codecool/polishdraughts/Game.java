package com.codecool.polishdraughts;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int boardLength;
    public int getValidBoardSize(){
        Scanner myVar = new Scanner(System.in);
        int inputBoardSize;
        while (true){
            System.out.println("Please give a board size (between 10 and 20): ");
            inputBoardSize = Integer.parseInt(myVar.nextLine());
            if (inputBoardSize>=10 && inputBoardSize<=20){
                break;
            }
            System.out.println("Error! Board size must be between 10 and 20!");
        }
        boardLength = inputBoardSize;
        return inputBoardSize;
    }
    public String checkForWinner(Pawn[][] fields){
        int pawnsThatCanMove = 0;
        int white=0;
        int whiteThatCanMove = 0;
        int black=0;
        int blackThatCanMove = 0;
        for (int row=0; row<fields.length; row++){
            for (int col=0; col< fields.length; col++){
                if (fields[row][col] != null ){
                    if (Objects.equals(fields[row][col].color, "white")){
                        white++;
                    }
                    else {
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

        if (white ==0){
            return "Black";
        } else if (black ==0) {
            return "White";
        } else if (pawnsThatCanMove == 0) {
            return "Tie";
        }
        return "None";
    }

    public boolean userInputValidation(String userInput){
        if (userInput.matches("^[a-zA-Z]\\d+$")){
            if (abc.indexOf(userInput.charAt(0)) < boardLength){
                if (Integer.parseInt(userInput.substring(1)) <= boardLength){
                    return true;
                }
            }
        }
        return false;
    }

    public void playRound(String currentPlayer,Pawn[][] fields){
        int row;
        int col;
        Scanner myVar = new Scanner(System.in);
        while (true){
            System.out.println("Please choose a draught to move with: ");
            String userInput = myVar.nextLine().toUpperCase();
            if (userInputValidation(userInput)){
                row = abc.indexOf(userInput.charAt(0));
                col = Integer.parseInt(userInput.substring(1)) -1;
                if (Objects.equals(fields[row][col].color, currentPlayer) && fields[row][col].canPawnMoveAnywhere(fields)) {
                    break;
                }
            }
            System.out.println("Please choose a draught that's yours and can move!");
        }



        if (currentPlayer == "white"){
            currentPlayer = "black";
        } else {
            currentPlayer = "white";
        }

    }
}
