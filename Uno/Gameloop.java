package Uno;

import java.util.*;

public class Gameloop {
    static int nCpu = 1;
    static Hand player = new Hand();
    static Hand cpu1 = new Hand();
    static Deck deck = new Deck();
    static Scanner s = new Scanner(System.in);
    static boolean skip;
    static boolean drawTwo;
    static boolean reverse;
    static boolean drawFour;
    static boolean winStatus;
    static char colorNow;
    static Card inPlay;
    static int turn;

    public static void gameloop() {
        skip = false;
        drawTwo = false;
        reverse = false;
        drawFour = false;
        winStatus = false;
        player.clearHand(); // Clear hands
        cpu1.clearHand();
        deck.shuffle();
        deck.shuffle();
        winStatus = false;

        System.out.print("Dealing cards...");
        drawCard(7, player);
        drawCard(7, cpu1);
        /*for(int i = 1; i <= nCpu; i++){
            drawCard(7, cpu1);
        }*/

        while (true) {
            if (Card.getCardNumber(deck.getLast()) == 13 || Card.getCardNumber(deck.getLast()) == 14) {
                deck.shuffle();
            } else {
                inPlay = deck.getLast();
                deck.removeLastCard();
                System.out.println("The card in play: " + inPlay.toString());
                System.out.println();
                break;
            }
        }

        do {
            for(int i = 0; i <= nCpu; i++) {
                /*if(reverse){
                    turn--;
                }
                else{
                    turn++;
                }*/
                if (inPlay.getCardColor() != 'a')
                    colorNow = inPlay.getCardColor();
                if (i == 0) {
                    if (playerTurn()) {
                        System.out.println("You win! Thanks for playing.");
                        winStatus = true;
                    }
                } else {
                    if (cpuTurn()) {
                        System.out.println("CPU wins! Thanks for playing.");
                        winStatus = true;
                    }
                }

            }
        } while (!winStatus);
    }

