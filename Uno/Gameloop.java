import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class Gameloop {
    static Hand player = new Hand();
    static Hand cpu1 = new Hand();
    static Hand cpu2 = new Hand();
    static Hand cpu3 = new Hand();
    static ArrayList<Hand> turnOrder = new ArrayList<>();
    static Deck deck = new  Deck();
    static Scanner s = new Scanner(System.in);
    static boolean reverse = false;
    static int winStatus; // 1 - win, -1 lost
    static int nCpu = 3;

    public static void drawCard(int cardAmt, Hand target) {
        for (int i = 0; i < cardAmt; i++) {
            target.addCard(deck);
        }
    }

    public static void actionCardCheck(int n, int turn) {
        switch (n) {
            case 10: // Skip
                turnOrder.remove(turn +  1);
                System.out.println(turnOrder.get(turn + 1) + " has been skipped!");
                break;
            case 11: // Draw 2
                drawCard(2, turnOrder.get(turn + 1));
                System.out.println(turnOrder.get(turn + 1) + " has drawn  2!");
                System.out.println(turnOrder.get(turn + 1) + ": \"You'll pay for that >:(\"");
                break;
            case 12: // Reverse
                Collections.reverse(turnOrder);
                System.out.println("Turn order has been reversed!");
                reverse =true;
                break;
            default:
                break;
        }
    }

    public static int choice() {
        int choice = 0;
        do {
            try {
                choice = s.nextInt();
                //s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                s.nextLine();
            }
        } while (choice == 0);
        return choice;
    }

    public static void printHand(Hand player1, boolean cardDrawn) {
        //Method to print the status of the user's hand.
        System.out.println(player1.getSize());
        int x = 0;
        for (int i = 0; i < player1.getSize(); i++) {
            x = i + 1;
            System.out.print("[" + x + "] " + player1.getCard(i) + " ██ "); // Print out the player's hand
        }
        x++;
        // Print a menu of possible options
        if (!cardDrawn) {
            System.out.println(" (" + x + ") Draw Card");
        } else if (cardDrawn) {
            System.out.println(" (" + x + ") End Turn");
        }
        x++;
    }

    public static void game() {
        Scanner input;
        Card inPlay; // The card currently in play
        String colorNow;

        turnOrder.add(player);
        turnOrder.add(cpu1);
        turnOrder.add(cpu2);
        turnOrder.add(cpu3);

        player.clearHand(); // Clear hands
        cpu1.clearHand();
        cpu2.clearHand();
        cpu3.clearHand();
        deck.shuffle();
        deck.shuffle();
        winStatus = 0;
        int playerTurn = 0; // Set to false at the end of turn or if skipped

        System.out.print("Dealing cards...");
        drawCard(7, player);
        drawCard(7, cpu1);

        // Check to make sure the top card is valid and not wild.
        while (true) {
            if (Card.getCardNumber(deck.getLast()) == 13 || Card.getCardNumber(deck.getLast()) == 14) {
                deck.shuffle();
            }
            else {
                inPlay = deck.getLast();
                deck.removeLastCard();
                System.out.println("The card in play: " + inPlay.toString());
                System.out.println();
                break;
            }
        }

        do { // The game loop
            // Create the regular turn order.
            // Add the top card of the deck into play, unless its a wild card
            int choice = 0;
            if (playerTurn == 0) {
                boolean cardDrawn = false;
                boolean cardPlayed = false;
                System.out.println("+------IN PLAY----+");
                System.out.println("| " + inPlay.toString());
                System.out.println("+-----------------+");
                System.out.println();
                System.out.println("+-- Your turn!  Make your move. --+");

                printHand(player, cardDrawn);
                System.out.println("(1 -" + player.getSize() + ") Makes your move.");

                while (!cardPlayed) {

                    choice = choice();
                    int cIndex = choice - 1;
                    // Check the conditions of the card and player's choice
                    if (choice <= player.getSize()) {
                        Card cardChoice = player.getCard(cIndex);

                        if (cardChoice.getCardNumber() == inPlay.getCardNumber() || cardChoice.getCardColor() == inPlay.getCardColor()) {
                            inPlay = cardChoice;
                            player.removeCard(cIndex);
                            cardPlayed = true;
                            // Advance the turnorder
                            cardPlayed = true;
                            if (reverse)
                                playerTurn = 0;
                            else
                                playerTurn += 1;
                        } else if (cardChoice.getCardNumber() > 9 & cardChoice.getCardNumber() < 13) {
                            actionCardCheck(cardChoice.getCardNumber(), turnOrder.indexOf(player));
                            cardPlayed = true;
                        } else if (cardChoice.getCardNumber() > 12) {
                            int x;
                            cardPlayed = true;
                            switch (cardChoice.getCardNumber()) {
                                case 13: case 14:
                                    // Check input.
                                    do {
                                        System.out.println("What color would you like the active card to be? (r, b, g, y)");
                                        input = new Scanner(System.in);
                                    } while (!input.hasNext("R..|r..|G....|g....|B...|b...|Y.....|y....."));
                                    if (input.hasNext("R..|r..") )
                                        colorNow = "Red";
                                    else if (input.hasNext("G....|g....") )
                                        colorNow = "Green";
                                    else if (input.hasNext("B...|b...") )
                                        colorNow = "Blue";
                                    else if (input.hasNext("Y.....|y.....") )
                                        colorNow = "Yellow";
                                    if (cardChoice.getCardNumber() == 14) {
                                        drawCard(4, turnOrder.get(playerTurn + 1));
                                        System.out.println(turnOrder.get(playerTurn + 1) + " has drawn 4 cards");
                                    }
                                    player.removeCard(cIndex);
                                    break;
                            }
                        }
                    }
                    else if (choice == player.getSize() + 1 & !cardDrawn) {
                        // Draw a card
                        drawCard(1, player);
                        cardDrawn = true;
                        printHand(player, cardDrawn);
                    }
                    else if (choice == player.getSize() + 1 & cardDrawn) {
                        // End turn
                        System.out.println("Turn ended!");
                        playerTurn += 1;
                        break;
                    }
                }
                System.out.println("Turn over!");
            }
            // CPU turns will go down here.
        } while (winStatus != -1 || winStatus != 1);
    }
}
