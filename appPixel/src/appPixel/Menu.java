package appPixel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private static JButton saveBtn, saveAsBtn, openBtn;
	private static String fileName, type;


	public Menu(){
		components();
		
		setSize(400, 400);
		setLayout(null);
		setBackground(new Color(54, 54, 54));
		setVisible(true);				
	}
		
	
	private void components() {
		saveBtn 	= new JButton("Save");
		saveAsBtn 	= new JButton("Save as");
		openBtn		= new JButton("Open");
		fileName 	= "img";
		type 		= "jpg";
		
		saveBtn.setBounds(20, 20, 120, 30);
		saveBtn.setBackground(new Color(237, 238, 240));
		saveBtn.setOpaque(true);
		saveBtn.setBorderPainted(false);
		saveBtn.setForeground(Color.black);
		saveBtn.setFont(new Font("Serif", Font.PLAIN, 20));
		saveBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Canvas.saveImage(fileName, type);
		    	saved();
		    }
		});
		
		saveAsBtn.setBounds(20, 70, 120, 30);
		saveAsBtn.setBackground(new Color(237, 238, 240));
		saveAsBtn.setOpaque(true);
		saveAsBtn.setBorderPainted(false);
		saveAsBtn.setForeground(Color.black);
		saveAsBtn.setFont(new Font("Serif", Font.PLAIN, 20));
		saveAsBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	String s = name();
		    	if(s == null)
		    		return;
		    	fileName = s;
		    	
			   	s = type();
			   	if(s == null)
		    		return;
			    type = s;
			    
			   	Canvas.saveImage(fileName, type);
			   	saved();
		    	
		    }
		});
		
		openBtn.setBounds(20, 120, 120, 30);
		openBtn.setBackground(new Color(237, 238, 240));
		openBtn.setOpaque(true);
		openBtn.setBorderPainted(false);
		openBtn.setForeground(Color.black);
		openBtn.setFont(new Font("Serif", Font.PLAIN, 20));
		openBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	JFileChooser fc = new JFileChooser(".");
		    	
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "PNG, JPG & GIF Images", "png", "jpg", "gif");
				fc.setFileFilter(filter);
		    	int returnVal = fc.showOpenDialog(getParent());
		    	
		    	if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		File file = fc.getSelectedFile();
		    		String name = file.getName();
		    		
		    		Image img = null;
					try {
						img = ImageIO.read(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		    		Canvas.setImage(img);
		    		
		    		JOptionPane.showMessageDialog(null, "You opened " + name, "Open image", JOptionPane.PLAIN_MESSAGE);
		    		
		    		String[] names = name.split("\\.");	    		
		    		fileName = names[0];
		    		type = names[1];
		    	}
		    	
		    }
		});
		
		add(saveBtn);
		add(saveAsBtn);
		add(openBtn);
	}
	
	private void saved() {
    	JOptionPane.showMessageDialog(null, "Image has been saved as \"" + fileName + "." + type +"\"", "Saved", JOptionPane.PLAIN_MESSAGE);
	}

	private String name() {		
    	return JOptionPane.showInputDialog(null, "Name:", "Image name", JOptionPane.PLAIN_MESSAGE);
	}
	
	private String type() {
		String[] extensions = {"png", "jpg", "gif"};
		
		return (String) JOptionPane.showInputDialog(null, "Select format:", "Image format", JOptionPane.PLAIN_MESSAGE, null, extensions, extensions[0]);
	}
}
