package window;


import game.Base;

import java.awt.Color;
import java.awt.Dimension;
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
		
		int numW = this.getNumOfTileWidth();
		int numH = this.getNumOfTileHeight();
		
		this.content = new Panel();
		
		GridBagLayout grille = new GridBagLayout();
		content.setLayout(grille);
		content.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		//On crée nos différents conteneurs de couleur différente
		
		//ArrayList<ArrayList<Panel>> cells = content.newGrid(this.getNumOfTileWidth(), this.getNumOfTileHeight(), 50, c);
	     
		
			Panel cell1 = new Panel();
         cell1.setBackground(Color.YELLOW);
         cell1.setPreferredSize(new Dimension(width/3, height/2));
             c.gridx = 0;
             c.gridy = 0;
             grille.setConstraints(cell1, c);
             content.add(cell1, c);
         
             Panel cell2 = new Panel();
         cell2.setBackground(Color.BLUE);
         cell2.setPreferredSize(new Dimension(width/3, height/2));  
             c.gridx = 1;
             c.gridy = 0;
             grille.setConstraints(cell2, c);
             content.add(cell2, c);
         
             Panel cell3 = new Panel();
         cell3.setBackground(Color.GREEN);
         cell3.setPreferredSize(new Dimension(width/3, height/2));  
             c.gridx = 2;
             c.gridy = 0;
             grille.setConstraints(cell3, c);
             content.add(cell3, c);
         
             Panel cell4 = new Panel();
         cell4.setBackground(Color.PINK);
         cell4.setPreferredSize(new Dimension(width/3, height/2));  
             c.gridx = 1;
             c.gridy = 0;
             grille.setConstraints(cell4, c);
             content.add(cell4, c);
		
		
		
		base1 = new Base(10, 10, null);
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
				base1.setAgents(base1.getAgents()-base1.getAgents());
				base2.setAgents(Math.abs(base1.getAgents()-base2.getAgents()));
				System.out.println(base1);
				System.out.println(base2);
			}
		});
		c.gridx = 3;
		c.gridy = 0;

		grille.setConstraints(base1, c);
		content.add(base1, c);
		
		// -------------------------------- - -  -   -    -     -      -       -        -         - -
		
		base2 = new Base(0, 10, null);
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
