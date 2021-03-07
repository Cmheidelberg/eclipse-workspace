package spider;
import java.util.ArrayList;
public class ComputerPlayer {
	
	Board board;
	public ComputerPlayer(Board board)
	{
		this.board = board;
	}
	boolean canMove = true;
	
	ArrayList<Cards> top = new ArrayList<Cards>();

	public boolean playTurn()
	{
		boolean moves = true;
		boolean canMakeMove = true;
		int turns = 0;
		
		while(canMakeMove) {	
			while(moves) {
				for(int refCol = 0; refCol < board.getTopCards().size(); refCol++) {
					moves = false;
					top = board.getTopCards();
					int end = findStackEndIndex(refCol);
					int putIt = findStackPlace(refCol,end);
					//System.out.println("End: " + end);
					//System.out.println("putIt: " + putIt);
					if(putIt != -1)	{
						turns++;
						board.moveCard(refCol, end, putIt);
						board.flipTopCards();
						board.drawBoard();
						moves = true;
					}
								
					
				}
			
			}
			moves = true;
			if(!board.drawCards())
			{
				canMakeMove = false;
			}
			System.out.println("Turns: " + turns);
		}
		
		
			
		
		return true;
	}
	
	public int findStackPlace(int col,int index)
	{
		//System.out.println(board.getCard(col, index).getName());
		Cards card = board.getCard(col, index);
		int loc = -1;
		for(int i = 0; i < top.size(); i++) {
			if(card.getValue() == top.get(i).getValue() - 1) {
				loc = i;
				break;
			}
		}
		return loc;
	}
	public int findStackEndIndex(int col)
	{
		ArrayList<Cards> column = new ArrayList<Cards>();
		
		column = board.getColumn(col);
		int top = -1;
		if(column.size() == 1)
		{
			top = 0;
		}
		else if(column.size() == 0)
		{
			top = -1;
		}
		else 
		{
			int last = column.get(column.size() -1).getValue();
			for(int i = column.size()-2; i >= 0; i--)
			{
				System.out.println("Last: " + last);
				System.out.println("Curr: " + column.get(i).getValue());
				if(column.get(i).getValue() != (last+1)) {
					
					top = column.size() - (i+2);
					break;
				}else
				{
					try{
						last = column.get(i).getValue();
					}
					
					catch(Exception e)
					{
						System.out.println("idk whats happaning man");
					}
				}
			}
		}
			System.out.println("Top: " + top);
		return top;
	}
}
