import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class EncryptMenu extends JFrame{

	Font font = new Font("Calbri",Font.PLAIN,30);
	
	private JTextField fieldInput = new JTextField("",22);
	private JTextField fieldKey = new JTextField("",22);
	private JTextField fieldOutput = new JTextField("",22);
	
	private JButton eBut = new JButton("Encrypt");
	private JButton dBut = new JButton("Decrypt");
	
	private JLabel directionsLabel = new JLabel("Enter a string to either encrypt or decrypt it           ");
	private JLabel whiteLabel = new JLabel("                                       ");
	private JLabel inpLabel = new JLabel("Input: ");
	private JLabel keyLabel = new JLabel("Key:   ");
	private JLabel outLabel = new JLabel("Output:");
	private JLabel warningLabel = new JLabel("This is where warning would go");
	
	private JPanel panel;
	
	public int width;
	public int height;
	
	
	public EncryptMenu(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		buildMenu();
	}
	
	public EncryptMenu()
	{
		//If width and height aren't specified than they are set to a default value
		this.width = 700;
		this.height = 400;
		
		buildMenu();
	}
	
	public void buildMenu()
	{
		//These ones are part of extended JFrame class
		setTitle("Encryptor");
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//Custom method that sets up the panel
		buildPanel();
		
		add(panel);
		setVisible(true);
		
	}
	
	private void buildPanel()
	{		
		defaultSettings();
		
		eBut.addActionListener(new EncryptButtonListener());
		dBut.addActionListener(new DecryptButtonListener());
		
		//Build panel
		panel = new JPanel();
		panel.add(directionsLabel);
		panel.add(inpLabel);
		panel.add(fieldInput);
		panel.add(keyLabel);
		panel.add(fieldKey);
		panel.add(eBut);
		panel.add(dBut);
		panel.add(whiteLabel);
		panel.add(outLabel);
		panel.add(fieldOutput);
		panel.add(warningLabel);
		
	}
	
	public void warning(int type,String warning)
	{
		warningLabel.setText(warning);
		switch(type)
		{
			case 1:
				fieldInput.setBackground(Color.PINK);
				break;
			case 2:
				fieldKey.setBackground(Color.PINK);
				break;
			case 3:
				fieldOutput.setBackground(Color.PINK);
				break;
			default:
				break;
		}
	}
	
	public void warning(String warning)
	{
		warningLabel.setText(warning);	
	}
	
	/*Validates:
		-that input string is not empty
		-There is a key and it is made only of integers
		-Nothing is entered in the output*/
	public boolean validInput()
	{
		//checks for input string
		if(!(fieldInput.getText().length() > 0))
		{
			System.out.println("User didnt give an input message");
			warning(1,"Input a string");
			return false;
		}
		else if (!(fieldKey.getText().length() > 0))
		{
			System.out.println("User didnt put in a key");
			warning(2,"Input an encryptian key");
			return false;
		}
		
		try {
			if(Integer.parseInt(fieldKey.getText()) < 0)
			{
				System.out.println("User tried to enter negative key");
				warning(2,"Key cannot be negative");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Caught exception: " + e.getMessage());
			warning(2,"Key cannot be a string");
			return false;
		}
		
		
		return true;
	}
	
	//Sets colors and fonts to default settings 
	public void defaultSettings()
	{
		directionsLabel.setFont(font);
		
		inpLabel.setFont(font);
		fieldInput.setFont(font);
		fieldInput.setBackground(Color.WHITE);
		
		keyLabel.setFont(font);
		fieldKey.setFont(font);
		fieldKey.setBackground(Color.WHITE);
		
		eBut.setFont(font);
		
		dBut.setFont(font);
		
		
		whiteLabel.setFont(font);
		
		outLabel.setFont(font);
		fieldOutput.setFont(font);
		fieldOutput.setBackground(Color.WHITE);
		
		warningLabel.setFont(font);
		warningLabel.setForeground(Color.RED);
	}
	
	public String encrypt()
	{
		String str = fieldInput.getText();
		int key = Integer.parseInt(fieldKey.getText());
		
		String newStr = "";
		char curr;
		
		for(int i = 0; i < str.length(); i++)
		{
			curr = str.charAt(i);
			
			switch(curr)
			{
				case 'a':
					newStr += "12";
					break;
				case 'b':
					newStr += "09";
					break;
				case 'c':
					newStr += "76";
					break;
				case 'd':
					newStr += "54";
					break;
				case 'e':
					newStr += "32";
					break;
				case 'f':
					newStr += "11";
					break;
				case 'g':
					newStr += "06";
					break;
				case 'h':
					newStr += "55";
					break;
				case 'i':
					newStr += "56";
					break;
				case 'j':
					newStr += "65";
					break;
				case 'k':
					newStr += "45";
					break;
				case 'l':
					newStr += "32";
					break;
				case 'm':
					newStr += "31";
					break;
				case 'n':
					newStr += "33";
					break;
				case 'o':
					newStr += "89";
					break;
				case 'p':
					newStr += "71";
					break;
				case 'q':
					newStr += "30";
					break;
				case 'r':
					newStr += "60";
					break;
				case 's':
					newStr += "10";
					break;
				case 't':
					newStr += "00";
					break;
				case 'u':
					newStr += "69";
					break;
				case 'v':
					newStr += "96";
					break;
				case 'w':
					newStr += "14";
					break;
				case 'x':
					newStr += "16";
					break;
				case 'y':
					newStr += "68";
					break;
				case 'z':
					newStr += "67";
					break;
				default:
					newStr += 93;
					break;
			}
		}
		int[] keyArr = new int[fieldKey.getText().length()];
		
		for(int w = 0; w < keyArr.length; w++)
		{
			keyArr[w] = fieldKey.getText().charAt(w);
		}
		
		int keyPos = 0;
		str = "";
		for(int i = 0; i < newStr.length(); i++) 
		{
			System.out.println("loop: " + i);
			System.out.println("keyArr length: " + keyArr.length + "   |   KeyPos: " + keyPos);
			if((Character.getNumericValue(newStr.charAt(i)) + keyArr[keyPos]) < 10)
			{
				str += Character.getNumericValue(newStr.charAt(i)) + keyArr[keyPos];
			}
			else
				str += Character.getNumericValue(newStr.charAt(i)) + keyArr[keyPos] - 10;
			
			if(keyPos < keyArr.length)
			{
				keyPos++;
			}
			else
			{
				keyPos = 0;
			}
		}
		System.out.println(newStr);
		return str;
	}
	private class EncryptButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			//TODO make this encrypt a message
			
			
			warning("");
			defaultSettings();
			if(validInput())
			{
				System.out.println(encrypt());
				fieldOutput.setText(encrypt());
			}
		}
	}
	
	private class DecryptButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//TODO make this decrypt a message
			warning("");
			defaultSettings();
			validInput();
		}
	}

	
}



