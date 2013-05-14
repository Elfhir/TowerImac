package window.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Line extends JComponent implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956143461428611393L;
	private int x1,x2,y1,y2;
	private boolean displayed;

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public boolean isDisplayed() {
		return displayed;
	}
	
	public void setDisplayed(boolean displayed) {
		this.displayed = displayed;
	}

	/**
	 * A simple Line, joining two point
	 * 
	 * @param x1   Coordinates of the first point (last-1 base we leave) - integer
	 * @param y1   Coordinates of the first point (last-1 base we leave) - integer
	 * @param x2   Coordinates of the second point (last base we leave)  - integer
	 * @param y2   Coordinates of the second point (last base we leave)  - integer
	 * @param displayed		Boolean which determined if we show it on the Panel, instead of setVisible,
	 * 						Because setVisible don't let the Line 'Mouse-focusable'
	 */
	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.displayed = false;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.red);
		g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * Set all new coordinates and repaint
	 * @param x from the Base Clicked
	 * @param y from the Base Clicked
	 * @param xMouse where the mouse is
	 * @param yMouse where the mouse is
	 */
	public void displayLine(int x, int y, int xMouse, int yMouse) {
		this.setX1(x);
		this.setY1(y);
		this.setX2(xMouse);
		this.setY2(yMouse);
		this.repaint();
	}
	
	/**
	 * Set only coordinates for the last point of Line and repaint
	 * @param xMouse where the mouse is
	 * @param yMouse where the mouse is
	 */
	public void displayLineLastPoint(int xMouse, int yMouse) {
		this.setX2(xMouse);
		this.setY2(yMouse);
		this.repaint();
	}

	// -------------------------------------------- MouseListener ---------------
	
	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
