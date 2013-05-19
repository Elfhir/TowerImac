package window.panel;

import game.Game;

import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import window.AppliWindow;

public class Panel extends JPanel implements KeyListener{

	// LList of Base extends JButton ?
	
	/**
	 * Il le faut à chaque fois
	 */
	private static final long serialVersionUID = -1788677938220552169L;
	
	public Panel() {
		super();
	}
	
	public Panel(LayoutManager layout) {
		super(layout);
	}
	
	//---------------------------------------- KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		
		AppliWindow window = AppliWindow.getInstance();
		
		switch (e.getKeyChar()) {
		case 'p':
			
			System.out.println("Key : "+e.getKeyChar());
			
			// Retour au jeu
			if(window.isPauseStatus()) {
				window.hidePause();
				window.showGame();
				Game.getInstance().setRunning(true);
				Game.getInstance().start();
				
			}
			// Mise en pause
			else {
				window.showPause();
				window.hideGame();
				Game.getInstance().setRunning(false);
			}	
			break;
			
		case 'q':
			if(window.isPauseStatus())
				System.exit(0);
		default:
			
			// any other key, let the Focus to the Panel
			window.giveFocusToPanel();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
