package window;

import exceptions.RealPlayerException;
import game.Game;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.accessibility.Accessible;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Label extends JLabel implements Accessible, MouseListener{

	
	private static final long serialVersionUID = 2250314127870259478L;
	
	protected ActionListener action;
	
	// ----------------------------------------- ActionListener ----------------------

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

	// ----------------------------------------- Accessor -----------------------------
	
		
	// ------------------------------------------ constructor -------------------------
	public Label() {
		super();
		addMouseListener(this);
	}

	public Label(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		addMouseListener(this);
	}

	public Label(Icon image) {
		super(image);
		addMouseListener(this);
	}

	public Label(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		addMouseListener(this);
	}

	public Label(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		addMouseListener(this);
	}

	public Label(String text) {
		super(text);
		addMouseListener(this);
	}

}
