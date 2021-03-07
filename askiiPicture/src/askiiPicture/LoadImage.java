package askiiPicture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoadImage extends Component{

	BufferedImage img;
	 
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
 
    public LoadImage() {
       try {
           img = ImageIO.read(new File("C:\\Users\\Admin\\Pictures\\background\\cake.png"));
       } catch (IOException e) {
    	   System.out.println("Image not found!");
       }
       System.out.println("height: " + img.getHeight());
       System.out.println("Width: " + img.getWidth());
       for(int i = 0; i < 100; i++)
       {
    	   for(int j = 0; j < 100; j++)
    		   img.setRGB(i, j, 255);
       }
       
       
       
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    
    
    
}
