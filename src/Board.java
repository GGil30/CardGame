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
        while(!deck.isEmpty())
        {
            extraCards.add(deck.deal());
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
        int largest = 0;
        for(int i = 0; i <7; i++)
        {
            System.out.print((i+1) + "                       ");
            if(mainBoard.get(i).size() > mainBoard.get(largest).size())
            {
                largest = i;
            }
        }
        System.out.println();
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(i<mainBoard.get(j).size())
                {
                    if(i == mainBoard.get(j).size() - 1)
                    {
                        mainBoard.get(j).get(i).setHidden(false);
                    }
                    System.out.print(mainBoard.get(j).get(i) + spaceAdd(mainBoard.get(j).get(i).toString()));

                }
                else if(i<mainBoard.get(largest).size()) {
                    System.out.print("                        ");
                }
            }
            if(i<mainBoard.get(largest).size() + 1) {
                System.out.println();
            }
        }
        if(!extraCards.isEmpty())
        {
            extraCards.get(0).setHidden(false);
            System.out.println("Wild card: " + extraCards.get(0));
        }
        System.out.println("Final Columns:");
        System.out.print("(1)Hearts: " + finalCardPrint(0) + "(2)Clubs: " + finalCardPrint(1) +  "(3)Diamonds: " + finalCardPrint(2) +  "(4)Spades: " + finalCardPrint(3));
        System.out.println();
    }

    public String spaceAdd(String str)
    {
        String toReturn = " ";
        for(int i = 0; i< (23 - str.length()); i++)
        {
            toReturn += " ";
        }
        return toReturn;
    }

    public String finalCardPrint(int i)
    {
        if(cardCols.get(i).isEmpty())
        {
            return "---------------------  ";
        }
        cardCols.get(i).get(cardCols.get(i).size() - 1).setHidden(false);
        return cardCols.get(i).get(cardCols.get(i).size() - 1).toString() + spaceAdd(cardCols.get(i).get(cardCols.get(i).size() - 1).toString());

    }

    public boolean doMove(String toMove, String categoryWhere, int where)
    {
        boolean isValidMove = false;
        ArrayList<Card> cardsToMove = new ArrayList<Card>();
        boolean isWildCard = true;
        for(ArrayList<Card> row : mainBoard)
        {
            if(!row.isEmpty()) {
                for(Card card : row)
                {
                    if(toMove.equals(card.toString()))
                    {
                        if (isValidMove(card, categoryWhere, where)) {
                            int test = row.indexOf(card);
                            int testing = row.size() - 1;
                            for (int i = testing; i > test - 1; i--)
                            {
                                cardsToMove.addFirst(row.remove(i));
                            }
                            isValidMove = true;
                            isWildCard = false;
                            break;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        if (!extraCards.isEmpty())
        {
            if(isWildCard && isValidMove(extraCards.get(0), categoryWhere, where))
            {
                cardsToMove.add(extraCards.remove(0));
            }
        }
        else if(!isValidMove)
        {
            return false;
        }

        if(categoryWhere.equals("f"))
        {
            cardCols.get(where -1).addAll(cardsToMove);
        }
        else
        {
            mainBoard.get(where -1).addAll(cardsToMove);
        }
        return true;
    }

public boolean isValidMove(Card card, String categoryWhere, int where)
{
    if(categoryWhere.equals("f"))
    {

            if(!cardCols.get(where-1).isEmpty())
            {
                if (cardCols.get(where - 1).get(cardCols.get(where - 1).size() - 1).getValue() > card.getValue()) {
                    return false;
                }
                if (!(cardCols.get(where - 1).get(cardCols.get(where - 1).size() - 1).getSuit().equals(card.getSuit()))) {
                    return false;
                }
            }
            else
            {
                if(card.getValue() != 1)
                {
                    return false;
                }
            }
    }
    else
    {
        if (!mainBoard.get(where - 1).isEmpty())
        {
            if(mainBoard.get(where-1).get(mainBoard.get(where-1).size()-1).getValue() < card.getValue())
            {
                return false;
            }
            if(!(card.getValue() == mainBoard.get(where-1).get(mainBoard.get(where-1).size()-1).getValue() - 1))
            {
                return false;
            }
            if(mainBoard.get(where-1).get(mainBoard.get(where-1).size()-1).getColor().equals(card.getColor()))
            {
                return false;
            }
        }
        else return card.getValue() == 13;
    }
return true;
}

public boolean checkWin()
{
    for(ArrayList row : mainBoard)
    {
        if (row.size()!= 0)
        {
            return false;
        }
    }
    if(!extraCards.isEmpty())
    {
        return false;
    }
    return true;
}

public void shiftWildCard()
{
    extraCards.add(extraCards.remove(0));
}
}
