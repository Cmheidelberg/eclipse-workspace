import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorGUI extends JFrame {

	private JLabel message;
	private JLabel message2;
	
	private JButton redButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton defaultButton;
	
	private JPanel panel;
	
	private int width = 500;
	private int height = 325;
	
	private Color backgroundColor;
	private Color messageColor;
	
	private String messageColorStr;
	
	public ColorGUI()
	{
		setTitle("Colors!");
		
		setSize(width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		message = new JLabel("Click a button to select a color");
		message2 = new JLabel("Current Color: None");
		redButton = new JButton("Red");
		blueButton = new JButton("Blue");
		yellowButton = new JButton("Yellow");
		defaultButton = new JButton("Default");
		
		redButton.addActionListener(new RedButtonListener());
		blueButton.addActionListener(new BlueButtonListener());
		yellowButton.addActionListener(new YellowButtonListener());
		defaultButton.addActionListener(new DefaultButtonListener());
		
		panel = new JPanel();
		panel.add(message);
		panel.add(redButton);
		panel.add(blueButton);
		panel.add(yellowButton);
		panel.add(defaultButton);
		panel.add(message2);
		
		add(panel);
		
		setVisible(true);
	}
	
	private class RedButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			backgroundColor = Color.RED;
			messageColor = Color.YELLOW;
			messageColorStr = "Red";
			updateColors();
		}
	}
	
	private class BlueButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			backgroundColor = Color.BLUE;
			messageColor = Color.RED;
			messageColorStr = "Blue";
			updateColors();
		}
	}
	
	private class YellowButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			backgroundColor = Color.YELLOW;
			messageColor = Color.BLUE;
			messageColorStr = "Yellow";
			updateColors();
		}
	}
	
	private class DefaultButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			backgroundColor = Color.LIGHT_GRAY;
			messageColor = Color.BLACK;
			messageColorStr = "None";
			updateColors();
		}
	}
	
	private void updateColors()
	{
		panel.setBackground(backgroundColor);
		message.setForeground(messageColor);
		message2.setForeground(messageColor);
		message2.setText("Current Color: " + messageColorStr);
	}
	
	
}
