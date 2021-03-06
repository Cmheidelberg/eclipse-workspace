import java.util.*;


public class TickTackTornoment {

	int[] game;
	TackMove ai1;
	TackMove ai2;
	
	int[] score;
	int winner;
	
	int turn;
	
	Random rand;
	
	private boolean ended; //Used so that the game does'nt keep saying "Game Over" see ButtonListener class
	
	public TickTackTornoment(TackMove ai1, TackMove ai2)
	{
		this.ai1 = ai1;
		this.ai2 = ai2;
		
		game = new int[9];
		ended = false;
		winner = 0;
		score = new int[15];
		
		rand = new Random();
	}
	
	public TackMove playGame()
	{

		for(int i = 0; i < score.length; i++)
		{
			turn = rand.nextInt(100);
			for(int j = 0; j < 9; j++)
			{
				if(!gameOver())
				{
					playTurn();
				}	
				else
				{
					//System.out.println("The Winner is of round " + i + " is player " + winner);
					game = new int[9];
					ended = false;
					score[i] = winner;
					winner = 0;
					
				}
			}
		}
		//printScore();
		//System.out.println("Winner: " + getWinner());
		
		if(getWinner() == 0)
			score[100] = 100;
		
		if(getWinner() == 2)
		{
			return ai2;
		}
		else 
			return ai1;
		
	}
	public int getWinner()
	{
		int p1 = 0;
		int p2 = 0;
		
		for(int i = 0; i < score.length; i++)
		{
			if(score[i] == 1)
			{
				p1++;
			}else if(score[i] == 2)
			{
				p2++;
			}
		}
		
		if(p2 < p1)
			return 1;
		else if(p1 > p2)
			return 2;
		else
			{
				int num = rand.nextInt(2) + 1;
				
				if(num == 1)
					return 1;
				else if(num == 2)
					return 2;
				else 
					return 0;
			}
	}
	public void printScore()
	{
		for(int i = 0; i < score.length; i++)
		{
			System.out.print(score[i] + ",");
		}
		System.out.print("\n");
	}
	public void playTurn()
	{
		if(turn % 2 == 0)
			compTurn(1);
		else
			compTurn(2);
		
		//printGame();
		turn++;
	
	}
	
	public void printGame()
	{
		System.out.println("Turn: " + turn);
		for(int i = 1; i <= 9; i++)
		{
			System.out.print(game[i-1] + ",");
			if(i%3 == 0)
			{
				System.out.print("\n");
			}
			
		}
		System.out.println("");
	}
	private void compTurn(int aiNum)
	{
		boolean placed = false;
		int num = 0;
		if(!gameOver())
		{
			//There is a 4.5% chance the game will randomly 
			//place the marker (this is in case if all the spots 
			//the computer wants to place the 'O' is taken)
			num = rand.nextInt(105);
			
			if(num > 100) 
			{
				while(!placed)
				{
					num = rand.nextInt(9);
					if(game[num] == 0)
					{
						game[num] = aiNum;
						placed = true;
					}
				}
			}
			else
			{
				while(!placed)
				{
					for(int i = 0; i < ai1.getWeights().length; i++)
					{
						num -= ai1.getWeights()[i];
						if(num <= 0 && game[i] == 0 && !placed)
						{
							game[i] = aiNum;
							placed = true;							
						}
					}
				}
			}
		}
	}
	
	private void redistributeAi1Weights(int indexToRemove)
	{
		for(int i = 0; i < ai1.getWeights()[indexToRemove]; i++)
		{
			int num = 0;
			boolean place = false;
			while(!place)
			{
				num = rand.nextInt(9);
				if(num != indexToRemove)
				{
					place = true;
				}
			}
			
			ai1.getWeights()[num] += 1;
			ai1.getWeights()[indexToRemove] -= 1;
		}
	}
	
	private void redistributeAi2Weights(int indexToRemove)
	{
		for(int i = 0; i < ai2.getWeights()[indexToRemove]; i++)
		{
			int num = 0;
			boolean place = false;
			while(!place)
			{
				num = rand.nextInt(9);
				if(num != indexToRemove)
				{
					place = true;
				}
			}
			
			ai2.getWeights()[num] += 1;
			ai2.getWeights()[indexToRemove] -= 1;
		}
	}
	
	private boolean gameOver()
	{
		boolean over = true;
		
		for(int i = 0; i < game.length; i++)
		{
			if(game[i] == 0)
			{
				over = false;
			}
		}
		
		//Checks horizontally to see if the game is over
		for(int i = 0; i <= 6; i+=3)
		{
			if(game[i] == game[i+1] && game[i] == game[i+2] && game[i] != 0)
			{
				winner = game[i];
				over = true;
				break;
			}
		}
		
		//Checks vertically to see if the game is over
		for(int i = 0; i < 3; i++)
		{
			if(game[i] == game[i+3] && game[i] == game[i+6] && game[i] != 0)
			{
				winner = game[i];
				over = true;
				break;
				
			}
		}
		
		//Next two check the verticalls
		if(game[0] == game[4] && game[0] == game[8] && game[0] != 0)
		{
			over = true;
			winner = game[0];
		}
		
		if(game[2] == game[4] && game[2] == game[6] && game[2] != 0)
		{
			over = true;
			winner = game[2];
		}
		
		return over;
	}
	
	
	
	
	
}
