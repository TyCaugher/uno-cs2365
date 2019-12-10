import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main extends Game {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        boolean repeatMenu = true;
        boolean validInput;
        int menuChoice;
        int nCpu = 1;
        System.out.println("Welcome to UNO!");
        System.out.println("Program by Tyler Kauffman and Christopher Bednarz\n");

        do {
            System.out.println("----Main Menu----\n");
            System.out.println("Select an option by entering a number:");
            System.out.println("(1) Start a new game.");
            System.out.println("(2) Set how many players there will be.");
            System.out.println("(3) Exit program.");

            if (!input.hasNextInt()) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Invalid input. Enter a number between 1 and 3.\n");
                input.next();
            }
            else {
                menuChoice = input.nextInt();
                switch (menuChoice) {
                    case 1:
                        newGame(nCpu);
                        break;
                    case 2:
                        //Change the number of players
                        validInput = false;
                        System.out.println("Enter how many computer opponents do you want (minimum is 1, maximum 3):");
                        while (!validInput) {
                            while (!input.hasNextInt()) {
                                System.out.println("Invalid input. Enter a number between 1 and 3.");
                                input.next();
                            }
                            nCpu = input.nextInt();
                            if (nCpu > 3 || nCpu < 1) {
                                System.out.println("Invalid input. Enter a number between 1 and 3.");
                            } else {
                                validInput = true;
                            }
                        }
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("Computer players set to " + nCpu + "\n");
                        break;
                    case 3:
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
