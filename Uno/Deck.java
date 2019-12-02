package Uno;

import java.util.ArrayList;
import java.util.Collections;

// Deck object.
public class Deck {
    char[] colors = {'r', 'b', 'g', 'y'};
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // Create array lists of card objects
    public ArrayList<Card> cards; // The deck
    public ArrayList<Card> draw; // Drawable cards still in the deck.
    // Deck constructor.
    public Deck() {
        // Add 2 numbres for each color. Add 0 seperately. (There's only 1 zero per color.)
        for (char c : colors) {
            for (int n : numbers) {
                cards.add(new Card(n, c));
                cards.add(new Card(n, c));
            }
            cards.add(new Card(0, c));
        }

        //Create 4 of each wild cards.
        for (int i = 0; i <= 3; i++) {
            cards.add(new Card(13, 'w'));
            cards.add(new Card(14, 'w'));
        }
    }
    // Shuffle the array of card objects
    public void shuffle() {
        for (int i = 0; i <= 2; i++)
            Collections.shuffle(cards);
    }

    // Get the size of the deck.
    public int getDeckSize() {
        return cards.size();
    }

    public void addCard() {
        //Add card to the deck?
    }
}
