import javax.swing.*;
import java.awt.event.*;

public class Multiply extends JFrame {

	int width;
	int height; 
	JPanel panel;
	
	JLabel message;
	JLabel message2;
	JTextField textField;
	JTextField textField2;
	JButton button;
	
	public Multiply(int width,int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void makeWindow() {
		
		setTitle("This is the title");
		
		setSize(width, height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildPanel();
		
		add(panel);
		
		setVisible(true);
	}
	
	public void buildPanel()
	{
		message = new JLabel("Give me a number");
		message2 = new JLabel("Give me another number");
		textField = new JTextField(10);
		textField2 = new JTextField(10);
		button = new JButton("Calculate");
		
		button.addActionListener(new ButtonListener());
		
		panel = new JPanel();
		
		panel.add(message);
		panel.add(textField);
		panel.add(message2);
		panel.add(textField2);
		panel.add(button);
	}
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String tf1, tf2;
			double outp;
			
			tf1 = textField.getText();
			tf2 = textField2.getText();
			
			outp = Double.parseDouble(tf1) * Double.parseDouble(tf2);
			
			JOptionPane.showMessageDialog(null, tf1 + " times " + tf2 +" is " + outp);
		}
	}
	
	
	
	
}
