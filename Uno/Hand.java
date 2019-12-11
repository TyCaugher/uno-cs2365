package Uno;

import java.util.ArrayList;

public class Hand extends Deck {

    private ArrayList<Card> hand;
    // Constructor.  Create arraylist object hand.
    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCard(Deck deck) {
        hand.add(hand.size(), deck.getLast()); // Add last card to hand
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
        return hand.get(hand.size() - 1);
    }

    public void clearHand() {
        hand.clear();
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
}
