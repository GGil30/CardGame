import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {
    // Instance Variables
    public static final int WINDOW_WIDTH = 1425;
    public static final int WINDOW_HEIGHT = 1000;
    private Image background;
    private Image heart;
    private Image spade;
    private Image diamond;
    private Image club;
    private Image backImage;
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



    Game game;

    // constructor
    public GameView(Game game) {
        this.game = game;
        this.background = new ImageIcon("Resources/background.jpg").getImage();
        this.heart = new ImageIcon("Resources/heart.png").getImage();
        this.spade = new ImageIcon("Resources/spade.png").getImage();
        this.diamond = new ImageIcon("Resources/diamond.png").getImage();
        this.club = new ImageIcon("Resources/club.png").getImage();
        this.backImage = new ImageIcon("Resources/back.png").getImage();


        // Setup the window.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        paintBackground(g);
        if(game.getState() == -1){
            g.setColor(Color.white);
            g.setFont(BIG_FONT);
            g.drawString("Please enter your name.", X_START, Y_START);
        }
        if (game.getState() == 0) {
            paintInstructions(g);
        }
        if (game.getState() == 1) {
            paintSetup(g);
        }
        if (game.getState() == 2) {
            paintEndScreen(g);
        }

    }

    public void paintBackground(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

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
        g.drawString("Press any key to continue.", X_START, Y_START +60);

    }

    public void paintSetup(Graphics g) {
        // Draw final cols and extra card cols
        g.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                g.drawRect(25 + i * CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT);
            }
            g.drawRect(WINDOW_WIDTH - CARD_MARGIN - i * CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT);
            g.setColor(Color.white);
            g.setFont(SMALLER_FONT);
            g.drawString(Integer.toString(4 - i), WINDOW_WIDTH - 80 - i * CARD_MARGIN, 50);
            g.setColor(Color.black);
        }
        g.drawImage(heart, FINAL_COL_X_START, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(club, FINAL_COL_X_START + CARD_MARGIN, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(diamond, FINAL_COL_X_START + CARD_MARGIN *2, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(spade, FINAL_COL_X_START + CARD_MARGIN *3, FINAL_COL_Y, Card.CARDWIDTH, Card.CARDHEIGHT, this);

        // Draw columns
        g.setColor(Color.white);
        g.setFont(SMALLER_FONT);
        for (int i = 0; i < 7; i++) {
            g.drawString(Integer.toString(i + 1), MAIN_BOARD_COL_X_START + i * MAIN_BOARD_COL_MARGIN, MAIN_BOARD_COL_Y);

            //Draw Main board

            int x = MAIN_BOARD_CARD_X_START + i * MAIN_BOARD_COL_MARGIN;
            for (int j = 0; j < game.getBoard().getMainBoard().get(i).size(); j++) {
                int y = MAIN_BOARD_CARD_Y_START + j * MAIN_BOARD_CARD_Y_MARGIN;
                game.getBoard().getMainBoard().get(i).get(j).draw(g, x, y);
            }
        }

        // Draw Text Indicator to go the terminal
        g.drawString("Please refer to the console", WINDOW_WIDTH/2 - (CARD_MARGIN +25), FINAL_COL_Y + Card.CARDHEIGHT/2);

        // Draw extra cards
        game.getBoard().getExtraCards().get(1).draw(g, EXTRA_CARD_X, FINAL_COL_Y);
        game.getBoard().getExtraCards().get(0).draw(g, EXTRA_CARD_X + CARD_MARGIN, FINAL_COL_Y);

        // Draw final cards
        for (int i = 0; i < 4; i++) {
            if (game.getBoard().getCardCols().get(3 - i).size() > 0) {
                int cardToGet = game.getBoard().getCardCols().get(3 - i).size() - 1;
                game.getBoard().getCardCols().get(3 - i).get(cardToGet).draw(g, WINDOW_WIDTH - 125 - i * 125, 60);
            }

        }
    }

    public void paintEndScreen(Graphics g) {
        g.setColor(Color.white);
        g.setFont(BIG_FONT);
        String s = "Congratulations " + game.player.getName() + ", you won!";
        g.drawString(s, 100, 100);
    }


    public Image getBackImage() {
        return backImage;
    }
}
