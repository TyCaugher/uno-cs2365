package Uno;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random r = new Random();
    // Create objects
    static Hand player = new Hand();
    static Hand cpu1 = new Hand();
    static Hand cpu2 = new Hand();
    static Hand cpu3 = new Hand();
    static Deck deck = new Deck();
    static Scanner s = new Scanner(System.in);
    static Hand discardPile = new Hand();

    int nCpu;
    boolean reverse = false;

    public static void newGame(int cpuCount) throws InterruptedException {
        deck.shuffle();
        deck.shuffle();
        boolean compNum = false;

        switch (cpuCount) {
            case 1:
                player.addCard(deck);
                cpu1.addCard(deck);
            case 2:
                player.addCard(deck);
                cpu1.addCard(deck);
                cpu2.addCard(deck);
            case 3:
                player.addCard(deck);
                cpu1.addCard(deck);
                cpu2.addCard(deck);
                cpu3.addCard(deck);
            default:
                System.out.print("Invalid int cpuCount");
                break;
        }

        discardPile.addCard(deck); // Adds the top card as the discard pile "face up"

        int playerTurn = 1;
        boolean uno = false; // True if a player has uno.


    }


    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        boolean menuRepeat = true;
        int menuChoice;
        int nCpu = 1;

        while(menuRepeat == true) {
            System.out.println("Welcome to UNO!");
            System.out.println("Select an option by entering a number:");
            System.out.println("(1) Start a new game.");
            System.out.println("(2) Set how many players there will be.");
            System.out.println("(3) Exit program.");
            try {
                menuChoice = input.nextInt();

                switch (menuChoice) {
                    case 1:
                        newGame(nCpu);
                    case 2:
                        //Change the number of players
                        System.out.println("Enter how many computer opponents you want (minimum is 1, maximum 3):");
                        nCpu = input.nextInt();

                    case 3:
                        //Exits program
                        menuRepeat = false;

                    default:
                        //Repeat menu for invalid input
                        System.out.println("Invalid input. Please input a number 1-3");
                }

            }
            catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
    }
}
