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

    Game game;

    // constructor
    public GameView(Game game){
        this.game = game;
        this.background = new ImageIcon("Resources/background.jpg").getImage();
        this.heart = new ImageIcon("Resources/heart.png").getImage();
        this.spade = new ImageIcon("Resources/spade.png").getImage();
        this.diamond = new ImageIcon("Resources/diamond.png").getImage();
        this.club = new ImageIcon("Resources/club.png").getImage();
        this.circle = new ImageIcon("Resources/circle.png").getImage();



        // Setup the window.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        g.drawRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        paintBackground(g);
        if(game.getState() == 0){
            paintInstructions(g);
        }

    }

    public void paintBackground(Graphics g){
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.black);
        for(int i = 0; i < 4; i++){
            if(i<2){
                g.drawRect(25 + i*125, 60, 100, 140);
            }
            g.drawRect(WINDOW_WIDTH - 125 - i*125, 60, 100, 140);
        }
        g.drawImage(heart, 925, 60, 100, 140, this);
        g.drawImage(club, 1050, 60, 100, 140, this);
        g.drawImage(diamond, 1175, 60, 100, 140, this);
        g.drawImage(spade, 1300, 60, 100, 140, this);
    }

    public void paintInstructions(Graphics g){
        g.setColor(Color.white);
        g.drawString("Welcome to Solitaire! If you aren't familiar with the game, the goal is to put all the cards in order by suit in the final columns. In this version, you will be shown the board before ", 100, 300);
        g.drawString("each move with the choice of getting the next wild card or playing a move. The game will end automatically once you have won, but if you find yourself without any moves, then you've ", 100, 320);
        String s = "unfortunately lost and will need to restart the game yourself. Good luck, " + game.player.getName() + "!";
        g.drawString(s, 100, 340);
        g.drawString("Press any key to continue.", 100, 360);

    }
}
