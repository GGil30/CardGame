// Gabriel Gil, 12/4/24

// Import the necessary classes
public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int value;
    // Need a color instance variable for solitaire, and created an isHidden to determine whether the cards show
    // or not
    private String color;
    private boolean isHidden;

    // Constructor
    public Card(String rank, String suit, int value, String color) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.color = color;
        // All cards start out as hidden, and will become not hidden as the game progresses
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

    // The toString method adjusts with whether the card is hidden or not. If hidden, the card appears as a few
    // dashes. If not hidden, the card's toString behaves as normal
    public String toString() {
        if(!isHidden) {
            return this.rank + " of " + this.suit + " - " + this.color;
        }
        return "----";
    }
}
