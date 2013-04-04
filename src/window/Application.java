package window;

import javax.swing.SwingUtilities;

public class Application {

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
				AppliWindow window = new AppliWindow("Tower-IMAC-Nano Prout !");
				window.setVisible(true); // Et on la rend visible.
			}
		});
		
		
	}

}
