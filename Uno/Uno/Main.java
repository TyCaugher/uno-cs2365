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

        do {
            try {
                // Game here
            }
        }
    }


    public static void main(String[] args) {
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
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
