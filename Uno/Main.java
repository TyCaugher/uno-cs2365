package Uno;

import java.util.ArrayList;
import java.util.Scanner;

class Game {
    // game properties and play method here
    int game(int numberPlayers){

    }

}

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        boolean menuRepeat = true;
        int menuChoice;
        int numberPlayers = 4;

        while(menuRepeat == true) {
            System.out.println("Welcome to UNO!");
            System.out.println("Select an option by entering a number:");
            System.out.println("(1) Start a new game.");
            System.out.println("(2) Set how many players there will be.");
            System.out.println("(3) Exit program.");
            menuChoice = input.nextInt();

            switch (menuChoice) {
                case 1:

                case 2:
                    //Change the number of players
                    System.out.println("Enter how many players you want (minimum is 2, maximum 4):");
                    numberPlayers = input.nextInt();

                case 3:
                    //Exits program
                    menuRepeat = false;

                default:
                    //Repeat menu for invalid input
                    System.out.println("Invalid input. Please input a number 1-3");
            }
        }
    }
}
