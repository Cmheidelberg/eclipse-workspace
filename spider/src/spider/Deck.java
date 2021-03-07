package spider;

import java.util.*;
public class Deck {

	private ArrayList<Cards> deck = new ArrayList<Cards>();
	private Cards card;
	public Deck()
	{
		
			for(int i = 0; i < 13; i++)
			{
				card = new Cards(i+1);
				
				deck.add(card);
			}
		
	}
	
	
	public void listCards()
	{
		Cards tmp;
		for(int i = 1; i <= deck.size(); i++)
		{
			tmp = deck.get(i-1);
			System.out.print(tmp.getName());
			
			if(i % 13 != 0)
			{
				System.out.print("| ");
			}
			else
				System.out.println("");
		}
	}
	
	public void shuffle()
	{
		ArrayList<Cards> temp = new ArrayList<Cards>();
		ArrayList<Cards> removeFrom = deck;
		Random rand = new Random();
				
		
		int num = 0;
		
		while(removeFrom.size() > 0)
		{
			
			rand.setSeed(100); //Random seed for TESTING ONLY
			num = rand.nextInt(removeFrom.size());
			temp.add(removeFrom.get(num));
			removeFrom.remove(num);
			
			
		}
		
		deck = temp;
	}
	
	public int getNumberOfCards()
	{
		return deck.size();
	}
	
	public boolean hasCard()
	{
		if( deck.size() > 0)
		{
			return true;
		}
		else
			return false;
	}
	public Cards getCard()
	{
		Cards draw = deck.get(0);
		deck.remove(0);
		return draw;
	}
	
	
}
