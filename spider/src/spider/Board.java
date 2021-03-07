package spider;

import java.util.*;
public class Board {

	ArrayList<Deck> decks = new ArrayList<Deck>();
	ArrayList<Cards> col;
	ArrayList<ArrayList<Cards>> board = new ArrayList<ArrayList<Cards>>();
	int cardDeck = 0;
	
	
	public Board()
	{
		Deck deck;
		for(int i = 0; i < 8; i++)
		{
			deck = new Deck();
			deck.shuffle();
			decks.add(deck);
		}
		
		int currDeck = 0;
		int num = 6;
		
		for(int j = 0; j < 10; j++)
		{
			if(j >= 4)
				num = 5;
			
			col = new ArrayList<Cards>();
			for(int i = 0; i < num; i++)
			{	
				if(!decks.get(currDeck).hasCard())
					currDeck++;
				col.add(decks.get(currDeck).getCard());
			}
			
			board.add(board.size(), col);
			
		}
		
		//Makes starting cards invisible
				for(int i = 0; i < board.size(); i++)
				{
					for(int j = 0; j < board.get(i).size()-1; j++)
					{
						board.get(i).get(j).notShown(); 
					}
						
				}
		
	}
	
	public void drawBoard()
	{
		
		System.out.println("\nGAME:");
		//draws the cards
		boolean placedCard;
		for(int i = 0; i < 50; i++) {
			placedCard = false;
			for(int j = 0; j < board.size(); j++)
			{
				if(board.get(j).size() > i)
				{
					System.out.print(board.get(j).get(i).getName() + " ");
					placedCard = true;
				}
				else
					System.out.print("   ");
				
				
			}
			if(placedCard)
				System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	public boolean drawCards()
	{
		if(countCardsInDeck() > 0)
		{
			
			for(int j = 0; j < board.size(); j++) {
				boolean foundDeck = false;
				
				//Checks to make sure there are cards in the deck it wants to draw from
				while(!foundDeck)
				{
					if(!decks.get(cardDeck).hasCard()) {
						cardDeck++;
						//System.out.println("CardDeck: " + cardDeck);
					}
					else
					{
						foundDeck = true;
					}	
				}
				
				//System.out.println("NumOfCards: " + decks.get(cardDeck).getNumberOfCards());
				board.get(j).add(decks.get(cardDeck).getCard());
				
			}
			return true;
		}
		return false;
	}
	public ArrayList<Cards> getTopCards()
	{
		ArrayList<Cards> top = new ArrayList<Cards>();
		for(int i = 0; i < board.size(); i++)
		{
			top.add(board.get(i).get(board.get(i).size()-1));
		}
		return top;
	}
	
	public int countCardsInDeck()
	{
		int count = 0;
		for(int i = 0; i < decks.size(); i++) {
			
			count += decks.get(i).getNumberOfCards();
		
		}
		
		return count;
	}
	
	public ArrayList<Cards> getColumn(int col)
	{
		return board.get(col);
	}
	
	public Cards getCard(int col, int index)
	{
		return board.get(col).get(board.get(col).size() - (index+1));
	}
	
	//moves cards to a new column (column from cards to take, how far up to take the cards, what column to put them on)
	public void moveCard(int col, int index,int moveToCol)
	{
		
		//copies the cards over to the new column
		for(int i = index; i >= 0; i--)
		{
			board.get(moveToCol).add(board.get(col).get(board.get(col).size() - (i+1)));
			//System.out.println("card move to " + (board.get(col).get(board.get(col).size() - (i+1))).getName());
		}
		
		//I honestly have no idea why this works but it removes the cards that were just copied over to the other column 
		for(int d = index; d >= 0; d--)
		{	
			//System.out.println("Remove Index: " + (board.get(col).size() - (d+1)));
			board.get(col).remove(board.get(col).size() - (d+1));
		}
		
	}
	
	public void flipTopCards()
	{
		for(int i = 0; i < board.size(); i++)
		{
			board.get(i).get(board.get(i).size()-1).shown();
		}
	}
	
	public void listDecks()
	{
		for(int i = 0; i < decks.size(); i++)
		{
			System.out.println("Deck " + i + ":");
			decks.get(i).listCards();
			System.out.println("\n");
		}
	}
	
	
}
