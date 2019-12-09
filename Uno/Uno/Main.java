package Uno;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main extends Game {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int r = 0;
        int menuChoice;
        int nCpu = 1;

        do {
            System.out.println("Welcome to UNO!");
            System.out.println("Select an option by entering a number:");
            System.out.println("(1) Start a new game.");
            System.out.println("(2) Set how many players there will be.");
            System.out.println("(3) Exit program.");
            menuChoice = input.nextInt();

            switch (menuChoice) {
                case 1:
                    newGame(nCpu);
                    break;
                case 2:
                    //Change the number of players
                    System.out.println("Enter how many computer opponents you want (minimum is 1, maximum 3):");
                    nCpu = input.nextInt();
                    break;
                case 3:
                    //Exits program
                    r = 1;
                    break;
            }
        } while (r == 1);
    }
}
