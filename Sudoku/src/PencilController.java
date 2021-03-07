import java.util.ArrayList;

public class PencilController {

	ArrayList<Pencil> emptyBoxes = new ArrayList<Pencil>();
	
	ArrayList<Pencil> cell = new ArrayList<Pencil>();
	
	
	public PencilController(Board board) {
		//Makes the pencil array for each unsolved box on the board
		for(int x = 0; x < 9; x++)
		{
			for( int y = 0; y < 9; y++)
			{
				if(board.getXY(x, y) == 0)
					emptyBoxes.add(new Pencil(x,y,board));
			}
		}
	}
	
	public int updatePencils()
	{
		boolean tmp = false;
		int boxesFilled = 0;
		
		System.out.println("Boxes left to solve: ");
		
		for(int i = 0; i < emptyBoxes.size(); i++)
		{
			tmp = emptyBoxes.get(i).updatePencil(); 
			
			if(tmp) {
				boxesFilled += 1;
			}
			
		}
		
		return boxesFilled;
	}
	
	public void fillCells()
	{	
	
		
		for(int x = 1; x <= 9; x*=3)
		{
			for(int y = 1; y <= 9; y*=3)
			{
				
				for(int i = 0; i < emptyBoxes.size(); i++)
				{
					//System.out.println("(" + emptyBoxes.get(i).getX() + "," + emptyBoxes.get(i).getY() + ") Cell: " 
					//+ (emptyBoxes.get(i).getX()+1)/x + "," + (emptyBoxes.get(i).getY()+1)/y);
				}
			}
		}
		
	}
	
}
