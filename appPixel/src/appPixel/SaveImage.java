package appPixel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SaveImage extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel nameLbl, typeLbl; 
	private JTextField name;
	private JComboBox<String> type;
	private JCheckBox grid, bg;
	private JButton ok, cancel;
	private String dir;
	
	public SaveImage() {
		setLayout(null);  
	    setSize(400,200);
	    setLocationRelativeTo(null);
	    
	    components();
	    
	}
	    
	private void components() {
		
		nameLbl = new JLabel("Name:");
		name = new JTextField();
		typeLbl = new JLabel("Type:");
		String[] ext = {"png", "jpg", "gif"};
		type = new JComboBox<String>(ext);
		grid = new JCheckBox("Draw grid");
		bg = new JCheckBox("Draw white background");
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		dir = "";
		
		nameLbl.setBounds(30, 20, 40, 20);
		name.setBounds(75, 20, 150, 20);
		typeLbl.setBounds(240, 20, 30, 20);
		type.setBounds(275, 20, 80, 20);
		
		ok.setBounds(100, 120, 80, 20);
		ok.addActionListener(this);
		
		cancel.setBounds(200, 120, 80, 20);
		cancel.addActionListener(this);
		
		grid.setBounds(40, 60, 100, 20);
		bg.setBounds(150, 60, 300, 20);
		
		add(nameLbl);
		add(name);
	    add(typeLbl);
	    add(type);
	    add(cancel);
	    add(ok);
	    add(grid);
	    add(bg);
	}
	
	public void saveDialog(String s) {
		setVisible(true);
		this.dir = s + "/";
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source == ok) {
			if(name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "You must enter a name for the image", "Missing name", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				if(bg.isSelected()) {
					Canvas.addBg();
				}
				if(grid.isSelected()) {
					Canvas.addGrid();
				}
				Canvas.saveImage(dir + name.getText(), type.getSelectedItem().toString());
				Menu.setFileName(name.getText());
				Menu.setType(type.getSelectedItem().toString());
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Image has been saved as \"" + name.getText() + "." + type.getSelectedItem().toString() +"\"", "Saved", JOptionPane.PLAIN_MESSAGE);
			}	
		} else {
			setVisible(false);
		}
		
	}
} 
