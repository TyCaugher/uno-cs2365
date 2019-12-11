/*import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Game {
    static Random r = new Random();
    // Create objects
    static Hand player = new Hand();
    static Hand cpu1 = new Hand();
    static Hand cpu2 = new Hand();
    static Hand cpu3 = new Hand();
    static Deck deck = new Deck();
    static Scanner s = new Scanner(System.in);
    static Hand discardPile = new Hand();

    static Card inPlay;

    int nCpu;
    static boolean reverse = false;
    static boolean skip = false;
    static boolean draw2 = false;
    static boolean draw4 = false;
    static boolean gameEnded = false;

    public static int playCard() {
        int choice = 0;
        do {
            try {
                System.out.print("Which card do you want to play: ");
                choice = s.nextInt();
                s.nextLine();
                System.out.println(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                s.nextLine();
            }
        } while (choice == 0);
        return choice;
    }

    public static void printHand(Hand player1, boolean cardDrawn, boolean uno, boolean unoCalled) {
        //Method to print the status of the user's hand.
        System.out.println(player1.getSize());
        int x = 0;
        for (int i = 0; i < player1.getSize(); i++) {
            x = i + 1;
            System.out.print("[(" + x + ") " + player1.getCard(i) + "] "); // Print out the player's hand
        }
        x++;
        // Print a menu of possible options
        if (!cardDrawn) {
            System.out.println(x + ". Draw Card");
        } else if (cardDrawn) {
            System.out.println(x + ". End Turn");
        }
        x++;
        if (uno && !unoCalled) {
            System.out.println(x + ". Uno");
        } else if (uno && unoCalled) {
            System.out.println("Uno called");
        }
        }
    }

    public static void printDiscard() {
        System.out.println("+------Card in play------+");
        System.out.println(discardPile.getLast());
        System.out.println("+------------------------+");
    }

    public static Card wildColor(int cardNum) {
        System.out.println("What color do you want?");
        String input = s.nextLine();
        char color = 'w';
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
                    break;
                case 2:
                    player.addCard(deck);
                    cpu1.addCard(deck);
                    cpu2.addCard(deck);
                    break;
                case 3:
                    player.addCard(deck);
                    cpu1.addCard(deck);
                    cpu2.addCard(deck);
                    cpu3.addCard(deck);
                    break;
            }
        }

        discardPile.addCard(deck); // Adds the top card as the discard pile "face up"
        inPlay = discardPile.getLast();
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

        do { // Until the game has ended
            while (playerTurn == 1) {
                skip = false;
                draw2 = false;
                draw4 = false;

                sleep(1500); // Delay

                boolean calledUno = false;
                int cardPlayed = 0;
                int choice = 0;
                Boolean cardDrawn = false; // True if the player has drawn a card or not.

                do {
                    // uno = checkUno();
                    System.out.println("+--------------------------+");
                    System.out.println(inPlay);
                    System.out.println("+--------------------------+");
                    System.out.println("Cards left in deck: " + deck.getDeckSize());
                    System.out.println("+--------------------------+");
                    printHand(player, cardDrawn, uno, calledUno);
                    System.out.println(); // Break

                    choice = playCard();

                    while (choice > player.getSize() + 1 || choice < 0) { // If the input taken is greater than hand size or less than 0
                        System.out.println("Invalid input");
                        choice = playCard(); // Again
                    }
                    int element = choice - 1; // For removing cards from players hands.

                    if (choice == player.getSize() + 1) { // Draw card option.
                        if (!cardDrawn) { // If no card has been drawn yet
                            player.addCard(deck);
                            cardDrawn = true;
                        } else if (cardDrawn) {
                            System.out.println("A card has already been drawn!!");
                            cardPlayed = 1;
                        }
                    } else if (choice == player.getSize() + 2) { // Choice to call uno.
                        if (!calledUno) {
                            System.out.println(); // Line break
                            System.out.println("You have called uno!");
                            calledUno = true;
                        } else if (calledUno || !uno) { // Have already called, or it isnt allowed
                            System.out.println();
                            System.out.println("Please select a valid card");
                        }
                    } else if (Card.getCardColor(player.getCard(element)) == 'a' && Card.getCardNumber(player.getCard(element)) == 13) {
                        discardPile.addCard(wildColor(13));
                        player.removeCard(element);
                    } else if (Card.getCardColor(player.getCard(element)) == 'a' && Card.getCardNumber(player.getCard(element)) == 14) {
                        discardPile.addCard(wildColor(14));
                        draw4 = true;
                        player.removeCard(element);
                        cardPlayed = 1; // Break the loop
                    } else if (Card.getCardColor(player.getCard(element)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(player.getCard(element)) == Card.getCardNumber(discardPile.getLast())) {
                        // If a skip, reverse, or draw 2 is played;
                        inPlay = player.getCard(element);

                        switch (Card.getCardNumber(player.getCard(element))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                draw2 = true;
                                break;
                            case 12:
                                // Check if reverse is already true
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                }
                                break;
                            default:
                                break;
                        }
                        player.removeCard(element);
                        cardPlayed = 1;

                    } else {
                        System.out.println("+----------------------------+");
                        System.out.println("| Please select a valid card |");
                        System.out.println("+----------------------------+");
                    }
                } while (cardPlayed == 0);
            }
        } while (!gameEnded);
    }

    public static void checkDraw(Deck deck, Hand discardPile) {
        if (deck.getDeckSize() <= 4) {
            System.out.println();
            System.out.println("Shuffling Deck");
            System.out.println();
            for (int i = 0; i < discardPile.getSize(); i++) {
                if (Card.getCardNumber(discardPile.getLast()) == 13 && Card.getCardColor(discardPile.getLast()) != 'a') {
                    discardPile.removeCard(discardPile.getSize() - 1);
                    discardPile.addCard(new Card(13, 'a'));
                } else if (Card.getCardNumber(discardPile.getLast()) == 14 && Card.getCardColor(discardPile.getLast()) != 'a') {
                    discardPile.removeCard(discardPile.getSize() - 1);
                    discardPile.addCard(new Card(14, 'a'));
                } else {
                    deck.addCard(discardPile.getLast());
                    discardPile.removeCard(discardPile.getSize() - 1);
                }
            }
            deck.shuffle();
        } else {

        }
    }
}
*/