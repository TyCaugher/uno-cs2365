package Uno;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        boolean repeatMenu = true;
        boolean validInput;
        int menuChoice;
        int nCpu = 1;
        System.out.println(" __    __  .__   __.   ______   \n|  |  |  | |  \\ |  |  /  __  \\ \n|  |  |  | |   \\|  | |  |  |  | ");
        System.out.println("|  |  |  | |  . `  | |  |  |  | \n|  `--'  | |  |\\   | |  `--'  | \n \\______/  |__| \\__|  \\______/  \n");
        System.out.println("Program by Tyler Kauffman and Christopher Bednarz\n");

        do {
            System.out.println("----Main Menu----\n");
            System.out.println("Select an option by entering a number:");
            System.out.println("(1) Start a new game.");
            System.out.println("(2) Exit program.");

            if (!input.hasNextInt()) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Invalid input. Enter a number between 1 and 3.\n");
                input.next();
            }
            else {
                menuChoice = input.nextInt();
                switch (menuChoice) {
                    case 1:
                        Gameloop.newGame();
                        break;
                    case 2:
                        //Exits program
                        repeatMenu = false;
                        break;
                    default:
                        System.out.println("Invalid input. Enter a number 1-3.");
                }
            }
        } while (repeatMenu);
    }
}
