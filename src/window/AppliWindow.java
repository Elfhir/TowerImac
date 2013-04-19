package window;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	private int width;
	private int height;
	private Panel content;
	private JButton bouton1;
	private JButton bouton2;
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Panel getContent() {
		return content;
	}

	
	
	public AppliWindow(String title, int width, int height, boolean resize){
		super();
		this.width = width;
		this.height = height;
		build(title, resize); // On initialise notre fenetre
	}
	
	public Panel getPane() {
		return content;
	}

	public void setPane(Panel pane) {
		this.content = pane;
	}
	/**
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param resize
	 * 
	 * Utiliser cette méthode pour construire la fenêtre de l'application
	 */ 
	private void build(String title, boolean resize) {
		setTitle(title);
		setSize(this.width, this.height);
		setLocationRelativeTo(null); // On centre la fenetre sur l'écran (en indiquant null)
		setResizable(resize); // On autorise avec true le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer au clic sur la croix.
		setContentPane(buildContentPane(width, height));
	}
	
	private Panel buildContentPane(int width, int height) {
		
		this.content = new Panel();
		GridBagLayout grille = new GridBagLayout();
		content.setLayout(grille);
		GridBagConstraints c = new GridBagConstraints();
		//On crée nos différents conteneurs de couleur différente
		
		content.setBackground(Color.ORANGE);
		
		//JLabel label = new JLabel("-- Sprint 1 : Mini-NanoWar --");
		//content.add(label);
		
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
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		grille.setConstraints(bouton1, c);
		content.add(bouton1, c);
		
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
		
		c.gridx = 10;
		c.gridy = 10;
		c.gridheight = 1;
		c.gridwidth = 1;
		grille.setConstraints(bouton2, c);
		content.add(bouton2,c);
		
		return content;
	}
}
