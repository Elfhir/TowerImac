package window;

import exceptions.RealPlayerException;
import game.Base;
import game.Game;
import game.Player;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.accessibility.Accessible;
import javax.swing.Icon;
import javax.swing.JLabel;

import window.graphic.GraphicElement;

public class Label extends JLabel implements Accessible, MouseListener, KeyListener, MouseMotionListener{


	private static final long serialVersionUID = 2250314127870259478L;

	protected ActionListener action;
	protected GraphicElement graph;

	/**
	 * @return the graph
	 */
	public GraphicElement getGraph() {
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(GraphicElement graph) {
		this.graph = graph;
	}

	// ----------------------------------------- MouseListener ----------------------

	public void addMouseListener(ActionListener actionListener) {
		action = actionListener;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		//Try to deselect the player
		Player realPlayer = null;
		try {
			realPlayer = Game.getInstance().getPlayerManager().getRealPlayer();
		} catch (RealPlayerException e) {

			e.printStackTrace();
		}
		if(realPlayer.getSelectedBases() != null) {
			realPlayer.getSelectedBases().setBackground(realPlayer.getColor().brighter());
		}
		realPlayer.setSelectedBases(null);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		//System.out.println("MOUSE EXITS THE BASE");
	}

	@Override
	public void mouseExited(MouseEvent event) {
		
		int xBase = 0, yBase = 0;
		int aBase = 0, bBase = 0;
		int radiusBase = AppliWindow.getInstance().getTilesSize();
		int xM = event.getX(), yM = event.getY();
		int distX  = 0, distY = 0;

		for(Base b : Game.getInstance().getBaseManager().getBases()){
			distX = (int) Math.abs(xM - b.getPosition().getX());
			distY = (int) Math.abs(yM - b.getPosition().getY());
			if(distX > radiusBase && distY > radiusBase) {
				break;
			}
			else{
				return;
			}
		}
		// If break in previous for ; the mouse is leaving the Label for Background, i.e enters a Base
		System.out.println("MOUSE ENTERS THE BASE "+xBase+" "+yBase+" "+aBase+" "+bBase);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("X : "+e.getX()+" Y : "+e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	// ----------------------------------------- MouseMotionListener -----------------
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("X : "+e.getX()+" Y : "+e.getY());
	}

	// ----------------------------------------- KeyListener--------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key aa: "+e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	// ----------------------------------------- Accessor -----------------------------

	// ------------------------------------------ constructor -------------------------
	public Label() {
		super();
		this.graph = new GraphicElement();
		addMouseListener(this);
	}

	public Label(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		this.graph = new GraphicElement();
		addMouseListener(this);
	}

	public Label(Icon image) {
		super(image);
		this.graph = new GraphicElement();
		addMouseListener(this);
	}

	public Label(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		this.graph = new GraphicElement();
		addMouseListener(this);
	}

	public Label(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		this.graph = new GraphicElement();
		addMouseListener(this);
	}

	public Label(String text) {
		super(text);
		this.graph = new GraphicElement();
		addMouseListener(this);
	}



}
