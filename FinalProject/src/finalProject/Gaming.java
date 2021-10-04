package finalProject;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gaming extends JFrame implements KeyListener,Runnable {
		
		ArrayList<Stair> stairs=new ArrayList<Stair>();
		ArrayList<Thread> threads=new ArrayList<Thread>();
		int jumpOn;
		
		static boolean pauseOrNot=false;
		static boolean gameover=false;
		String musicpath="sounds/";
		static MusicPlayer jump,bgm,collapse,boost,rocket;
		
		public JLabel endBackground,startBackground;
		
		Dragon dragon =new Dragon();
		
		 Pause pause;
		public void restart() {
			if(Pause.rst==true) {
				System.out.println("oh ohoh");
				while(stairs.size()!=0) {
					stairs.get(0).setVisible(false);
					stairs.remove(0);
				}		
				this.initializeStairs();
				dragon.currentHeight=640;
				dragon.reachableHeight=460;
				dragon.speed_y=0;
				dragon.imageState=0;
				dragon.death=false;
				gameover=false;
				dragon.setLocation(10, 500);
				Pause.rst=false;
				pause.scr=0;
				pause.score.setText("Score: "+0);
			}

			
		}
		
		//boolean toMove;
		//下面是當前dragon x方向的移動
		final static int LEFT=0,RIGHT=1;
		int DIR;
		int backgroundMove=0;
		
		//
		int invisibleLine;//使 dragon最多只能到多少高度
		
		/*public void pause() {
			if(pauseOrNot) {
				dragon.setLocation(dragon.getX(), dragon.getY());
			}
		}*/
		
		//Stair x=new Stair();//just for testing
		public void moveableStairs() {
			for(int i=0;i<stairs.size();i++) {
				if(stairs.get(i).getClass().equals(MoveStair.class)) {
					MoveStair x=(MoveStair)stairs.get(i);
					x.move();
				}
			}
		}
		public void end() {
			if(gameover==true) {
				pause.gameover();
			}
		}
		
		public void removeStairs() {
			for(int i=0;i<stairs.size();i++) {
				if(stairs.get(i).y>=650) {
					stairs.get(i).setVisible(false);
					stairs.remove(i);
					repaint();
					//stairs.get(i).enable(false);
					
					
					//System.out.println("remove"+i);
				}
			}
		}
		public void teleportation(){
			if(dragon.leftX()>=450) {
				dragon.setLocation(-50,dragon.getY());
			}
			if(dragon.rightX()<=0) {
				dragon.setLocation(400,dragon.getY());
			}
		}
		public void createStairs() {
			int temp;
			int y=0;
			int x;
				long time = System.currentTimeMillis();
				Random rnd=new Random(time);
				x=rnd.nextInt(12)*35;
				//System.out.print(x+" ");
				temp=rnd.nextInt(100);
				if(temp<10) {
					stairs.add(new FakeStair(x,y));
					this.add(stairs.get(stairs.size()-1));
					stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
					this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
				}
				else if(temp>=10&&temp<30){
					stairs.add(new MoveStair(x,y));
					MoveStair tempStair =(MoveStair) stairs.get(stairs.size()-1);
					threads.add(new Thread(tempStair));
					threads.get(threads.size()-1).start();
					this.add(stairs.get(stairs.size()-1));
					stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
					this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
				}
				else if(temp>=30&&temp<37){
					stairs.add(new JumpStair(x,y));
					this.add(stairs.get(stairs.size()-1));
					stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
					this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
				}
				else if(temp>=37&&temp<42) {
					stairs.add(new FlyStair(x,y));
					this.add(stairs.get(stairs.size()-1));
					stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
					this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
				}
				else {
					stairs.add(new RegularStair(x,y));
					this.add(stairs.get(stairs.size()-1));
					stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
					this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
				}
					
				repaint();
			
			/*for(int j=0;j<stairs.size();j++) {
				this.add(stairs.get(j));
				this.getLayeredPane().add(stairs.get(j),JLayeredPane.PALETTE_LAYER);
				stairs.get(j).setLocation(stairs.get(j).x, stairs.get(j).y);
				repaint();
			}*/
			
			
		}
		
		public void initializeStairs() {
			//int numberInRow=1;
			int y=600;
			int x;
			
			long time = System.currentTimeMillis();
			Random rnd=new Random(time);
			for(int k=0;k<14;k++) {
					x=rnd.nextInt(12)*35;
					if(rnd.nextInt(50)<8) {
						stairs.add(new FakeStair(x,y));
						this.add(stairs.get(stairs.size()-1));
						stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
						this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
					}
					else {
						stairs.add(new RegularStair(x,y));
						this.add(stairs.get(stairs.size()-1));
						stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
						this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
					}
					
					

				y-=40;
			}
			for(int k=0;k<7;k++) {
				stairs.add(new RegularStair(k*60,600));
				this.add(stairs.get(stairs.size()-1));
				stairs.get(stairs.size()-1).setLocation(stairs.get(stairs.size()-1).x, stairs.get(stairs.size()-1).y);
				this.getLayeredPane().add(stairs.get(stairs.size()-1),JLayeredPane.PALETTE_LAYER);
			}
			
			
	/*	for(int j=0;j<stairs.size();j++) {
			this.add(stairs.get(j));
			this.getLayeredPane().add(stairs.get(j),JLayeredPane.PALETTE_LAYER);
			stairs.get(j).setLocation(stairs.get(j).x, stairs.get(j).y);
			repaint();
		}*/
			

		}
		
	/*	public void dragonMove() {
			if(dragon.toMove) {
				
				dragon.setLocation(dragon.getX()+dragon.speed_x,dragon.getY());
			}
			
		}*/
		
	
		
	/*	public void collsionWithStair(Stair x) {
			if((Math.abs(dragon.bottomPosition()-x.getY()))<=2 && dragon.speed_y<0 && inRangeOfStair(x) ) {
				dragon.speed_y=5;
				dragon.currentHeight=x.getY();
				dragon.reachableHeight=dragon.currentHeight-250;
			}
		}
	*/	
		
	/*	public boolean inRangeOfStair(ArrayList<Stair> stairs) {
			if((dragon.rightX()>stairs.get(i).leftX()&&dragon.leftX()<stairs.get(i).leftX())||(dragon.leftX()>stairs.get(i).leftX()&&dragon.rightX()<x.rightX())||(dragon.rightX()>stairs.get(i).rightX()&&dragon.leftX()<stairs.get(i).rightX())) {
				return true;	
			}
			return false;
		}*/
		
		public void collisionWithStair(ArrayList<Stair> stairs) {
			int i;
			
			for( i=0 ;i<stairs.size();i++) {
				if((Math.abs(dragon.bottomPosition()-stairs.get(i).getY()))<5 && dragon.speed_y<0&&((dragon.rightX()>stairs.get(i).leftX()&&dragon.leftX()<stairs.get(i).leftX())||(dragon.leftX()>stairs.get(i).leftX()&&dragon.rightX()<stairs.get(i).rightX())||(dragon.rightX()>stairs.get(i).rightX()&&dragon.leftX()<stairs.get(i).rightX()))) {
				//	System.out.print(i);
					dragon.speed_y=5;
					dragon.currentHeight=stairs.get(i).getY();
					dragon.reachableHeight=dragon.currentHeight-180;
					jumpOn=i;
					if(dragon.imageState==0||dragon.imageState==3) dragon.dragon_left();
					
					
					if(stairs.get(i).getClass().equals(RegularStair.class)||stairs.get(i).getClass().equals(MoveStair.class)) 
						jump.play();
					else if(stairs.get(i).getClass().equals(FakeStair.class)){
						collapse.play();
						FakeStair x=(FakeStair)stairs.get(i);
						x.collapse();
						stairs.remove(i);
					}
					else if(stairs.get(i).getClass().equals(JumpStair.class)) {
						JumpStair x=(JumpStair)stairs.get(i);
						x.boost(dragon);
					}
					else if(stairs.get(i).getClass().equals(FlyStair.class)) {
						FlyStair x=(FlyStair)stairs.get(i);
						x.fly(dragon);
					}
					
					
					for(int j=0;j<10 ;j++) {
						if(j<5)
							dragon.setLocation(dragon.getX(), dragon.getY()+1);
						
						try {
							Thread.sleep(6);
						 } catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				}
			}
			//if(i>=0&&i<stairs.size()&&inRangeOfStair(stairs)) {

				
				
		//	}	
			//System.out.println(jumpOn+" "+dragon.reachableHeight);
		}
		
		public void moveStairs() {//可達最高高度太高 要把
			int temp;
			//moveableStairs();
			if(dragon.reachableHeight<300 && (dragon.getY()-300)<5) {
			
				while(pauseOrNot==false) {
					
					//temp=dragon.speed_y;
				//	dragon.speed_y=1;
					collisionWithStair(stairs);
					
										
					for(int i=0;i<stairs.size();i++) {
						stairs.get(i).y++;
						stairs.get(i).setLocation(stairs.get(i).getX(), stairs.get(i).getY()+1);
						
					}
					
					backgroundMove++;
					pause.scr++;
					pause.score.setText("Score:"+pause.scr);
					if(backgroundMove%60==1) {
						createStairs();
					}
					if((300-dragon.reachableHeight)==backgroundMove) {

						dragon.reachableHeight=dragon.reachableHeight+backgroundMove;
						dragon.currentHeight=dragon.currentHeight+backgroundMove;
						backgroundMove=0;
						//dragon.reachableHeight=640;
						if(FlyStair.flying||JumpStair.jumping) {
							dragon.dragon_left();
						}
						
						dragon.speed_y=-3;
						break;
					}
					try {
						if(JumpStair.jumping==true||FlyStair.flying==true) {
							Thread.sleep(1);
						}
						else {
							Thread.sleep(5);
						}
						
					 } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if(pauseOrNot==false) {
					JumpStair.jumping=false;
					FlyStair.flying=false;
				}
			
			}

			
			
		}
		
		public void dragonJump() {
			if(dragon.speed_y>0) {//往上跳
				//System.out.println(" reach "+dragon.reachableHeight);
				dragon.setLocation(dragon.getX(),dragon.getY()-dragon.speed_y );
				
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<180) dragon.speed_y=9;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<160) dragon.speed_y=9;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<140) dragon.speed_y=7;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<120) dragon.speed_y=6;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<100) dragon.speed_y=5;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<80) dragon.speed_y=4;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<60) dragon.speed_y=3;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<40) dragon.speed_y=2;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<30) dragon.speed_y=2;
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<20) dragon.speed_y=1;
				if(dragon.currentHeight==640) dragon.speed_y=5;
				
				
			
				if(Math.abs(dragon.getY()-dragon.reachableHeight)<=5) {
					//if(Math.abs(dragon.getY()-300)<5) {						
					//}														
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dragon.speed_y=-1;//變向
				}
				
				
			}
				
			else if(dragon.speed_y<=0) { //往下跳
				dragon.setLocation(dragon.getX(),dragon.getY()-dragon.speed_y );
				
				if(-(dragon.getY()-dragon.currentHeight)<160) dragon.speed_y=-2;
				if(-(dragon.getY()-dragon.currentHeight)<140) dragon.speed_y=-3;
				if(-(dragon.getY()-dragon.currentHeight)<120) dragon.speed_y=-4;
				if(-(dragon.getY()-dragon.currentHeight)<100) dragon.speed_y=-5;
				if(-(dragon.getY()-dragon.currentHeight)<80) dragon.speed_y=-6;
				if(-(dragon.getY()-dragon.currentHeight)<50) dragon.speed_y=-6;
				if(-(dragon.getY()-dragon.currentHeight)<40) dragon.speed_y=-7;
				if(-(dragon.getY()-dragon.currentHeight)<30) dragon.speed_y=-7;
				if(-(dragon.getY()-dragon.currentHeight)<20) dragon.speed_y=-7;
				if(-(dragon.getY()-dragon.currentHeight)<10) dragon.speed_y=-8;
				if(-(dragon.getY()-dragon.currentHeight)<5) dragon.speed_y=-9;
				//if(dragon.currentHeight==640) dragon.speed_y=-3;
				//if(Math.abs(dragon.getY()-670)<5) dragon.speed_y=5;//自救
				
			}
				
				repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		public void bgchange() {
			if(pause.startbgon==true) {
				this.startBackground.setVisible(true);
				this.startBackground.setEnabled(true);
			}
			else {
				this.startBackground.setVisible(false);
				this.startBackground.setEnabled(false);
			}
			if(pause.endbgon==true) {
				this.endBackground.setVisible(true);
				this.endBackground.setEnabled(true);
			}
			else {
				this.endBackground.setVisible(false);
				this.endBackground.setEnabled(false);
			}

		}
		
		public Gaming(){//constructor
			
			
			//bgm
			try {
				jump = new MusicPlayer(new File(musicpath+"jump.wav"));
				bgm=new MusicPlayer(new File(musicpath+"Monster.wav"));
				rocket=new MusicPlayer(new File(musicpath+"rocket.wav"));
				collapse=new MusicPlayer(new File(musicpath+"break.wav"));
				boost= new MusicPlayer(new File(musicpath+"Spring.wav"));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bgm.bgm();
			
			
			
			//add background
			 ImageIcon icon=new ImageIcon("image/background.png");
			 JLabel background= new JLabel();
			 background.setIcon(icon);
			 background.setBounds(0,0,450,690);		 
		    this.getLayeredPane().add(background,JLayeredPane.DEFAULT_LAYER);
		    
	     //start background
		     ImageIcon startbg=new ImageIcon("image/startBG.png");
			 startBackground= new JLabel();
			 startBackground.setIcon(startbg);
			 startBackground.setBounds(0,0,450,690);		 
		     this.getLayeredPane().add(startBackground,JLayeredPane.MODAL_LAYER);
		     startBackground.setVisible(false);
		     
		   //END BACK
		     ImageIcon endbg=new ImageIcon("image/endBG.png");
			 endBackground= new JLabel();
			 endBackground.setIcon(endbg);
			 endBackground.setBounds(0,0,450,690);		 
		     this.getLayeredPane().add(endBackground,JLayeredPane.MODAL_LAYER);
		     endBackground.setVisible(false);
		     
		   //start panel
		 /*    start=new Start();
		     start.setVisible(true);
			 this.getLayeredPane().add(start,JLayeredPane.POPUP_LAYER);
			 ;
		   
		    
		    

			 
			 
			 
		      end=new End();
			  this.getLayeredPane().add(start,JLayeredPane.POPUP_LAYER);
			  end.setVisible(false);*/
		    
		    

		    //pause panel 
			 pause=new Pause();
			 this.getLayeredPane().add(pause,JLayeredPane.DRAG_LAYER);
			 pause.setVisible(true);
			 pause.start();
			 pauseOrNot=true;
			 
			 

			 
			 
			 
			  
			
		   //主角入場
			this.add(dragon);
			this.getLayeredPane().add(dragon, JLayeredPane.MODAL_LAYER);
			dragon.setLocation(10,690);
			dragon.speed_y=4;
			threads.add(new Thread(dragon));
			threads.get(threads.size()-1).start();
			
			
			//Stair 測試
			initializeStairs();
			
			
			//設定JFrame
			this.setResizable(false);
			this.setLayout(null);
			this.setVisible(true);
			this.setSize(450,720);
			this.addKeyListener(dragon);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			
			
			
		}
		
	public static void main (String[] args) {
		
		
		Gaming game=new Gaming();
		
		Thread gam= new Thread(game);
		gam.start();
		
			
	}	
		

	
	
	
	public void run() {

		
			while(true) {//一直檢查狀態
				//System.out.println("let me out ");
				bgchange();

				restart();
				end();
				this.setFocusable(true);

				if(pauseOrNot==false&&dragon.death==false) {
					
					//System.out.println("here");
					removeStairs();
					collisionWithStair(stairs);
					dragonJump();
					//moveableStairs();
					teleportation();
					/*if(dragon.reachableHeight<690) {
						dragonMove();
					}*/
					//dragon.speed_x/=2;
					moveStairs();
					
					
					if(dragon.getY()>700) {
						dragon.death=true;
					}
				}
				else if(pauseOrNot==true){
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(dragon.death==true&&gameover==false) {
					dragon.dragon_death();
					dragon.speed_y=0;
					dragon.deathAnime();
					gameover=true;
					pauseOrNot=true;
					
				}
					
				
		}	
		
		

		
		
	}


	public void keyTyped(KeyEvent e) {
	
	
		
	}

	
	public void keyPressed(KeyEvent e) {
		
	/*	if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			dragon.toMove=true;
			dragon.speed_x=-2;
			DIR=LEFT;
			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			dragon.toMove=true;
			dragon.speed_x=2;
			DIR=RIGHT;
		}
		*/
	}

	public void keyReleased(KeyEvent e) {
		
	//	if((e.getKeyCode()==KeyEvent.VK_RIGHT&&DIR!=LEFT )|| (e.getKeyCode()==KeyEvent.VK_LEFT&&DIR!=RIGHT))
		//	dragon.toMove=false;
	}

}
