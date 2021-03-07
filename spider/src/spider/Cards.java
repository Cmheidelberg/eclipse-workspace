package spider;

public class Cards {

	private String name = "null";
	private int value = -1;
	private boolean shown = true;
	
	public Cards(int value)
	{
		this.value = value;
		
		if(value <= 9 && value != 1) { 
			name = " " + value;
		}
		else if(value == 10)
		{
			name = "" + value;
		}
		else {
			switch(value) {
				case 1:
					name = " A";
					break;
				case 11: 
					name = " J";
					break;
				case 12:
					name = " Q";
					break;
				case 13:
					name = " K";
					break;
				default:
					name = " #";
					break;				
			}
		}
		
	}
	
	public void notShown()
	{
		shown = false;
	}
	
	public void shown()
	{
		shown = true;
	}
	
	public String getName()
	{
		if(shown)
			return name;
		else
			return " @";
	}
	
	public int getValue()
	{
		if(shown)
			return value;
		else
			return -100;
	}
	
}