    public static boolean playerTurn() {
        if (skip){
            System.out.println("You've been skipped.");
            skip = false;
            return false;
        }
        else if (drawTwo){
            drawCard(2, player);
            System.out.println("You've drawn 2 cards.");
            drawTwo = false;
        }
        else if (drawFour){
            drawCard(4, player);
            System.out.println("You've drawn 4 cards.");
            drawFour = false;
        }

        Scanner input = new Scanner(System.in);
        int choice;
        boolean cardDrawn = false;
        boolean cardPlayed = false;
        boolean validWild = false;
        System.out.println("+------IN PLAY----+");
        System.out.println("| " + inPlay.toString());
        System.out.println("+-----------------+");
        System.out.println();
        System.out.println("+-- Your turn!  Make your move. --+");


        while (!cardPlayed) {

            printHand(player, cardDrawn);
            System.out.println("(1 -" + (player.getSize()) + ") Makes your move.");

            choice = choice();
            int cIndex = choice;
            // Check the conditions of the card and player's choice
            if (choice < player.getSize()) {
                Card cardChoice = player.getCard(cIndex);
                if (cardChoice.getCardNumber() == 10 && cardChoice.getCardColor() == colorNow) {
                    //skip
                    skip = true;
                    inPlay = cardChoice;
                    player.removeCard(cIndex);
                    cardPlayed = true;
                } else if (cardChoice.getCardNumber() == 11 && cardChoice.getCardColor() == colorNow) {
                    //draw 2
                    drawTwo = true;
                    inPlay = cardChoice;
                    player.removeCard(cIndex);
                    cardPlayed = true;
                } else if (cardChoice.getCardNumber() == 12 && cardChoice.getCardColor() == colorNow) {
                    //reverse
                    reverse = !reverse;
                    inPlay = cardChoice;
                    player.removeCard(cIndex);
                    cardPlayed = true;
                } else if (cardChoice.getCardNumber() > 12) {

                    System.out.println("What color would you like the active card to be? (r, b, g, y)");
                    do {
                        if (input.hasNext("r")) {
                            System.out.println("Color is set to red.");
                            colorNow = 'r';
                            validWild = true;
                        } else if (input.hasNext("g")) {
                            System.out.println("Color is set to green.");
                            colorNow = 'g';
                            validWild = true;
                        } else if (input.hasNext("b")) {
                            System.out.println("Color is set to blue.");
                            colorNow = 'b';
                            validWild = true;
                        } else if (input.hasNext("y")) {
                            System.out.println("Color is set to yellow.");
                            colorNow = 'y';
                            validWild = true;
                        } else {
                            System.out.println("Cannot read chosen colour. Please enter \"r\", \"b\", \"g\", or \"y\".");
                        }
                    } while (!validWild);
                    if (cardChoice.getCardNumber() == 14) {
                        drawFour = true;
                    }
                    player.removeCard(cIndex);
                    cardPlayed = true;
                } else if (cardChoice.getCardNumber() == inPlay.getCardNumber() || cardChoice.getCardColor() == colorNow) {
                    //regular
                    inPlay = cardChoice;
                    player.removeCard(cIndex);
                    cardPlayed = true;
                } else{
                    System.out.println("Invalid card selection. Please select a valid card or draw a card/end your turn.");
                }
            }else if (choice == player.getSize() & !cardDrawn) {
                System.out.println("Drawing a card");
                // Draw a card
                drawCard(1, player);
                cardDrawn = true;
            } else if (choice == player.getSize() & cardDrawn) {
                // End turn
                System.out.println("Turn ended!");
                cardPlayed = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + (player.getSize()));
            }
        }
        return player.getSize() == 0;
    }


    public static boolean cpuTurn() {
        if (skip){
            System.out.println("CPU has been skipped.");
            skip = false;
            return false;
        }
        else if (drawTwo){
            drawCard(2, cpu1);
            System.out.println("CPU has drawn 2 cards.");
            drawTwo = false;
        }
        else if (drawFour){
            drawCard(4, cpu1);
            System.out.println("CPU has drawn 4 cards.");
            drawFour = false;
        }
        boolean cardPlayed = false;
        System.out.println("CPU's turn!");
        Card cardChoice;

        for (int j = 0; true; j++) {
            System.out.println("CPU is thinking.");
            for (int i = 0; i < cpu1.getSize(); i++) {
                if (j == 1){
                    i = cpu1.getSize()-1;
                }
                cardChoice = cpu1.getCard(i);
                if (cardChoice.getCardNumber() == 10 && cardChoice.getCardColor() == colorNow) {
                    //skip
                    skip = true;
                    inPlay = cardChoice;
                    cpu1.removeCard(i);
                    cardPlayed = true;
                    break;
                } else if (cardChoice.getCardNumber() == 11 && cardChoice.getCardColor() == colorNow) {
                    //draw 2
                    drawTwo = true;
                    inPlay = cardChoice;
                    cpu1.removeCard(i);
                    cardPlayed = true;
                    break;
                } else if (cardChoice.getCardNumber() == 12 && cardChoice.getCardColor() == colorNow) {
                    //reverse
                    reverse = !reverse;
                    inPlay = cardChoice;
                    cpu1.removeCard(i);
                    cardPlayed = true;
                    break;
                } else if (cardChoice.getCardNumber() > 12) {
                    char[] colors = {'r', 'b', 'g', 'y'};
                    Random r = new Random(); // choose a random color.
                    int rand = r.nextInt(4);
                    switch (colors[rand]) {
                        case 'r': System.out.println("Color is set to red");
                        break;
                        case 'b': System.out.println("Color is set to blue");
                            break;
                        case 'g': System.out.println("Color is set to green");
                            break;
                        case 'y': System.out.println("Color is set to yellow");
                            break;
                    }
                    colorNow = colors[rand];
                    if (cardChoice.getCardNumber() == 14) {
                        drawFour = true;
                    }
                    inPlay = cardChoice;
                    cpu1.removeCard(i);
                    cardPlayed = true;
                    break;
                } else if (cardChoice.getCardNumber() == inPlay.getCardNumber() || cardChoice.getCardColor() == colorNow) {
                    //regular
                    inPlay = cardChoice;
                    cpu1.removeCard(i);
                    cardPlayed = true;
                    break;
                }
            }
            if (cardPlayed)
                break;
            else if (j == 1){
                System.out.println("CPU has no cards to play. How unfortunate.");
                break;
            }
            drawCard(1, cpu1);
            System.out.println("CPU drew a card.");
        }
        System.out.println("CPU has " + cpu1.getSize() + " cards remaining.");
        return cpu1.getSize() == 0;
    }

    public static void drawCard(int cardAmt, Hand target) {
        for (int i = 0; i < cardAmt; i++) {
            target.addCard(deck);
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

    public static void printHand(Hand player, boolean cardDrawn) {
        //Method to print the status of the user's hand.
        //System.out.println(player.getSize());
        int i;
        for (i = 1; i < player.getSize(); i++) {
            System.out.print("[" + i + "] " + player.getCard(i) + " ██ "); // Print out the player's hand
        }
        // Print a menu of possible options
        if (!cardDrawn) {
            System.out.println(" (" + i + ") Draw Card");
        } else {
            System.out.println(" (" + i + ") End Turn");
        }
    }
}
