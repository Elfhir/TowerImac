package application;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.jdom2.JDOMException;

import exceptions.MapFileException;
import game.Game;

import window.AppliWindow;
import writer.XmlWriter;

public class Launcher {

	public static void main(String[] args) {
		
// Une JWindow :
		
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//On crée une nouvelle instance de notre JWindow
				JWindow window = new JWindow();
				window.setSize(300, 200); // On lui donne une taille pour la voir
				window.setVisible(true); // On l'a rend visible
			}
		});
		
		try {
			Thread.sleep(5555);
		} catch (InterruptedException e) {}
		
		System.exit(0);*/
		
		// Une JFrame
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// On crée la nouvelle instance de la JFrame
				AppliWindow window;
				try {
					window = new AppliWindow("Tower-IMAC-Nano Prout !", 800, 600, true, "design/testMapGrille.png");
					window.setVisible(true); // Et on la rend visible.
				} catch (MapFileException e) {
					e.printStackTrace();
				} catch (JDOMException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
