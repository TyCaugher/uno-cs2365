package Uno;

/*
*   +-----+
*   |1    |
*   |  B  | UNO
*   |    1|
*   +-----+
*   A class which defines the properties of a card.
*   There are 108 cards in a deck.  A deck consists of:
*       - 4 Wild Cards
*       - 4 wild and + four cards
*       - 4 colors, (r)ed, (y)ellow, (g)reen and (b)lue
*       - One 0 and two of each number (1 - 9) for each color.
*       - 2 (s)kips, +(t)wos and re(v)erse of each color.
*
* Wild and action cards  will be counted as cards number 10, 11, 12, 13, 14
*
*/

public class Card {
    private int number;
    private char color;
    // Construtor for card data.
    Card(int cardN, char cardC) {
        number = cardN;
        color = cardC;
    }

    public int getCardNumber() {
        return number;
    }
    public char getCardColor() {
        return color;
    }
    // Static methods that call these previous
    public static char getCardColor(Card cards) {
        return cards.getCardColor();
    }
    public static int getCardNumber(Card cards) {
        return cards.getCardNumber();
    }

    // Output card data as a string.  If we need to call a string to display.  Override normal toString method
    @Override
    public String toString() {
        // Object number and color
        String cardN = null;
        String cardC = null;

        switch (color) {
            case 'r':
                cardC = "Red";
                break;
            case 'b':
                cardC = "Blue";
                break;
            case 'y':
                cardC = "Yellow";
                break;
            case 'g':
                cardC = "Green";
                break;
            case 'a':
                cardC = "All"; // For wild card color
                break;
        }

        if (number <= 9) {
            cardN = Integer.toString(number); // Just print the number as a string if its a normal number card
        }
        else if (number > 9) {
            switch(number) {
                case 10:
                    cardN = "Skip";
                    break;
                case 11:
                    cardN = "Draw 2";
                    break;
                case 12:
                    cardN = "Reverse";
                    break;
                case 13:
                    cardN = "Wild";
                    break;
                case 14:
                    cardN = "Wild +4";
                    break;
            }
        }
        return (cardC + " " + cardN);
    }
}
