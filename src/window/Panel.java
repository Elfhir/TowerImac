package window;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{

	// LList of Base extends JButton ?
	
	/**
	 * Il le faut à chaque fois
	 */
	private static final long serialVersionUID = -1788677938220552169L;

	public Panel() {
		super();
		
	}

	public void drawGrid(Graphics g) {
	    //x1, y1, x2, y2
	    g.drawLine((this.getWidth()/2), 0, (this.getWidth()/2), this.getHeight());
	    g.drawLine(0, (this.getHeight()/2), this.getWidth(), (this.getHeight()/2));
	}   
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
