import java.util.ArrayList;
import java.util.Collections;

// Deck object.
public class Deck {
    char[] colors = {'r', 'b', 'g', 'y'};
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // Create array lists of card objects
    public ArrayList<Card> cards; // The deck// Drawable cards still in the deck.
    // Deck constructor.
    public Deck() {
        // Add 2 numbres for each color. Add 0 seperately. (There's only 1 zero per color.)
        cards = new ArrayList<>(); // Create the actual object
        for (char c : colors) {
            for (int n : numbers) {
                cards.add(new Card(n, c));
                cards.add(new Card(n, c));
            }
            cards.add(new Card(0, c));
        }

        //Create 4 of each wild cards.
        for (int i = 0; i <= 3; i++) {
            cards.add(new Card(13, 'a'));
            cards.add(new Card(14, 'a'));
        }
    }
    // Add a card to the deck
    public void addCard(Card addedCard) {
        cards.add(addedCard);
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

    public Card getLast() {
        return cards.get(cards.size() - 1);
    }

    public void removeLastCard() {
        cards.remove(cards.size() - 1); // Remove the card displayed in getLastCard()
    }
}

