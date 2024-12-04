// Gabriel Gil, 12/4/24 DO WE NEED SETTERS FOR THIS?????

// Import the necessary classes
import java.util.ArrayList;

public class Player {
    // Instance variables
    ArrayList<Card> hand;
    String name;
    int points;

    // Constructors
    public Player(String name)
    {
        this.name = name;
        points = 0;
    }

    // This constructor does not apply to my game
    public Player(String name, ArrayList<Card> handIn)
    {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
        for (Card card : handIn)
        {
            hand.add(new Card(card.getRank(), card.getSuit(), card.getValue(), card.getColor()));
        }
    }

    // Getter methods
    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    // Add points method
    public void addPoints(int points)
    {
        this.points+=points;
    }

    // Add card method
    public void addCard(Card c)
    {
        hand.add(new Card(c.getRank(), c.getSuit(), c.getValue(), c.getColor()));
    }

    // To string method
    public String toString()
    {
        return this.name + " has " + this.points + " points\n" + this.name + "'s cards: " + this.hand;
    }

}
