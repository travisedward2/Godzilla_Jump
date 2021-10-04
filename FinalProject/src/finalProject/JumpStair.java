package finalProject;

import javax.swing.ImageIcon;

public class JumpStair extends Stair{
	public static boolean jumping=false;
	public JumpStair() {
		spring1();
    
	}
	public JumpStair(int x,int y) {
		this();
		this.x=x;
		this.y=y;
	}
	public void spring1() {
		ImageIcon icon=new ImageIcon("image/jumpstair1.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
	}
	public void spring2() {
		ImageIcon icon=new ImageIcon("image/jumpstair2.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
        this.setLocation(this.getX(),this.getY()-15);
	}
	
	public void boost(Dragon x) {
		x.reachableHeight=x.currentHeight-500;
		spring2();
		//System.out.print(this.getHeight());
		jumping=true;
		x.dragon_up();
		Gaming.boost.play();

	}
	
}
