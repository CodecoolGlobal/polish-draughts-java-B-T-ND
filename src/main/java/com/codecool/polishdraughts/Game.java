package com.codecool.polishdraughts;
import java.util.Objects;
import java.util.Scanner;

public class Game {
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
        return inputBoardSize;
    }
    public String checkForWinner(Pawn[][] fields){
        int white=0;
        int black=0;
        for (int row=0; row<fields.length; row++){
            for (int col=0; col< fields.length; col++){
                if (fields[row][col] != null ){
                    if (Objects.equals(fields[row][col].color, "white")){
                        white++;
                    }
                    else {
                        black++;
                    }
                }
            }
        }
        if (white ==0){
            return "Black";
        } else if (black ==0) {
            return "White";
        }
        return "None";
    }

    public boolean userInputValidation(String userInput, String abc, int boardLength){
        if (userInput.matches("^[a-zA-Z]\\d+$")){
            if (abc.indexOf(userInput.charAt(0)) < boardLength){
                if (Integer.parseInt(userInput.substring(1)) <= boardLength){
                    return true;
                }
            }
        }
        return false;
    }
}
