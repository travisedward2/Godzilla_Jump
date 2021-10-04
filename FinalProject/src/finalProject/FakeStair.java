package finalProject;

import javax.swing.ImageIcon;

public class FakeStair extends Stair{
		public FakeStair() {
			ImageIcon icon=new ImageIcon("image/fakestair.png");
	        this.setIcon(icon);
	        this.setSize(icon.getIconWidth(),icon.getIconHeight());  
		}
		public FakeStair(int x,int y) {
			this();
			this.x=x;
			this.y=y;
			
			/*ImageIcon icon=new ImageIcon("image/stair.png");
	        this.setIcon(icon);
	        this.setSize(icon.getIconWidth(),icon.getIconHeight()); */   
		}
		public void collapse() {
			this.setVisible(false);
		}
}
