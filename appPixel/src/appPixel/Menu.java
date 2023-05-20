package appPixel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private JButton saveBtn, saveAsBtn, openBtn;
	private static String fileName, type;
	private JCheckBox grid, bg;


	public Menu(){
		components();
		
		setSize(200, 400);
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
		
		grid = new JCheckBox("Grid");
		grid.setBounds(20, 170, 100, 20);
		grid.setBackground(new Color(237, 238, 240));
		grid.setOpaque(false);
		grid.setBorderPainted(false);
		grid.setForeground(Color.white);
		grid.setFont(new Font("Serif", Font.PLAIN, 20));
		grid.setSelected(true);
		grid.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	App.shGrid();
		    }
		});
		
		bg = new JCheckBox("White Background");
		bg.setBounds(20, 200, 200, 25);
		bg.setBackground(new Color(237, 238, 240));
		bg.setOpaque(false);
		bg.setBorderPainted(false);
		bg.setForeground(Color.white);
		bg.setFont(new Font("Serif", Font.PLAIN, 20));
		bg.setSelected(true);
		bg.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	App.shBg();
		    }
		});
		
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
		    	JOptionPane.showMessageDialog(null, "Image has been saved as \"" + fileName + "." + type +"\"", "Saved", JOptionPane.PLAIN_MESSAGE);
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
		    	
		    	JFileChooser fc = new JFileChooser(".");
		    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	
	            int returnVal = fc.showDialog(getParent(), "Select directory");
	            String dir = null;
	            
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	 File fdir = fc.getSelectedFile();
	            	 dir = fdir.getAbsolutePath();
	            	 
	            	 SaveImage save = new SaveImage();
	            	 save.saveDialog(dir);
				}
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
				fc.setAcceptAllFileFilterUsed(false);
		    	int returnVal = fc.showOpenDialog(getParent());
		    	
		    	if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		File file = fc.getSelectedFile();
		    		String name = file.getName();
		    		
		    		BufferedImage img = null;
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
		add(grid);
		add(bg);
	}
	
	public static void setFileName(String s) {
		fileName = s;
	}
	public static void setType(String s) {
		type = s;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
