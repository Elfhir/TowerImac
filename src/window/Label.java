package window;

import exceptions.RealPlayerException;
import game.Game;
import game.Player;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.accessibility.Accessible;
import javax.swing.Icon;
import javax.swing.JLabel;

import window.graphic.GraphicElement;

public class Label extends JLabel implements Accessible, MouseListener, KeyListener{

	
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
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	// ----------------------------------------- KeyListener--------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key : "+e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
