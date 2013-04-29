package window;


import game.Base;

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
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	private int width;
	private int height;
	private Panel content;
	private Base base1;
	private Base base2;
	
	
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
	
	public void setContent(Panel content) {
		this.content = content;
	}

	public int getNumOfTile() {
		return ((this.width/50) * (this.height/50));
	}
	
	public int getNumOfTileWidth() {
		return (this.width/50);
	}
	
	public int getNumOfTileHeight() {
		return (this.height/50);
	}
	
	public AppliWindow(String title, int width, int height, boolean resize){
		super();
		this.width = width;
		this.height = height;
		build(title, resize); // On initialise notre fenetre
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
		
		int numW = this.getNumOfTileWidth();
		int numH = this.getNumOfTileHeight();
		
		this.content = new Panel();
		
		GridBagLayout grille = new GridBagLayout();
		content.setLayout(grille);
		content.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		//On crée nos différents conteneurs de couleur différente
		
		//It not works yet
		//ArrayList<ArrayList<Panel>> cells = content.newGrid(numW, numH, 50, content, grille, c);
	     
		// One cell
		/*
		Panel cell1 = new Panel();
		cell1.setBackground(Color.YELLOW);
		cell1.setPreferredSize(new Dimension(50, 50));
        c.gridx = 0;
        c.gridy = 0;
        grille.setConstraints(cell1, c);
        */
       
		// 1st Button
		
		base1 = new Base(10, null);
		base1.setBorder(BorderFactory.createLineBorder(Color.black));
		base1.setContentAreaFilled(false);
		base1.setBounds(0, 0, width, height);
		try
	    {
	        base1.setIcon(new ImageIcon(ImageIO.read(new File("design/cercle2.png"))));
	    }
		catch (IOException e1)
	    {
	    }
		base1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Base 1 Selectionnée !\n");
				base1.setNbAgents(base1.getNbAgents()-base1.getNbAgents());
				base2.setNbAgents(Math.abs(base1.getNbAgents()-base2.getNbAgents()));
				System.out.println(base1);
				System.out.println(base2);
			}
		});
		c.gridx = 3;
		c.gridy = 0;

		grille.setConstraints(base1, c);
		content.add(base1, c);

		// -------------------------------- - -  -   -    -     -      -       -        -         - -
		
		base2 = new Base(0, null);
		base2.setBorder(BorderFactory.createLineBorder(Color.black));
		base2.setContentAreaFilled(false);
		try
	    {
	        base2.setIcon(new ImageIcon(ImageIO.read(new File("design/cercle2.png"))));
	        System.out.println(base2);
	    }
		catch (IOException e2)
	    {
			//bouton2.getGraphics().drawOval(100, 100, 30, 30);
			System.out.println(base2);
	    }
		base2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Base 2 Selectionnée !\n");
			}
		});
		
		c.gridx = 3;
		c.gridy = 1;
		grille.setConstraints(base2, c);
		content.add(base2,c);
		
		
		return content;
	}
}
