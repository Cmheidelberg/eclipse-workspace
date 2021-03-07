import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiCalc extends JFrame{

	JPanel mainPanel;
	JPanel buttonPanel;
	JPanel textPanel;
	
	JTextField textField;
	
	JButton clear;
	
	JButton add;
	JButton subtract;
	JButton multiply;
	JButton divade;
	
	JButton zero;
	JButton one;
	JButton two;
	JButton three;
	JButton four;
	JButton five;
	JButton six;
	JButton seven;
	JButton eight;
	JButton nine;
	
	private final int WIDTH = 600;
	private final int HEIGHT = 800;
	
	
	public GuiCalc()
	{
		setTitle("Calc");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new GridLayout());
		
		
		
	}
}
