// Gabriel Gil, 11/18/24
public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int value;
    private String color;
    private boolean isHidden;

    // Constructor
    public Card(String rank, String suit, int value, String color) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.color = color;
        isHidden = true;
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

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public String toString()
    {
        if(!isHidden)
        {
            return this.rank + " of " + this.suit + " - " + this.color;
        }
        return "----";

    }
}
