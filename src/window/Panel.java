package window;

import engine.Engine;
import game.Game;
import game.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

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
