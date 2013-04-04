package window;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	public AppliWindow(String title){
		super();
		
		build(title); // On initialise notre fenetre
	}
	
	private void build(String title) {
		setTitle(title);
		setSize(340, 260);
		setLocationRelativeTo(null); // On centre la fenetre sur l'écran (en indiquant null)
		setResizable(false); // On interdit le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer au clic sur la croix.
	}
}
