package appPixel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame implements ActionListener , MouseListener , ChangeListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	Canvas canvas;
	ColorPicker rgb;
	Colors colors;
	Menu menu;
	static Graphics g;	
	static BufferedImage img;
	
	int width, height, x, y;
	
	public App(int width, int height) {
	
		this.width = width;
		this.height = height;				
		
		components();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // al cerrar la ventana se detiene el programa
		setSize(width,height);							// tamaño de la ventana
		setLocationRelativeTo(null);					// centra la ventana
		setLayout(null);								// elimina plantillas
		setResizable(false);							// no permite modificar el tamaño de la ventana
		setVisible(true);								// muestra la ventana	
	}
			
	private void components()
	{
		contentPane = new JPanel();
		canvas 		= new Canvas();
		rgb 		= new ColorPicker();
		colors 		= new Colors();
		menu 		= new Menu();
		
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(54, 54, 54));
		contentPane.setBounds(0,0,width,height);

		canvas.setBounds(400, 10, 800,800);//---	
		canvas.addMouseListener(this);
		
		add(canvas);
		
		rgb.setBounds(10, 0, 320, 500);
		add(rgb);

		colors.setBounds(10, 500, 300, 100);
		add(colors);
		
		menu.setBounds(1200, 10, 400, 400);
		add(menu);
		
		add(contentPane);
	}
	
	public void actionPerformed(ActionEvent e) {}
		
	private static int pixelX(int x) {
		 return x = ((x+1)/20)*20;
	}
	 
	private static int pixelY(int y) {
		 return y = ((y+1)/20)*20;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		Color c = rgb.getColor();
		
		canvas.getGraphic().setColor(c);
		canvas.paintPixel(x, y);
		
		colors.setColor(c);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		Color c = rgb.getColor();
		canvas.getGraphic().setColor(c);
		//canvas.addPoint(pixelX(x), pixelY(y));
		colors.setColor(c);
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		//canvas.addPoint(pixelX(x), pixelY(y));
        
	 }

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void stateChanged(ChangeEvent e) {
	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}