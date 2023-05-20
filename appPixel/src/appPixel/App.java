package appPixel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private int width, height;
	private JPanel contentPane;
	private static Canvas canvas;
	private ColorPicker rgb;
	private Colors colors;
	private Menu menu;
	
	public App(int width, int height) {
	
		this.width = width;
		this.height = height;				
		
		components();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // al cerrar la ventana se detiene el programa
		setSize(width, height);							// tamaño de la ventana
		setLocationRelativeTo(null);					// centra la ventana
		setLayout(null);								// elimina plantillas
		setResizable(true);							// no permite modificar el tamaño de la ventana
		setVisible(true);								// muestra la ventana	
		getContentPane().setBackground(new Color(54, 54, 54));
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

		colors.setBounds(10, 500, 300, 200);
		add(colors);
		
		menu.setBounds(1200, 10, 400, 400);
		add(menu);
		
		add(contentPane);
	}
	
	public static void shBg() {
		if(canvas.isOpaque() == true) {
			canvas.setOpaque(false);
		} else {
			canvas.setOpaque(true);
		}
	}
	
	public static void shGrid() {
		if(canvas.getGrid() == true) {
			canvas.setGrid(false);
		} else {
			canvas.setGrid(true);
		}
	}

	public void mouseClicked(MouseEvent e) {
		Color c = rgb.getColor();
		canvas.getGraphic().setColor(c);
		colors.setColor(c);
		
	}

	public void mousePressed(MouseEvent e) {
		Color c = rgb.getColor();
		canvas.getGraphic().setColor(c);
		colors.setColor(c);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}