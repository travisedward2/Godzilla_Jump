package finalProject;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class MoveStair extends Stair implements Runnable{
	int tempx;
	int range=0;
	public MoveStair() {
		ImageIcon icon=new ImageIcon("image/movestair.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
	}
	public MoveStair(int x,int y) {
		this();
		this.x=x;
		this.y=y;
		tempx=x;
	}
	
	public void move() {
		if(this.getX()<440&&range>=0) {
			this.setLocation(this.getX()+1,this.getY() );
			//this.x=this.getX()+1;
			//this.y=this.getY();
			range+=1;
			if(range>200) {
				range=-1;
			}
		}
		else if(this.getX()>0&&range<0){
			this.setLocation(this.getX()-1,this.getY() );
			//this.x=this.getX()-1;
			//this.y=this.getY();
			range-=1;
			if(range<-200) {
				range=0;
			}
		}
		else {
			range=-range;
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if(Gaming.pauseOrNot==false) {
				move();
	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
	}
}	
