package window.graphic;

import game.base.Base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import window.AppliWindow;

public class LineCursor extends JComponent implements MouseMotionListener{

	private int x1,x2,y1,y2;
	private boolean displayed;
	private Color color;

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * A simple LineCursor, joining two points ; the second point will follow the mouse cursor
	 * 
	 * @param x1   Coordinates of the first point (last-1 base we leave) - integer
	 * @param y1   Coordinates of the first point (last-1 base we leave) - integer
	 * @param x2   Coordinates of the second point (last base we leave)  - integer
	 * @param y2   Coordinates of the second point (last base we leave)  - integer
	 * @param c		Color of the LineCursor
	 */
	public LineCursor(int x1, int y1, int x2, int y2, Color c) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = c;
		this.displayed = false;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(this.getColor());
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
	 * Set only coordinates for the last point of LineCursor and repaint
	 * @param xMouse where the mouse is
	 * @param yMouse where the mouse is
	 */
	public void displayLineLastPoint(int xMouse, int yMouse) {
		this.setX2(xMouse);
		this.setY2(yMouse);
		this.repaint();
	}

	/**
	 * Set only coordinates for the first point of LineCursor and repaint
	 * @param x from the Base Clicked
	 * @param y from the Base Clicked
	 */
	public void displayLineFirstPoint(int x, int y) {
		this.setX1(x);
		this.setY1(y);
		this.repaint();
	}

	public void displayFirstPoint(Base b) {
		int xBase = (int) b.getX() + AppliWindow.getInstance().getTilesSize()/2;
		int yBase = (int) b.getY() + AppliWindow.getInstance().getTilesSize()/2;
		System.out.println("LC x "+xBase+" ; y "+yBase);
		AppliWindow.getInstance().getLineCursor().setDisplayed(true);
		AppliWindow.getInstance().getLineCursor().displayLine(xBase, yBase, xBase, yBase);
	}

	public void displayLastPoint(int xM, int yM) {
		System.out.println("LC a "+xM+" ; b "+yM);
		this.setColor(new Color(0,200,0));
		AppliWindow.getInstance().getLineCursor().setDisplayed(true);
		AppliWindow.getInstance().getLineCursor().displayLineLastPoint(xM, yM);
	}

	// ----------------------------------------- MouseMotionListener ----------------------------------
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}
