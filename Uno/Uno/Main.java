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

    public static int playCard() {
        int choice = 0;
        do {
            try {
                System.out.print("Which card do you want to play: ");
                choice = s.nextInt();
                s.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input");
                s.nextLine();
            }
        } while (choice == 0);
        return choice;
    }

    public static void printHand(Hand player1, boolean cardDrawn, boolean uno, boolean unoCalled) {
        //Method to print the status of the user's hand.
        int x = 0;
        for ( int i = 0; i < player1.getSize(); i++) {
            x += 1;
            System.out.print(x + ". " + player1.getCard(x)); // Print out the player's hand
        }
        x++;
        // Print a menu of possible options
        if (!cardDrawn) {
            System.out.println(x + ". Draw Card");
        }
        else if (cardDrawn) {
            System.out.println(x + ". End Turn");
        }
        x++;
        if (uno && !unoCalled) {
            System.out.println(x + ". Uno");
        } else if (uno && unoCalled) {
            System.out.println("Uno called");
        }
    }

    public static void printDiscard() {
        System.out.println("Discard pile: ");
        System.out.println(discardPile.getLast());
    }

    public static Card wildColor(int cardNum) {
        System.out.println("What color do you want?");
        String input = s.nextLine();
        char color = 'a';
        input = input.toLowerCase();

        switch (input.charAt(0)) {
            case 'b':
                color = 'b';
                break;
            case 'r':
                color = 'r';
                break;
            case 'y':
                color = 'y';
                break;
            case 'g':
                color = 'g';
                break;
            default:
                System.out.println("(b)lue, (g)reen, (r)ed or (y)ellow.");
                wildColor(cardNum); // Loop
                break;
        }
        return new Card(cardNum, color); // Return a card of the given parameters.
    }

    public static void newGame(int cpuCount) throws InterruptedException {
        deck.shuffle();
        deck.shuffle();
        boolean compNum = false;
        // Deal 7 cards based on cpu count
        for (int i = 0; i <= 6; i++) {
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
        }

        discardPile.addCard(deck); // Adds the top card as the discard pile "face up"
        // If the first card is wild
        if (Card.getCardNumber(discardPile.getLast()) == 14) {
            discardPile.addCard(wildColor(14));
        }
        // Case for +4 wild
        else if (Card.getCardNumber(discardPile.getLast()) == 13) {
            discardPile.addCard(wildColor(13));
        }

        int playerTurn = 1;
        boolean uno = false; // True if uno has been called
        do {
            while (playerTurn == 1) {
                boolean unoCalled = false;
                boolean cardPlayed = false;
                int choice = 0;

                System.out.println(); // Line break
                printHand(player, cardPlayed, uno, unoCalled);
                System.out.println(); // Line break
                printDiscard();

                choice = playCard();

                /*
                while (choice > player.getSize() + 2 || choice < 0) { // Input check
                    System.out.println("Invalid input");
                    choice = playCard(); // Call it again until its right.
                }
                */

                int elem = choice - 1; // To make it easier to remove cards from players hand

                if (choice == (player.getSize() + 1)) {

                }

            }
        }



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
