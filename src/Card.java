// Gabriel Gil, 11/18/24
public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int value;
    private String color;

    // Constructor
    public Card(String rank, String suit, int value, String color) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.color = color;
    }

    // Getters and Setters
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString()
    {
        return this.rank + " of " + this.suit;
    }
}
