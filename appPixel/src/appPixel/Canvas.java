package appPixel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Canvas extends JPanel implements MouseMotionListener{
	
	
	private static final long serialVersionUID = 1L;
	private int x,y;
	private static BufferedImage img;
	private static Graphics gfx;	
	private static int PIXEL_SIZE = 20;
	private List<int[]> xyList; 
	
	ColorPixel drawLine = new ColorPixel();
	
	
	public Canvas() {		
		setOpaque(true);
		addMouseMotionListener(this);
		setBackground(Color.white);
		
		 xyList = new ArrayList<int[]>();
		
		img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
		gfx = img.createGraphics();
		gfx.setColor(Color.white);
		gfx.fillRect ( 0, 0, 800, 800);
		drawSquareGrid(40, Color.black);
	}
	 
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	    g.setColor(Color.red);
	    g.fillOval(x-5, y-5, 10, 10);   
	    repaint();
	    
	}
	
	public static void setImage(Image image) {
		img = (BufferedImage) image;
		gfx = img.createGraphics();
	}
	public void paintPixel(int x, int y) {
		x = pixelX(x);
		y = pixelY(y);
		gfx.fillRect(x, y, 20, 20);
		gfx.setColor(Color.black);
		gfx.drawRect(x, y, 20, 20);
		repaint();
	}
	
	 private static void drawSquareGrid(int size, Color c) {
	      for (int i = 0; i < size; i++) {
	          for (int j = 0; j < size; j++) {                
	              gfx.setColor(c);
	              gfx.drawRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
	          }
	      } 
	  }
	 
	 public static void saveImage(String name, String type) {
		 try {       
	         ImageIO.write(img, type, new File(name + "." + type));
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
	 
	 private static int pixelX(int x) {
		 return x = ((x+1)/20)*20;
	}
	 
	private static int pixelY(int y) {
		 return y = ((y+1)/20)*20;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX(); //get the x and y coordinates of
		y = e.getY(); //the mouse click point	
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		drawLine.addPoint(e.getX(), e.getY());
		//addPoint(x, y);
		repaint();
	}
	
	@Override
	 public void mouseDragged(MouseEvent e) {
		drawLine.addPoint(e.getX(), e.getY());
		drawLine.draw(gfx);;
		//addPoint(x, y);
		//draw();
        repaint();
        
	 }
	
	public Graphics getGraphic() {
		return gfx;
	}
	
}

