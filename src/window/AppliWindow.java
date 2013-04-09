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
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	JButton bouton1;
	JButton bouton2;
	
	public AppliWindow(String title){
		super();
		
		build(title); // On initialise notre fenetre
	}
	
	// Utiliser cette méthode pour construire la fenêtre de l'application
	private void build(String title) {
		setTitle(title);
		setSize(280, 260);
		setLocationRelativeTo(null); // On centre la fenetre sur l'écran (en indiquant null)
		setResizable(true); // On interdit le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer au clic sur la croix.
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.GRAY);
		
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
	    }
		catch (IOException e2)
	    {
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
