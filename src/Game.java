import java.util.ArrayList;
import java.util.Scanner;

public class Game {
   // Instance Variables
    Player player;
    Deck deck;
    ArrayList<ArrayList<Card>> board;

    // Constructor
    public Game()
    {
        Scanner input = new Scanner(System.in);
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q","K"};
        int[] values = {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10, 11, 12, 13};
        System.out.println("What is your name?");
        String name = input.nextLine();
        player = new Player(name);
        deck = new Deck(ranks, suits, values);
        board = new ArrayList<ArrayList<Card>>();
        for(int i = 0; i<7; i++)
        {
            for(int j = 0; j<i+1; j++)
            {
                board.get(i).add(deck.deal());
            }
        }
    }

    // Play game method

    public void playGame()
    {

    }

    public static void main(String[] args) {
        Game game = new Game();

    }
}
