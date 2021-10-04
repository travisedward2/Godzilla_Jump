package finalProject;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Stair extends JLabel{
	
	public int leftPos,RightPos,x,y;
	
	public Stair() {
	/*	ImageIcon icon=new ImageIcon("image/stair.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());*/  
        
	}
	public Stair(int x, int y) {
	/*	this.x=x;
		this.y=y;
		ImageIcon icon=new ImageIcon("image/stair.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());   */
	}
	
	public int leftX() {
		return this.getX()+10;
	}
	
	public int rightX() {
		return this.getX()+45;
		
	}	
}
