import java.util.ArrayList;
import java.util.Scanner;

public class Game {
   // Instance Variables
    Player player;
    Deck deck;
    Board board;

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
        board = new Board(deck);
        board.printBoard();

    }

    // Play game method

    public void playGame()
    {

    }

    public static void main(String[] args) {
        Game game = new Game();

    }
}
