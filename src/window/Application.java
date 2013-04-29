package window;

import game.Base;
import game.Game;
import game.IAPlayer;
import game.Player;
import game.RealPlayer;

import javax.swing.SwingUtilities;

import writer.XmlWriter;

public class Application {

	public static void main(String[] args) {
		
	Game game = Game.getInstance();
	
	Player michel = new RealPlayer("Michel");
	Player jean_luc = new IAPlayer("Jean-Luc");
	Player patrick = new IAPlayer("Patrick");
	Player germaine = new IAPlayer("Germaine");
	
	Base base1 = new Base(10, michel);
	Base base2 = new Base(0, michel);
	
	game.getPlayerManager().addPlayer(michel);
	game.getPlayerManager().addPlayer(jean_luc);
	game.getPlayerManager().addPlayer(patrick);
	game.getPlayerManager().addPlayer(germaine);
	
	game.getBaseManager().addBase(base1);
	game.getBaseManager().addBase(base2);
	
	XmlWriter.createXmlFile(game);
	
	
	
	michel.start();
	jean_luc.start();
	patrick.start();
	germaine.start();
		
		
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
		
// Une JDialog :
		
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//On crée une nouvelle instance de notre JWindow
				JDialog dialog = new JDialog();
				dialog.setSize(350, 220); // On lui donne une taille pour la voir
				dialog.setTitle("Tower-IMAC-Nano Pooowaaaa !"); // On lui donne un titre
				dialog.setVisible(true); // On la rend visible
			}
		});*/
		
		// Une JFrame
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// On crée la nouvelle instance de la JFrame
				AppliWindow window = new AppliWindow("Tower-IMAC-Nano Prout !", 800, 600, true);
				window.setVisible(true); // Et on la rend visible.
				
				
			}
		});
		
		System.out.println("base1: " + Game.getInstance().getBaseManager().getBases().get(0).getSize());
		System.out.println("base2: " + Game.getInstance().getBaseManager().getBases().get(1).getSize());		
		
	}

}
