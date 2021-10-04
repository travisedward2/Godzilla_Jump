package finalProject;

import javax.swing.ImageIcon;

public class RegularStair extends Stair{
	public RegularStair() {
		ImageIcon icon=new ImageIcon("image/stair.png");
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
	}
	public RegularStair(int x, int y) {
		this();
		this.x=x;
		this.y=y;
	}

}
