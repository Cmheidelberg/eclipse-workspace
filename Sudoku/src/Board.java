
public class Board {

	//Stores all the Sudoko board locations and also gives out number positions 
	
	private int[] board;
	public  Board(int[] arr)
	{
		board = arr;
	}
	
	//Returns the number in the specified box 
	public int getXY(int x, int y)
	{
		int loc = (y*9) + x;
				
		return board[loc];
	}
	
	//Returns all the numbers from the specified column
	public int[] getCol(int col)
	{
		int[] colArr = new int[9];
		
		for(int i = 0; i < 9; i++)
		{
			colArr[i] = getXY(col,i);
		}
		return colArr;
	}
	
	//Returns all the numbers from the specified row
	public int[] getRow(int row)
	{
		int[] rowArr = new int[9];
		
		for(int i = 0; i < 9; i++)
		{
			rowArr[i] = getXY(i,row);
		}
		
		return rowArr;
	}
	
	//Returns the numbers from each cell from their "X,Y" location
	public int[] getCell(int x,int y)
	{
		int[] cellArr = new int[9];
		
		x*=3;
		y*=3;
		
		int xCount;
		int yCount = 0;
		
		//I dont remember how this works but it uses math to get the numbers from each cell
		for(int i = y; i < (y+3); i++)
		{
			yCount += 1;
			xCount = 0;
			for(int j = x; j < (x+3); j++)
			{
				cellArr[((3*yCount)+xCount) - 3] = getXY(j,i);
				xCount += 1;
			}
		}
		
		return cellArr;
	}
	
	//Sets the number from the specified box to that number 
	public void setNum(int x,int y,int num)
	{
		int loc = (y*9) + x; //Since the board is 9x9 every y value would be 9 spaces further in the array 
		board[loc] = num;
	}
	
	//Checks to see if the board has been solved
	public boolean solved()
	{
		boolean solved = true;
		for(int i = 0; i < board.length; i++)
		{
			if(board[i] == 0)
				solved = false;
		}
		return solved;
	}
	
	//Outprints the board into the console 
	public void drawBoard()
	{
		int count = 0;
		System.out.print("\n");
		for(int i = 0; i < board.length; i++)
		{
			//Draws vertical lines at end of each row
			if(i%9 == 0 && i != 0) {
				System.out.println("|");
				count++;
			}
			
			if(count != 0 && count%3 == 0)
			{
				System.out.print("\n");
				count = 0;
			}
			//creates lines after every cell
			if(i%3 == 0)
				System.out.print("|"); //If its the end of a cell draw the "|" else draw a comma 
			else
				System.out.print(" "); //seporates inner numbers
			
			if(board[i] != 0) //Doesn't draw a number if it is a zero
				System.out.print(board[i]);
			else
			System.out.print("_"); //Draws this when the number is a zero or "unsolved"
			
		}
		
		System.out.println("|\n"); //Prints last vertical pillar
	}
}

