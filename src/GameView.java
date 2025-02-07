import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {
    // Instance Variables
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    Game game;

    // constructor
    public GameView(Game game){
        this.game = game;


        // Setup the window.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        g.drawRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

    }
}
