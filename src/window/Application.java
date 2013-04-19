package window;

import javax.swing.SwingUtilities;

public class Application {

	public static void main(String[] args) {
		
// Une JFrame
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// On crée la nouvelle instance de la JFrame
				AppliWindow window = new AppliWindow("Tower-IMAC-Nano Prout !", 600, 400, true);
				window.setVisible(true); // Et on la rend visible.
			}
		});
		
	}

}
