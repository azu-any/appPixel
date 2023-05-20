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
		private static Boolean grid = true;
		
		public Canvas() {		
			setOpaque(true);
			addMouseListener(this);
			addMouseMotionListener(this);
			setBackground(Color.white);
			setOpaque(true);
			
			img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
			gfx = img.createGraphics();
			((Graphics2D) gfx).setBackground(new Color(0,0,0,0));
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, null);
			g.setColor(Color.red);
			g.fillOval(x-5, y-5, 10, 10);   
			if(getGrid() == true) {
				drawSquareGrid(g, 40, Color.black);
			}
			repaint();
			
		}
		
		public static void setImage(BufferedImage image) {
			try {
				img = resizeImage(image, 800, 800);
			} catch (IOException e) {
				e.printStackTrace();
			}
			gfx = img.createGraphics();
			((Graphics2D) gfx).setBackground(new Color(0,0,0,0));
		}
		
		public static void saveImage(String name, String type) {
			
			if(type != "jpg") {
				try {       
					ImageIO.write(img, type, new File(name + "." + type));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				BufferedImage temp = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
		        temp.createGraphics().drawImage(img,0,0,Color.WHITE, null);
				try {       
					ImageIO.write(temp, "JPEG", new File(name + ".jpg"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public Graphics getGraphic() {
			return gfx;
		}
		
		private static int pixel(int p) {
			return p = ((p+1)/20)*20;
		}
		
		private void paintPixel() {
			gfx.fillRect(pixel(x), pixel(y), PIXEL_SIZE, PIXEL_SIZE);
			repaint();
		}
		
		private void ereasePixel() {
			gfx.clearRect(pixel(x), pixel(y), PIXEL_SIZE, PIXEL_SIZE);
		}
		
		private static void drawSquareGrid(Graphics g, int size, Color c) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {                
					g.setColor(c);
					g.drawRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
				}
			} 
		}
		
		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if(gfx.getColor().getAlpha() == 0) {
				System.out.print(0);
				ereasePixel();
			} else {
				System.out.print(gfx.getColor());
				paintPixel();
			}
			
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY(); 
			repaint();
		}
		
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY(); 
			if(gfx.getColor().getAlpha() == 0) {
				System.out.print(0);
				ereasePixel();
			} else {
				System.out.print(gfx.getColor());
				paintPixel();
			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}

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
		
		private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
			Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
			return outputImage;
		}
		
		public static void addGrid() {
			drawSquareGrid(gfx, 40, Color.black);
		}
		public static void addBg() {
			gfx.setColor(Color.WHITE);
			gfx.fillRect(0, 0, 800, 800);
		}


		public Boolean getGrid() {
			return grid;
		}

		public void setGrid(Boolean grid) {
			Canvas.grid = grid;
		}
	}