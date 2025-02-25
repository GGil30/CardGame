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
    private Image circle;
    private Image backImage;
    private final int XSTART = 100;
    private final int YSTART = 100;
    private final int CARDMARGIN = 125;
    private final int FINALCOLXSTART = 925;
    private final int FINALCOLY = 60;

    Game game;

    // constructor
    public GameView(Game game) {
        this.game = game;
        this.background = new ImageIcon("Resources/background.jpg").getImage();
        this.heart = new ImageIcon("Resources/heart.png").getImage();
        this.spade = new ImageIcon("Resources/spade.png").getImage();
        this.diamond = new ImageIcon("Resources/diamond.png").getImage();
        this.club = new ImageIcon("Resources/club.png").getImage();
        this.circle = new ImageIcon("Resources/circle.png").getImage();
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
            g.setFont(new Font("serif", Font.PLAIN, 30));
            g.drawString("Please enter your name.", XSTART, YSTART);
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
        g.setFont(new Font("serif", Font.PLAIN, 15));
        g.drawString("Welcome to Solitaire! If you aren't familiar with the game, the goal is to put all the " +
                "cards in order by suit in the final columns. In this version, you will be shown the board " +
                "before ", XSTART, YSTART);
        g.drawString("each move with the choice of getting the next wild card or playing a move. The game will " +
                "end automatically once you have won, but if you find yourself without any moves, then " +
                "you've ", XSTART, YSTART+20);
        String s = "unfortunately lost and will need to restart the game yourself. Good luck, " + game.player.getName() + "!";
        g.drawString(s, XSTART, YSTART+40);
        g.drawString("Press any key to continue.", XSTART, YSTART+60);

    }

    public void paintSetup(Graphics g) {
        // Draw final cols and extra card cols
        g.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                g.drawRect(25 + i * CARDMARGIN, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT);
            }
            g.drawRect(WINDOW_WIDTH - CARDMARGIN - i * CARDMARGIN, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString(Integer.toString(4 - i), WINDOW_WIDTH - 80 - i * CARDMARGIN, 50);
            g.setColor(Color.black);
        }
        g.drawImage(heart, FINALCOLXSTART, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(club, FINALCOLXSTART + CARDMARGIN, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(diamond, FINALCOLXSTART + CARDMARGIN*2, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT, this);
        g.drawImage(spade, FINALCOLXSTART + CARDMARGIN*3, FINALCOLY, Card.CARDWIDTH, Card.CARDHEIGHT, this);

        // Draw columns
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        for (int i = 0; i < 7; i++) {
            g.drawString(Integer.toString(i + 1), 200 + i * 150, 250);

            //Draw Main board

            int x = 155 + i * 150;
            for (int j = 0; j < game.getBoard().getMainBoard().get(i).size(); j++) {
                int y = 260 + j * 30;
                game.getBoard().getMainBoard().get(i).get(j).draw(g, x, y);
            }
        }

        // Draw extra cards
        game.getBoard().getExtraCards().get(1).draw(g, 25, 60);
        game.getBoard().getExtraCards().get(0).draw(g, 25 + 125, 60);

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
        g.setFont(new Font("serif", Font.PLAIN, 30));
        String s = "Congratulations " + game.player.getName() + ", you won!";
        g.drawString(s, 100, 100);
    }


    public Image getBackImage() {
        return backImage;
    }
}
