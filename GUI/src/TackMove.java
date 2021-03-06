import java.util.*;

public class TackMove {

	Random r = new Random();
	int[] weights = new int[9];
	
	
	public TackMove()
	{
		int pos;
		for(int i = 0; i < 100; i++)
		{
			pos = r.nextInt(9);
			weights[pos] += 1;
		}
	}
	
	public int[] getWeights()
	{
		return weights;
	}
		
	public int[] getMutant(int mutateValue)
	{
		int[] mutant = new int[weights.length];
		
		for(int j = 0; j < weights.length; j++)
		{
			mutant[j] = weights[j];
		}
		for(int i = 0; i < mutateValue; i++)
		{
			boolean possible = false;
			while(!possible)
			{
				int sub = r.nextInt(3);
				int subPos = r.nextInt(9);
				int addPos = r.nextInt(9);
				
				if(mutant[subPos] - sub >= 0 && mutant[addPos] + sub <= 100)
				{
					possible = true;
					mutant[subPos] -= sub;
					mutant[addPos] += sub;
				}
			}
		}
		
		return mutant;
	}
	
	public void setWeights(int[] nw)
	{
		this.weights = nw;
	}
}
