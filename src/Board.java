import java.util.ArrayList;

public class Board {
    // Instance Variables
   private ArrayList<ArrayList<Card>> mainBoard;
   private ArrayList<ArrayList<Card>> cardCols;
   private ArrayList<Card> extraCards;

    // Constructor
    public Board(Deck deck)
    {
        extraCards = new ArrayList<Card>();
        cardCols = new ArrayList<ArrayList<Card>>();
        mainBoard = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < 7; i++) {
            mainBoard.add(new ArrayList<Card>());
            if(i<4){
                cardCols.add(new ArrayList<Card>());
            }
        }

        for(int i = 0; i<7; i++)
        {
            for(int j = 0; j<i+1; j++)
            {
                mainBoard.get(i).add(deck.deal());
            }
        }
    }

    // Getters and setters
    public ArrayList<Card> getExtraCards() {
        return extraCards;
    }

    public void setExtraCards(ArrayList<Card> extraCards) {
        this.extraCards = extraCards;
    }

    public ArrayList<ArrayList<Card>> getCardCols() {
        return cardCols;
    }

    public void setCardCols(ArrayList<ArrayList<Card>> cardCols) {
        this.cardCols = cardCols;
    }

    public ArrayList<ArrayList<Card>> getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(ArrayList<ArrayList<Card>> mainBoard) {
        this.mainBoard = mainBoard;
    }

    public void printBoard()
    {
        for(int i = 0; i <7; i++)
        {
            System.out.print("      " + i + "         ");
        }
        System.out.println();
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(i<mainBoard.get(j).size())
                {
                    System.out.print(mainBoard.get(j).get(i) + "   ");
                }
                else {
                    System.out.print("                ");
                }
            }
            System.out.println();
        }

    }

}
