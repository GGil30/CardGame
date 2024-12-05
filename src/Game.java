// Gabriel Gil, 12/4/24

// Import the necessary classes
import java.util.Scanner;

public class Game {
   // Instance Variables
    Player player;
    Deck deck;
    Board board;
    // Declare a scanner instance variable to get the name of the player
    Scanner input;

    // Constructor
    public Game() {
        // Initialize all instance variables, calling necessary constructors and getting necessary input from the
        // user
        input = new Scanner(System.in);
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q","K"};
        int[] values = {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10, 11, 12, 13};
        System.out.println("What is your name?");
        String name = input.nextLine();
        player = new Player(name);
        deck = new Deck(ranks, suits, values);
        board = new Board(deck);
        // Print out the instructions for the game
        printInstructions();
    }

    // Play game method
    public void playGame()
    {
        // Print the board out first for the user
        board.printBoard();
        // Repeat the process of playing the user's moves or adjusting the board for their choices until they have won
        while(true) {
            // Ask the user whether they want to move or get the next wild card
            System.out.println("Would you like to move a card or get the next wild card? To move a card, type m. " +
                    "To get the next wildcard, type any key");
            String str = input.nextLine();
            // Call the play move function and check whether the game has been won after that move
            playMove(str);
            board.printBoard();
            if(board.checkWin()) {
                break;
            }
        }
        // Once the game has been won, the loop will break, so print congratulatory message
        System.out.println("Congratulations, " + player.getName() + "! You won!");
    }

    // Play move function to adjust the board based on the user's choices
    public void playMove(String str)
    {
        // If the user chose to move a card, ask for the specifics for the move, and do the move if it is valid
        if (str.equals("m")) {
            System.out.println("What card to you want to move? Type in the card exactly how it is seen on the" +
                    " board.");
            String toMove = input.nextLine();
            System.out.println("Do you want to put it in a board column or final column? For board column, " +
                    "type any key. For a final column, type 'f'");
            String categoryWhere = input.nextLine();
            System.out.println("Where do you want to put it? Type the number of the column. If it's a final " +
                    "column, type the number associated with the column");
            int where = input.nextInt();
            input.nextLine();
            // Call the do move function, which passes in parameters which include the details of the move
            // If the move is not valid, inform the user with a message
            if(!board.doMove(toMove, categoryWhere, where)) {
                System.out.println("That is not a valid move, try again.");
            }
        }
        else {
            // Call the shift wild card function if the user wants the next wild card
            board.shiftWildCard();
        }
    }

    // Function which prints instructions of the game
    public void printInstructions() {
        System.out.println("Welcome to Solitaire! If you aren't familiar with the game, the goal is to put all the" +
                " cards in order by suit in the final columns. In this version, you will be shown \nthe board before " +
                "each move with the choice of getting the next wild card or playing a move. The game will end " +
                "automatically once you have won, but if you find yourself \nwithout any moves, then you've " +
                "unfortunately lost and will need to restart the game yourself. Good luck, " + player.getName() +"!");
    }

    // Main function. Create a new game and call the play game function
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
