package com.codecool.polishdraughts;
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
}
