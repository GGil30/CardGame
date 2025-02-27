// Gabriel Gil, 2/26/25

// Import the necessary classes
import java.awt.*;

public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int value;
    // Need a color instance variable for solitaire, and created an isHidden to determine whether the cards show
    // or not
    private String color;
    private boolean isHidden;
    // Declare instance variables to store the card's image and the frontend for it draw on
    private Image cardImage;
    private GameView window;
    // Constants for the card's width and height
    public static final int CARDWIDTH = 100;
    public static final int CARDHEIGHT = 140;


    // Constructor to receive everything passed in and properly initialize instance variables
    public Card(String rank, String suit, int value, String color, Image cardImage, GameView window) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.color = color;
        this.cardImage = cardImage;
        this.window = window;
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
        if (!isHidden) {
            return this.rank + " of " + this.suit + " - " + this.color;
        }
        return "----";
    }

    // Draw method for the cards to draw themselves based on their images. If the card is not hidden, then it should
    // draw itself. If it is hidden, it should access the image of the backside of the card from the frontend and draw
    // that instead
    public void draw(Graphics g, int x, int y) {
        if (this.isHidden) {
            g.drawImage(window.getBackImage(), x, y, CARDWIDTH, CARDHEIGHT, window);
        } else {
            g.drawImage(cardImage, x, y, CARDWIDTH, CARDHEIGHT, window);
        }
    }

    // Getters for the card image and the frontend
    public Image getCardImage() {
        return cardImage;
    }

    public GameView getWindow() {
        return window;
    }
}
