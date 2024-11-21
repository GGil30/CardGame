import java.util.ArrayList;
import java.util.Scanner;

public class Game {
   // Instance Variables
    Player player;
    Deck deck;
    ArrayList<ArrayList<Card>> board;

    // Constructor
    public Game(String name, char[] ranks, String[] suits, int[] values)
    {
        player = new Player(name);
        deck = new Deck(ranks, suits, values, colors);
    }

    // Play game method

    public void playGame()
    {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        char[] ranks = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q','K'};
        int[] values = {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10, 11, 12, 13};
        System.out.println("What is your name?");
        String name = input.nextLine();
        Game game = new Game(name, ranks, suits, values);
    }
}
