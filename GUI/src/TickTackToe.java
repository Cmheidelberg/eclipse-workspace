import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TickTackToe{

	JFrame frame;
	JPanel gameBoxes;
	JPanel infoBox;
	JPanel mainPanel;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem exitItem;
	JMenuItem newGameItem;
	
	Font font = new Font("Calbri",Font.PLAIN,30);
	
	JLabel[] info;
	
	JButton[] button;
	
	Random rand = new Random();
	
	ImageIcon x = new ImageIcon("C:\\Users\\Admin\\Pictures\\X.png");
	ImageIcon o = new ImageIcon("C:\\Users\\Admin\\Pictures\\O.png");
	
	int[] game;
	int[] ai = {26,11,11,0,1,9,11,14,17};
	int winner;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 850;
	private final String BUFFER_STR = "                                       ";
	private boolean ended; //Used so that the game does'nt keep saying "Game Over" see ButtonListener class
	
	public TickTackToe()
	{			
		frame = new JFrame("TickTackToe");
		buildMenuBar();		
	}
	
	public void buildMenuBar() 
	{
		menuBar = new JMenuBar();
		
		exitItem = new JMenuItem("Quit");
		newGameItem = new JMenuItem("New Game");
		
		exitItem.addActionListener(new ExitListener());
		newGameItem.addActionListener(new NewGameListener());
		
		fileMenu = new JMenu("File");
		
		fileMenu.add(exitItem);
		fileMenu.add(newGameItem);
		
		fileMenu.setFont(font);
		exitItem.setFont(font);
		newGameItem.setFont(font);
		
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		
		
		startGame();
		
	}
	public void startGame()
	{
		System.out.println("New Game");
		info = new JLabel[4];
		
		button = new JButton[9];
		game = new int[9];
		ended = false;
		winner = 0;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		gameBoxes = new JPanel();
		gameBoxes.setLayout(new GridLayout(3,3,2,2));
		gameBoxes.setMaximumSize(new Dimension(800,800));
		
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new JButton("b" + i);
			button[i].setPreferredSize(new Dimension(50,200));
			button[i].addActionListener(new ButtonListener());
			gameBoxes.add(button[i]);
		}
		
		infoBox = new JPanel();
		infoBox.setLayout(new GridLayout(info.length,1));
		
		//Sets up the text boxes under the game board
		for(int i = 0; i < info.length; i++)
		{
			info[i] = new JLabel("");
			info[i].setFont(font);
			infoBox.add(info[i]);
		}
		
		mainPanel.add(gameBoxes);
		mainPanel.add(infoBox);
		frame.setSize(WIDTH,HEIGHT);
		frame.setResizable(false);
		
		frame.setContentPane(mainPanel);
		frame.setMinimumSize(new Dimension(850,850));
		
		frame.setVisible(true);
	}
	
	private void shiftText()
	{
		for(int i = info.length-2; i >= 0; i--)
		{
			info[i+1].setText(info[i].getText());
			info[i+1].setForeground(info[i].getForeground());
			info[i].setForeground(Color.BLACK);
		}
	}
	private void updateInfo(String str)
	{
		shiftText();
		str = BUFFER_STR + str;
		info[0].setText(str);
	}
	
	private void updateInfo(String str, int color)
	{
		shiftText();
		str = BUFFER_STR + str;
		info[0].setText(str);
		
		if(color == 1)
		{
			info[0].setForeground(Color.RED);
		}
		else if(color == 2)
		{
			info[0].setForeground(Color.ORANGE);
		}
	}
	
	private boolean checkClick(String box)
	{
		int index = Integer.parseInt(box.charAt(box.length()-1)+ "");
		updateGame();
		
		if(gameOver())
		{
			updateInfo("The game has ended!",2);
			return false;
		}
		
		if(game[index] != 0)
		{
			updateInfo("That box is already marked!",2);
			return false;
		}
		
		game[index] = 1;
		updateGame();
		
		return true;
	}
	
	public void updateGame()
	{
		for(int i = 0; i < game.length; i++)
		{
			
				if(game[i] == 1 && button[i].getIcon() == null)
				{
					button[i].setIcon(x);
					updateInfo("X added in b" + i);
				}
				else if(game[i] == 2 && button[i].getIcon() == null)
				{
					button[i].setIcon(o);
					updateInfo("O added in b" + i);
				}
						
		}
		
	}
	private void compTurn()
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
				updateInfo("Random Move");
				while(!placed)
				{
					num = rand.nextInt(9);
					if(game[num] == 0)
					{
						game[num] = 2;
						placed = true;
						updateGame();
					}
				}
			}
			else
			{
				while(!placed)
				{
					for(int i = 0; i < ai.length; i++)
					{
						num -= ai[i];
						if(num <= 0 && game[i] == 0 && !placed)
						{
							game[i] = 2;
							placed = true;
							updateGame();
							
						}
					}
				}
			}
		}
	}
	
	private void redistributeAiWeights(int indexToRemove)
	{
		for(int i = 0; i < ai[indexToRemove]; i++)
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
			
			ai[num] += 1;
			ai[indexToRemove] -= 1;
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
	
	private class ButtonListener implements ActionListener
	{
		
		
		public void actionPerformed(ActionEvent e)
		{
			//Refers to action command method from buttons
			String com = e.getActionCommand();
			
			if(checkClick(com))
				compTurn();
			
			if(gameOver() && !ended)
			{
				updateInfo("Game Over!",1);
				if(winner == 1)
				{
					updateInfo("Plater Wins!");
				}
				else if(winner == 2)
				{
					updateInfo("Computer Wins!");
				}
				ended = true;
			}
								
		}
	}
	
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	private class NewGameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			startGame();
		}
	}
	
	
	
}
