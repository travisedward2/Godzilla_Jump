package finalProject;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
public class Pause extends JPanel implements MouseListener,ActionListener{
	public JLabel start,pause,pause_dragon,score;
	public long scr;
	public static boolean startbgon,endbgon,stop,rst;
	JButton startbtn,exitbtn,restartbtn;
	
	public Pause(){

		
		//System.out.println("ffff");
		 //ImageIcon bg=new ImageIcon("image/pause_background.png");
		 //img=bg.getImage();
		// this.img=img;
		 startbgon=false;
		 endbgon=false;
		 stop=false;
		 
		 this.setBounds(0, 0, 450, 690);
		 this.setLayout(null);
		 this.setBackground(null);
		 this.setVisible(false);
		 this.setOpaque(false);
		
		//this.setBackground(Color.red);
		 ImageIcon rst=new ImageIcon("image/restartbtn.png");
		 restartbtn= new JButton();
		 restartbtn.addActionListener(this);
		 restartbtn.setIcon(rst);
		 restartbtn.setBounds(130,500,190,55);
		 restartbtn.setVisible(false);
		 restartbtn.setEnabled(false);	
		 this.add(restartbtn);
		 
		 ImageIcon icon=new ImageIcon("image/pause.png");
		 pause= new JLabel();
		 pause.addMouseListener(this);
		 pause.setIcon(icon);
		 pause.setBounds(380,5,50,50);
		 this.add(pause);
		 
		 //ImageIcon scr=new ImageIcon("image/pause.png");

		// pause.addMouseListener(this);
		// pause.setIcon(icon);
		 score= new JLabel("Score:");
		 score.setBounds(10,0,200,50);
		 score.setFont(new Font("Serif",Font.BOLD,20));
		 this.add(score);
		 
		 ImageIcon icon2=new ImageIcon("image/start.png");
		 start= new JLabel();
		 start.addMouseListener(this);
		 start.setIcon(icon2);
		 start.setBounds(380,5,50,50);
		 start.setVisible(false);
		 start.setEnabled(false);		 
		 this.add(start);
		 
		 
		 ImageIcon icon3=new ImageIcon("image/pause_dragon.png");
		 pause_dragon= new JLabel();
		//pause_dragon.addMouseListener(this);
		 pause_dragon.setIcon(icon3);
		 pause_dragon.setBounds(60,200,300,200);
		 pause_dragon.setVisible(false);
		 pause_dragon.setEnabled(false);		 
		 this.add(pause_dragon);
		 
		 ImageIcon st=new ImageIcon("image/startbtn.png");
		 startbtn= new JButton();
		 startbtn.addActionListener(this);
		 startbtn.setIcon(st);
		 startbtn.setBounds(130,500,190,55);
		 startbtn.setVisible(false);
		 startbtn.setEnabled(false);	
		 this.add(startbtn);
		 
		 
		 ImageIcon exit=new ImageIcon("image/exitbtn.png");
		 exitbtn= new JButton();
		 exitbtn.addActionListener(this);
		 exitbtn.setIcon(exit);
		 exitbtn.setBounds(130,580,190,55);
		 exitbtn.setVisible(false);
		 exitbtn.setEnabled(false);		 
		 this.add(exitbtn);
		 	 
		 
	}
	//public JLabel start,pause,pause_dragon,score;
	//public static boolean startbgon,endbgon;
	//JButton startbtn,exitbtn,restartbtn;
	public void start() {
		startbgon=true;
		endbgon=false;
		
		
		this.exitbtn.setVisible(true);
		this.startbtn.setVisible(true);
		
		this.pause.setVisible(false);
		this.pause_dragon.setVisible(false);
		this.restartbtn.setVisible(false);
		
		this.exitbtn.setEnabled(true);
		this.startbtn.setEnabled(true);
		
		this.pause.setEnabled(false);
		this.pause_dragon.setEnabled(false);
		this.restartbtn.setEnabled(false);
						
	}
	
	public void gameover() {
		endbgon=true;
		startbgon=false;
		this.exitbtn.setVisible(true);
		this.startbtn.setVisible(false);
		
		this.pause.setVisible(false);
		this.pause_dragon.setVisible(false);
		this.restartbtn.setVisible(true);
		
		this.exitbtn.setEnabled(true);
		this.startbtn.setEnabled(false);
		
		this.pause.setEnabled(false);
		this.pause_dragon.setEnabled(false);
		this.restartbtn.setEnabled(true);
	}
	
	public void pause() {
		Gaming.pauseOrNot=false;
		endbgon=false;
		startbgon=false;
		this.exitbtn.setVisible(false);
		this.startbtn.setVisible(false);
		
		this.pause.setVisible(true);
		this.pause_dragon.setVisible(false);
		this.restartbtn.setVisible(false);
		
		this.exitbtn.setEnabled(false);
		this.startbtn.setEnabled(false);
		
		this.pause.setEnabled(true);
		this.pause_dragon.setEnabled(false);
		this.restartbtn.setEnabled(false);
		
	}

	/*protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 450, 690, this);
    }*/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Gaming.pauseOrNot==false) {
			Gaming.pauseOrNot=true;
			pause.setEnabled(false);
			pause.setVisible(false);
			start.setEnabled(true);
			start.setVisible(true);
			pause_dragon.setEnabled(true);
			pause_dragon.setVisible(true);
			
		}
		else {
			Gaming.pauseOrNot=false;
			pause.setEnabled(true);
			pause.setVisible(true);
			start.setEnabled(false);
			start.setVisible(false);
			pause_dragon.setEnabled(false);
			pause_dragon.setVisible(false);
		}
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(startbtn)) {
			//stop=false;
			pause();
			Gaming.pauseOrNot=false;
		}
		if(e.getSource().equals(exitbtn)) {
			System.exit(0);
		}
		if(e.getSource().equals(restartbtn)) {
			rst=true;
			Gaming.pauseOrNot=false;
			Gaming.gameover=false;

			pause();
		}
		
	}
	
	
	
}
