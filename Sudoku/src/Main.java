import java.util.*;

public class Main {
	/*
	 * Boards: Copy these into the int[] arr below to change the board. Yes I made the whole board a 1D array.
	 * It's more fun that way.
	 * 
	 * Easy:   
	 * 		   {0,0,0,6,0,0,1,0,7,6,8,0,9,5,1,3,0,0,0,0,3,0,0,2,5,6,8,0,4,0,8,1,0,0,2,0,0,0,0,0,0,0,8,5,0,0,9,0,0,6,5,0,7,3,4,0,9,0,0,3,0,8,5,1,6,2,0,0,9,0,3,0,5,0,0,7,0,6,0,0,0};
	 * 		   {0,3,1,6,0,7,0,0,0,6,0,0,8,0,0,2,5,7,8,0,0,0,9,0,6,0,3,4,0,0,0,0,0,8,3,2,0,1,0,0,6,9,0,0,0,7,0,3,2,4,0,0,0,6,9,0,2,4,0,1,0,7,8,0,8,5,0,0,0,0,0,9,3,0,4,0,0,0,0,6,1};
	 * 		   {0,6,0,0,8,0,4,2,0,0,1,5,0,6,0,3,7,8,0,0,0,4,0,0,0,6,0,1,0,0,6,0,4,8,3,0,3,0,6,0,1,0,7,0,5,0,8,0,3,5,0,0,0,0,8,3,0,9,4,0,0,0,0,0,7,2,1,3,0,9,0,0,0,0,9,0,2,0,6,1,0};
	 * 
	 * Medium: 
	 * 		   {0,0,0,4,0,0,2,0,0,0,0,2,0,0,0,0,1,8,5,0,6,9,0,0,0,3,0,0,6,9,0,0,0,3,0,0,0,5,0,0,0,0,0,2,1,8,0,0,1,5,7,6,0,9,0,0,0,0,3,0,9,6,0,9,0,0,6,0,2,0,5,0,0,0,0,0,0,0,7,0,2};
	 * 		   {0,2,0,0,0,0,0,0,0,0,5,0,0,0,6,0,4,0,4,0,0,8,0,0,2,5,0,0,0,7,2,5,0,6,0,3,0,4,0,0,0,0,0,0,0,0,6,8,9,0,3,4,2,7,7,0,4,1,6,0,0,0,8,0,1,0,0,3,0,0,0,0,6,0,5,0,0,0,0,0,0};
	 *   	   {0,0,0,0,0,7,2,0,3,0,8,0,0,0,4,0,9,0,4,2,7,1,0,0,0,0,0,8,0,1,0,0,0,0,0,0,3,0,0,0,0,6,0,0,0,0,5,0,9,0,1,3,0,0,7,0,4,0,5,0,0,1,0,5,0,0,0,1,0,0,0,7,1,0,0,0,4,9,5,8,0};		
	 * 
	 * Hard:  
	 * 		   {0,0,0,0,6,8,0,3,1,1,9,0,0,0,0,0,0,0,8,0,3,1,0,0,2,0,0,4,0,0,0,5,1,7,6,0,7,0,0,0,2,0,0,0,4,0,0,0,0,7,0,8,0,0,0,1,0,0,0,5,0,0,7,0,0,4,0,1,0,0,0,0,0,5,0,0,3,0,1,0,0};
	 * 		   {0,0,0,0,6,8,0,3,0,1,9,0,0,0,0,0,0,0,8,0,3,1,0,0,2,0,0,4,0,0,0,5,1,0,6,0,7,0,0,0,2,0,0,0,4,0,0,0,0,7,0,8,0,0,0,1,0,0,0,5,0,0,7,0,0,4,0,0,0,0,0,0,0,5,0,0,3,0,1,0,0};
	 * 		   {0,0,4,8,6,0,0,3,0,0,0,1,0,0,0,0,9,0,8,0,0,0,0,9,0,6,0,5,0,0,2,0,6,0,0,1,0,2,7,0,0,1,0,0,0,0,0,0,0,4,3,0,0,6,0,5,0,0,0,0,0,0,0,0,0,9,0,0,0,4,0,0,0,0,0,4,0,0,0,1,5};
	 * 		   {0,0,7,0,0,0,3,0,2,2,0,0,0,0,5,0,1,0,0,0,0,8,0,1,4,0,0,0,1,0,0,9,6,0,0,8,7,6,0,0,0,0,0,4,9,0,0,0,0,0,0,0,0,0,0,0,0,1,0,3,0,0,0,8,0,1,0,6,0,0,0,0,0,0,0,7,0,0,0,6,3};
	 * Expert: 
	 * 		   {1,3,0,0,0,5,0,0,0,5,0,0,7,0,0,0,0,2,0,0,0,2,0,9,0,0,0,0,0,0,0,0,0,0,0,0,9,1,0,0,0,0,0,8,7,0,0,7,0,6,0,0,1,0,0,5,0,4,0,0,0,0,0,3,4,0,0,0,0,0,0,0,8,0,0,0,0,6,3,0,5};
	 *		   
	 */
	//TODO improve game board choosing system
	//TODO make a pencil class that lets the pencil classes communicate with one another to further eliminate numbers that aren't possible
	public static void main(String[] args) {
		//This is the actual game board
		int[] arr ={2,4,7, 5,6,8, 9,3,1,
				    1,9,5, 0,4,0, 6,7,8,
				    8,6,3, 1,9,7, 2,4,5,
				    
				    4,0,9, 8,5,1, 7,6,0,
				    7,8,0, 0,2,0, 3,0,4,
				    5,0,0, 0,7,0, 8,0,0,
				    
				    0,1,0, 0,8,5, 0,0,7,
				    0,0,4, 0,1,0, 0,0,0,
				    0,5,0, 0,3,0, 1,0,0};
		
		Board board = new Board(arr);
		PencilController pc = new PencilController(board);
			
		board.drawBoard();
		
		int moves = 0; //Counts the number of "updates" the computer has made
		int boxesFilled = 1; //Counts the number of boxes filled each turn (starts positive to get tha ball rolling)
		
		//loops if something was changed last update and if the board isnt complete yet
		while(boxesFilled > 0 && !board.solved()) {
			
			//Updates all pencil markings and returns the amount of numbers entered on the board
			boxesFilled = pc.updatePencils(); 
			pc.fillCells();
			
			if(boxesFilled > 0 && !board.solved())
				moves++;
			System.out.println("Boxes filled that turn: " + boxesFilled);
			board.drawBoard();		
		}
		
		if(board.solved())
			System.out.println("The Game has been solved! It took " + moves + " move(s).");
		else
			System.out.println("I got stuck in " + moves + " moves.");
	}
	
	
}
