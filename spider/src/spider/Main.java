package spider;

import java.util.*;

public class Main {
	static Board board = new Board();
	
	public static void main(String[] args) 
	{
		
		board.drawBoard();
		
		ComputerPlayer cp = new ComputerPlayer(board);
		
		cp.playTurn();
		
		board.drawBoard();
		
		int arr[] = new int[10];
		for(int i = 0; i < 100; i++)
		{
			System.out.println(arr[i]);
		}
		
	}
	
	public static void listTop()
	{
		ArrayList<Cards> top = board.getTopCards();
		
		for(int i = 0; i < top.size() ; i++)
		{
			System.out.print(top.get(i).getName() + ", ");
		}
	}

}
