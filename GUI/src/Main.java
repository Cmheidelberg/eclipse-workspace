import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Multiply window = new Multiply(350,350);
		//window.makeWindow();
		// new ColorGUI();
		new TickTackToe();
		
		/*
		int bestWins = 0;
	for(int p = 0; p < 1000; p++)
	{
		int numOfAi = 32;
		int numOfMutants = 16;
		int round = 0;
		TackMove best = new TackMove();
		TackMove tm;
		TickTackTornoment ttt;
	
		ArrayList<TackMove> aiArr = new ArrayList<TackMove>();
		ArrayList<TackMove> winners = new ArrayList<TackMove>();
		ArrayList<TackMove> mutants = new ArrayList<TackMove>();
		ArrayList<TackMove> mutantWinners = new ArrayList<TackMove>();
		//Makes the initial 64 ai moves
		for(int i = 0; i < numOfAi; i++)
		{
			tm = new TackMove();
			aiArr.add(tm);
		}
		while(aiArr.size() > 1)
		{
			round++;
			for(int i = 0; i < aiArr.size(); i+=2)
			{
				if(i+2 < aiArr.size()) {
					ttt = new TickTackTornoment(aiArr.get(i), aiArr.get(i+1));
					winners.add(ttt.playGame());
				}
			}
			
			//Mutates the winners
			for(int j = 0; j < winners.size(); j++)
			{
				//adds mutants
				//pa(winners.get(j).getWeights(),"origional[" + j + "]");
				mutants = new ArrayList<TackMove>();
				for(int i = 0; i < numOfMutants; i++)
				{
					TackMove mut = new TackMove();
					mut.setWeights(winners.get(j).getMutant(30));
					mutants.add(mut);
				}
				while(mutants.size() > 1)
				{
					mutantWinners = new ArrayList<TackMove>();
					for(int i = 0; i < mutants.size(); i+=2)
					{
						if(i+1 < mutants.size())
						{
							ttt = new TickTackTornoment(mutants.get(i), mutants.get(i+1));
							mutantWinners.add(ttt.playGame());
						}
					}
					
					mutants = new ArrayList<TackMove>();
					
					for(int i = 0; i < mutantWinners.size(); i++)
					{
						mutants.add(mutantWinners.get(i));
					}
					
				}
				//pa(mutants.get(0).getWeights(),"Mutant Winenr[" + j + "] is");
				winners.set(j,mutants.get(0));
				
			}
			
			
			//Reset aiArr array
			aiArr = new ArrayList<TackMove>();
			
			for(int i = 0; i < winners.size(); i++)
			{
				 aiArr.add(winners.get(i));
				 //pa(aiArr.get(i).getWeights(),"Winners[" + round + "][" + i + "]");
				 best = aiArr.get(i);
			}
			winners = new ArrayList<TackMove>();
	}
			
		
		
		TackMove tm1 = new TackMove();
		TackMove tm2 = new TackMove();
		
		
		int[] p1 = 	new TackMove().getWeights(); //best.getWeights();
		int[] p2 =  new TackMove().getWeights();
		tm1.setWeights(p1);
		tm2.setWeights(p2);
		
		if(p % 50 == 0) {
		System.out.println("Turn: " + p);
		pa(p1,"                  Best Move");
		pa(p2,"                  Worst Move");
		}
		TickTackTornoment finals = new TickTackTornoment(tm1,tm2);
		if(finals.playGame().getWeights().equals(p1)) {
			//System.out.println("Best Wins: " + p);
			bestWins++;
		}
		else {
			//System.out.println("Worst Wins: " + p);
			bestWins--;
		}
		
	}
	System.out.println(bestWins);
	
		
	}

	public static void pa(int[] arr, String str)
	{
		System.out.print(str + ": ");
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + ",");
		}
		System.out.print("\n");
		*/
	}
	
	
	public static int getMaxMutant(ArrayList<int[]> list)
	{
		int maxIndex = 0;
		int maxVal = 0;
		int currVal = 0;
		
		
		for(int i = 0; i < list.size(); i++)
		{
			currVal = list.get(i)[6] + list.get(i)[1];
			//System.out.println("currVal[" + i + "] = " + currVal);
			if(currVal > maxVal)
			{
				maxIndex = i;
				maxVal = currVal;
			}
			//System.out.println("maxVal[" + i + "] = " + maxVal);
			//System.out.println("maxIndex[" + i + "] = " + maxIndex);
		}
		
		return maxIndex;
	}
}
