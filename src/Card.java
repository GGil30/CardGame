// Gabriel Gil, 11/18/24
public class Card {
    // Instance variables
    private char rank;
    private String suit;
    private int value;

    // Constructor
    public Card(char rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    // Getters and Setters
    public char getRank() {
        return rank;
    }

    public void setRank(char rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString()
    {
        return this.rank + " of " + this.suit;
    }
}
