// Gabriel Gil, 2/26/25

// Import the necessary classes
import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {
    // Declare Image variables for necessary images in the background
    private Image background;
    private Image heart;
    private Image spade;
    private Image diamond;
    private Image club;
    private Image backImage;
    // Set the constants of numbers and fonts that are repeatedly used in the spacing and design of the game
    // to eliminate magic numbers and repetition of font declaration
    private final int X_START = 100;
    private final int Y_START = 100;
    private final int CARD_MARGIN = 125;
    private final int FINAL_COL_X_START = 925;
    private final int FINAL_COL_Y = 60;
    private final int EXTRA_CARD_X = 25;
    private final int MAIN_BOARD_COL_X_START = 200;
    private final int MAIN_BOARD_COL_MARGIN = 150;
    private final int MAIN_BOARD_COL_Y = 250;
    private final int MAIN_BOARD_CARD_X_START = 155;
    private final int MAIN_BOARD_CARD_Y_MARGIN = 30;
    private final int MAIN_BOARD_CARD_Y_START = 260;
    private final Font BIG_FONT = new Font("serif", Font.PLAIN, 30);
    private final Font SMALLER_FONT = new Font("serif", Font.BOLD, 20);
    private final Font SMALLEST_FONT = new Font("serif", Font.PLAIN, 15);
    public static final int WINDOW_WIDTH = 1425;
    public static final int WINDOW_HEIGHT = 1000;
    public static final int FINAL_COL_RECT_X_START = 80;
    public static final int FINAL_COL_RECT_Y_START = 50;
    // Declare game instance variable to be able to store a backend
    Game game;

    // GameView constructor
    public GameView(Game game) {
        // Store the backend in the game instance variable
        this.game = game;

        // Initialize the necessary images
        this.background = new ImageIcon("Resources/background.jpg").getImage();
        this.heart = new ImageIcon("Resources/heart.png").getImage();
        this.spade = new ImageIcon("Resources/spade.png").getImage();
        this.diamond = new ImageIcon("Resources/diamond.png").getImage();
        this.club = new ImageIcon("Resources/club.png").getImage();
        this.backImage = new ImageIcon("Resources/back.png").getImage();

        // Setup the window with the proper title, default close operation, its size, and setVisible to true
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Paint method which shows all the frontend graphics and does different things depending on the state
    public void paint(Graphics g) {
        // Draw the window and call the paint background method
        g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        paintBackground(g);

        // If the state is -1, meaning the pregame, then draw the appropriate text directing the user to the console
        if(game.getState() == -1){
            g.setColor(Color.white);
            g.setFont(BIG_FONT);
            g.drawString("Please enter your name.", X_START, Y_START);
        }
        // If the game state = 0, call the paint instructions method
        else if (game.getState() == 0) {
            paintInstructions(g);
        }
        // If the game state = 1, meaning the active game, call the paint setup method
        else if (game.getState() == 1) {
            paintSetup(g);
        }
        // If the game state = 2, meaning the game is over, call the paintEndScreen method
        else if (game.getState() == 2) {
            paintEndScreen(g);
        }
    }

    // Paint background method to draw the green background for the game
    public void paintBackground(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    // Paint instructions method to draw the instructions on the screen with the user's name
    public void paintInstructions(Graphics g) {
        g.setColor(Color.white);
        g.setFont(SMALLEST_FONT);
        g.drawString("Welcome to Solitaire! If you aren't familiar with the game, the goal is to put all the " +
                "cards in order by suit in the final columns. In this version, you will be shown the board " +
                "before ", X_START, Y_START);
        g.drawString("each move with the choice of getting the next wild card or playing a move. The game will " +
                "end automatically once you have won, but if you find yourself without any moves, then " +
                "you've ", X_START, Y_START +20);
        String s = "unfortunately lost and will need to restart the game yourself. Good luck, " + game.player.getName() + "!";
        g.drawString(s, X_START, Y_START +40);

        // Tell the user to type any key to start the active game phase
        g.drawString("Press any key to continue.", X_START, Y_START +60);

    }

    // Paint setup method to draw all the necessary features for the active game state
    public void paintSetup(Graphics g) {
        // Draw the rectangles to "hold" the final cards and the extra card deck and extra card
        g.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                g.drawRect(EXTRA_CARD_X + i * CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT);
            }
            g.drawRect(WINDOW_WIDTH - CARD_MARGIN - i * CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT);
            g.setColor(Color.white);

            // Draw the number labels for the columns of the final cards
            g.setFont(SMALLER_FONT);
            g.drawString(Integer.toString(4 - i), WINDOW_WIDTH - FINAL_COL_RECT_X_START - i * CARD_MARGIN, FINAL_COL_RECT_Y_START);
            g.setColor(Color.black);
        }
        // Draw the suit symbols in the appropriate rectangles to represent the final columns
        g.drawImage(heart, FINAL_COL_X_START, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(club, FINAL_COL_X_START + CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(diamond, FINAL_COL_X_START + CARD_MARGIN *2, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(spade, FINAL_COL_X_START + CARD_MARGIN *3, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);

        // Draw the column labels for the main board
        g.setColor(Color.white);
        g.setFont(SMALLER_FONT);
        for (int i = 0; i < 7; i++) {
            g.drawString(Integer.toString(i + 1), MAIN_BOARD_COL_X_START + i * MAIN_BOARD_COL_MARGIN, MAIN_BOARD_COL_Y);

            // Draw the cards on the main board, iterating through each card and calling their draw methods, passing
            // the appropriate x and y positions for the cards into their draw methods
            int x = MAIN_BOARD_CARD_X_START + i * MAIN_BOARD_COL_MARGIN;
            for (int j = 0; j < game.getBoard().getMainBoard().get(i).size(); j++) {
                int y = MAIN_BOARD_CARD_Y_START + j * MAIN_BOARD_CARD_Y_MARGIN;

                // Call the card's draw method
                game.getBoard().getMainBoard().get(i).get(j).draw(g, x, y);
            }
        }

        // Draw Text Indicator to go the terminal
        g.drawString("Please refer to the console", WINDOW_WIDTH/2 - (CARD_MARGIN +25), FINAL_COL_Y + Card.CARDHEIGHT/2);

        // Draw the extra card assuming there is an extra card remaining, and draw a card face down to indicate the
        // pile of remaining extra cards assuming there is more than one left
        if(game.getBoard().getExtraCards().size() > 0) {
            game.getBoard().getExtraCards().get(0).draw(g, EXTRA_CARD_X + CARD_MARGIN, FINAL_COL_Y);
            if(game.getBoard().getExtraCards().size() > 1) {
                game.getBoard().getExtraCards().get(1).draw(g, EXTRA_CARD_X, FINAL_COL_Y);
            }
        }
        // Draw final cards assuming their arraylists are not empty and pass in the appropriate x and y positions
        for (int i = 0; i < 4; i++) {
            if (game.getBoard().getCardCols().get(3 - i).size() > 0) {
                int cardToGet = game.getBoard().getCardCols().get(3 - i).size() - 1;
                game.getBoard().getCardCols().get(3 - i).get(cardToGet).draw(g, WINDOW_WIDTH - CARD_MARGIN -
                        i * CARD_MARGIN, FINAL_COL_Y);
            }

        }
    }

    // PaintEndScreen method to draw the correct text once the game is won
    public void paintEndScreen(Graphics g) {
        g.setColor(Color.white);
        g.setFont(BIG_FONT);
        String s = "Congratulations " + game.player.getName() + ", you won!";
        g.drawString(s, X_START, Y_START);
    }


    // GetBackImage method so the cards can access the backImage when drawing themselves
    public Image getBackImage() {
        return backImage;
    }
}
