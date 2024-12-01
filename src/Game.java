import java.util.ArrayList;
import java.util.Scanner;

public class Game {
   // Instance Variables
    Player player;
    Deck deck;
    Board board;
    Scanner input;

    // Constructor
    public Game()
    {
        input = new Scanner(System.in);
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q","K"};
        int[] values = {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10, 11, 12, 13};
//        System.out.println("What is your name?");
//        String name = input.nextLine();
//        player = new Player(name);
        deck = new Deck(ranks, suits, values);
        board = new Board(deck);
        board.printBoard();

    }

    // Play game method

    public void playGame()
    {
        while(true)
        {
            System.out.println("Would you like to move a card or get the next wild card? To move a card, type m. To get the next wildcard, type any key");
            String str = input.nextLine();
            playMove(str);
            board.printBoard();
            if(board.checkWin())
            {
                break;
            }
        }
        System.out.println("Congratulations! You won!");
    }

    public void playMove(String str)
    {
        if (str.equals("m"))
        {
            while(true)
            {
                System.out.println("What card to you want to move? Type in the card exactly how it is seen on the board.");
                String toMove = input.nextLine();
                System.out.println("Do you want to put it in a board column or final column? For board column, type 'b'. For a final column, type 'f'");
                String categoryWhere = input.nextLine();
                System.out.println("Where do you want to put it? Type the number of the column. If it's a final column, type the number associated with the column");
                int where = input.nextInt();
                input.nextLine();
                if(board.doMove(toMove, categoryWhere, where))
                {
                    return;
                }
                else {
                    System.out.println("That is not a valid move, try again.");
                    break;
                }
            }
        }
        else
        {
            board.shiftWildCard();
        }


    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();

    }
}
