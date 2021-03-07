package city;
import java.util.*;

public class District {

	int[] map;
	ArrayList<Road> roads = new ArrayList<Road>();
	
	//Weights between 0 and 1. 1 means 100% road coverage 
	double roadAmmountWeight = .4;
	double roadLengthWeight = .4;
	
	
	public District(int size)
	{
		map = new int[size];
	}
	
	public void makeRoads()
	{
		Random r = new Random();
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map.length; y++)
			{
				if(r.nextDouble() + roadAmmountWeight > 1.0)
				{
					roads.add(new Road(x,y,roadLengthWeight));
				}
					
				
			}
		}
	}
}
