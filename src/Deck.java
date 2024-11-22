import java.util.ArrayList;
import java.lang.Math;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values)
    {
        cards = new ArrayList<Card>();
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

    // deal function
    public Card deal()
    {
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
        for(int i = cards.size() - 1; i> -1; i--)
        {
            j = (int)(Math.random() * i);
            Card swap = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, swap);
        }
    }

}
