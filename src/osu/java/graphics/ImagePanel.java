package osu.java.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.DrowUtil;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       try {        
    	  System.out.println("DO It");
          image = ImageIO.read(new File("resourses/canvas.png"));
         // image.setRGB(500, 500, Color.green.getRGB());
          DrowUtil.DrowLine(0, 0, 400, 400, image);
          DrowUtil.Brez(500, 10, 40, 600, image);
       } catch (IOException ex) {
    	   System.out.println("ex");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);   
    }

}