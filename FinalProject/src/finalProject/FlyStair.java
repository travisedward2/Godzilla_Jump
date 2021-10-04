package finalProject;

import javax.swing.ImageIcon;


public class FlyStair extends Stair{
	public static boolean flying=false;
	public FlyStair() {
			fly1();
    
	}
	public FlyStair(int x,int y) {
		this();
		this.x=x;
		this.y=y;
	}
	public void fly1() {
		ImageIcon icon=new ImageIcon("image/flystair1.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
	}
	public void fly2() {
		ImageIcon icon=new ImageIcon("image/flystair2.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
        this.setLocation(this.getX(),this.getY()+18);
	}
	
	public void fly(Dragon x) {
		x.reachableHeight=x.currentHeight-1200;
		fly2();
		System.out.print(this.getHeight());
		flying=true;
		x.dragon_fly();
		Gaming.rocket.play();
	}
}
