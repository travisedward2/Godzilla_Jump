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

public class Dragon extends JLabel implements KeyListener,Runnable {
		
		public int imageState;
		public int speed_y=0;
		public int speed_x=0;
		public int currentHeight=640;
		public int reachableHeight=460;
		public int leftPos,RightPos;
		public boolean toMove;
		public int DIR;
		public boolean death=false;
		
		public void dragonMove() {
			if(this.toMove) {
				
				this.setLocation(this.getX()+this.speed_x,this.getY());
			}
			
		}
	
		public Dragon() {
			dragon_up();
	        
		}
		public void dragon_up() {
			 ImageIcon icon=new ImageIcon("image/dragon_up.png");
			 this.imageState=0;
		     this.setIcon(icon);
		     this.setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		public void dragon_left() {
			this.imageState=1;
			 ImageIcon icon=new ImageIcon("image/dragon_left.png");
		     this.setIcon(icon);
		     this.setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		public void dragon_right() {
			this.imageState=2;
			 ImageIcon icon=new ImageIcon("image/dragon_right.png");
		     this.setIcon(icon);
		     this.setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		
		public void dragon_fly() {
			this.imageState=3;
			 ImageIcon icon=new ImageIcon("image/fly.png");
		     this.setIcon(icon);
		     this.setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		public void dragon_death() {
			this.imageState=4;
			 ImageIcon icon=new ImageIcon("image/death.png");
		     this.setIcon(icon);
		     this.setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		public int bottomPosition() {
			
			return this.getY()+47;
		}
		
		public int leftX() {
			return this.getX();
		}
		
		public int rightX() {
			return this.getX()+62;
		}
		
		
		public void deathAnime() {
				int range=0;
				while(true) {					
					if(range>300) {
						break;
					}
					else {
						this.setLocation(this.getX(),this.getY()-1);
						range++;
					}
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while(true) {
					if(this.getY()>700) {
						break;
					}
					else {
						this.setLocation(this.getX(),this.getY()+2);
					}
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			//System.out.println("ok");
			if(e.getKeyCode()==KeyEvent.VK_LEFT&&Gaming.pauseOrNot==false&&this.death==false) {
				//System.out.println("in");
				toMove=true;
				this.speed_x=-2;
				this.DIR=LEFT;
				if(JumpStair.jumping==false&&FlyStair.flying==false) {
					this.dragon_left();
				}
				
				
				
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT&&Gaming.pauseOrNot==false&&this.death==false) {
				toMove=true;
				this.speed_x=2;
				this.DIR=RIGHT;
				if(JumpStair.jumping==false&&FlyStair.flying==false) {
					this.dragon_right();
				}		
				
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if((e.getKeyCode()==KeyEvent.VK_RIGHT&&DIR!=LEFT )|| (e.getKeyCode()==KeyEvent.VK_LEFT&&DIR!=RIGHT))
				toMove=false;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				this.dragonMove();
				//this.dragonJump();
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}

		
}
