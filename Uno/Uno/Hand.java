package Uno;

import java.util.ArrayList;

public class Hand extends Deck {

    private ArrayList<Card> hand;
    // Constructor.  Create arraylist object hand.
    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCard(Deck deck) {
        hand.add(0, deck.getLastCard()); // Add last card to hand
        deck.removeLastCard();
    }

    public void removeCard(int elem) {
        hand.remove(elem);
    }

    public Card getCard(int elem) {
        return hand.get(elem);
    }

    public int getSize() {
        // Return the current size of the hand.  This will be important for detecting hand uno
        return hand.size();
    }
    public void printArray() {
        // Print the hand array
        System.out.println(hand.toString());
    }
    public Card getLast() {
        // Get the last card in the hand
        return hand.get(hand.size() -1);
    }

    public static boolean checkUno(Hand play) {
        boolean uno = false;
        if (play.getSize() == 1) {
            uno = true;
        } else {
            uno = false;
        }
        return uno;
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
}
