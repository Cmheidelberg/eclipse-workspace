import java.util.*;

public class Pencil {

	//The pencil class marks every number that can fit into the given location on the Board
	
	//X,Y is the location (or box) the pencil is referring to
	private int x;
	private int y;
	
	private int[] row;
	private int[] col;
	private int[] cel;
	
	private ArrayList<Integer> possibleNumbers = new ArrayList<Integer>(); //All the numbers that could go in the box
	private Board board;
	
	private boolean changed = false;
	
	//Sets up local class variables
	public Pencil(int x, int y, Board board)
	{
		this.x = x;
		this.y = y;
		this.board = board;
		
		//Before checking i'm gonna assume the box could be any number (1:9)
		for(int i = 1; i <= 9; i++)
		{
			possibleNumbers.add(i);
		}
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	//Checks all numbers to see what numbers can go in a box. Enters the number to the board if there is only one possibility
	//returns true if it entered a number to the board and false otherwise 
	public boolean updatePencil()
	{
		changed = false;
		
		row = board.getRow(y); //Gets all the numbers in the same row as the box the pencil is referring to
		
		col = board.getCol(x); // Gets all the numbers in the same column the pencil is referring to
		
		cel = board.getCell(x/3, y/3); //There are only 3 cells (from 0:2) on the board so you have to 
									   //divide by three to get the right one
		
		//Removes numbers that cannot be in the box
		removeSame(row);
		removeSame(col);
		removeSame(cel);
		
		
		//This is for debugging, shows the numbers that could go in that box
		if(possibleNumbers.size() > 1) {
			System.out.print("Box(" + x + "," + y + "): ");
			
			for(int i = 0; i < possibleNumbers.size(); i++) {
				if((i+1) < possibleNumbers.size())
					{
						System.out.print(possibleNumbers.get(i) + ",");
					}
				else
					System.out.print(possibleNumbers.get(i) + "\n");
			}
		}
		
		//If there is only one possibility for that box, write it to the board
		if(possibleNumbers.size() == 1)
		{
			board.setNum(x, y, possibleNumbers.get(0));
			changed = true;
		}
		
		//returns true if it wrote in a number
		return changed;
		
	}
	
	//Removes any numbers from possibleNumbers array that cannot be in that slot
	public void removeSame(int[] arr)
	{		
		boolean done = false;
		boolean changed;
		//This is really sloppy and inneficient and i'm not proud of it...
		//Finds all the nubers that are the same are removes them from the possibleNumbers array
		while(!done) {
			changed = false;
			for(int i = 0; i < possibleNumbers.size(); i++)
			{
				for(int j = 0; j < arr.length; j++)
				{					
					if(arr[j] != 0 && possibleNumbers.get(i) == arr[j]) //Removes the number it it is already in the row,
					{													//col, or cell
						
						possibleNumbers.remove(i); 
						j = 100;
						changed = true;
					}					
				}
			}
			if(!changed)
				done = true;
		}
		
	}
	
}
