package window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	private Panel panel;
	private JButton bouton1;
	private JButton bouton2;
	
	public AppliWindow(String title, int width, int height, boolean resize){
		super();
		
		build(title, width, height, resize); // On initialise notre fenetre
	}
	
	public Panel getPane() {
		return panel;
	}

	public void setPane(Panel pane) {
		this.panel = pane;
	}

	// Utiliser cette méthode pour construire la fenêtre de l'application
	private void build(String title, int width, int height, boolean resize) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(null); // On centre la fenetre sur l'écran (en indiquant null)
		setResizable(resize); // On autorise avec true le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer au clic sur la croix.
		setContentPane(buildContentPane());
	}
	
	private Panel buildContentPane() {
		
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.ORANGE);
		
		JLabel label = new JLabel("-- Sprint 1 : Mini-NanoWar --");
		panel.add(label);
		
		bouton1 = new JButton("Base 1");
		bouton1.setBorder(BorderFactory.createEmptyBorder());
		bouton1.setContentAreaFilled(false);
		try
	    {
	        bouton1.setIcon(new ImageIcon(ImageIO.read(new File("src/window/cercle2.png"))));
	    }
		catch (IOException e1)
	    {
	    }
		bouton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Base 1 Selectionnée !\n");
			}
		});
		panel.add(bouton1);
		
		bouton2 = new JButton("Base 2");
		bouton2.setBorder(BorderFactory.createEmptyBorder());
		bouton2.setContentAreaFilled(false);
		try
	    {
	        bouton2.setIcon(new ImageIcon(ImageIO.read(new File("src/window/cercle2.png"))));
	        System.out.println(bouton2);
	    }
		catch (IOException e2)
	    {
			//bouton2.getGraphics().drawOval(100, 100, 30, 30);
			System.out.println(bouton2);
	    }
		bouton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Base 2 Selectionnée !\n");
			}
		});
		panel.add(bouton2);
		
		return panel;
	}
}
