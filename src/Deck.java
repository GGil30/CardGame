// Gabriel Gil, 12/4/24

// Import the necessary classes
import java.util.ArrayList;
import java.lang.Math;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values)
    {
        // Initialize the cards ArrayList
        cards = new ArrayList<Card>();
        // Iterate through all the ranks of cards and create new cards for each rank with proper suits and colors
        for(int i = 0; i< ranks.length; i++)
        {
            for(int j = 0; j< suits.length; j++)
            {
                if(suits[j].equals("diamonds") ||suits[j].equals("hearts"))
                {
                    cards.add(new Card(ranks[i], suits[j], values[i], "red"));
                }
                else
                {
                    cards.add(new Card(ranks[i], suits[j], values[i], "black"));
                }
            }
        }
        this.cardsLeft = cards.size();
        shuffle();
    }

    // isEmpty function
    public boolean isEmpty()
    {
        return this.cardsLeft == 0;
    }

    // getCardsLeft
    public int getCardsLeft() {
        return cardsLeft;
    }

    // Deal function
    public Card deal()
    {
        // If cards remain in the deck, return a card and decrease cardsLeft accordingly
        if (isEmpty())
        {
            return null;
        }
        return cards.get(--cardsLeft);

    }

    // shuffle function
    public void shuffle()
    {
        int j;
        Card swap;
        // Shuffle the deck by swapping iterating backwards through the deck and swapping the card at that index with
        // a random card in the deck
        for(int i = cards.size() - 1; i > -1; i--)
        {
            j = (int)(Math.random() * i);
            swap = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, swap);
        }
    }

}
