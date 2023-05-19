package appPixel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
	
	
	private static final long serialVersionUID = 1L;
	private int x,y;
	private static BufferedImage img;
	private static Graphics gfx;	
	private static int PIXEL_SIZE = 20;
	
	public Canvas() {		
		setOpaque(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.white);
		
		img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
		gfx = img.createGraphics();

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
		try {
			img = resizeImage(img, 800, 800);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gfx = img.createGraphics();
		drawSquareGrid(40, Color.black);
	}
	
	public static void saveImage(String name, String type) {
		 try {       
	         ImageIO.write(img, type, new File(name + "." + type));
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
	
	public Graphics getGraphic() {
		return gfx;
	}
	
	private static int pixel(int p) {
		 return p = ((p+1)/20)*20;
	}
	
	private void paintPixel() {
		Color c = gfx.getColor();
		gfx.fillRect(pixel(x), pixel(y), 20, 20);
		gfx.setColor(Color.black);
		gfx.drawRect(pixel(x), pixel(y), 20, 20);
		gfx.setColor(c);
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
	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		paintPixel();
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY(); 
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY(); 
		paintPixel();
        repaint();
        
	 }

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		x = e.getX();
		y = e.getY(); 
		repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		x = e.getX();
		y = e.getY(); 
		repaint();
	}
	
	static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}
}

