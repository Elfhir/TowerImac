package window;


import game.Base;
import game.Game;

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

import time.BaseThread;

@SuppressWarnings("serial")
public class AppliWindow extends JFrame {
	
	private int width;
	private int height;
	private Panel content;
	
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
		buildWindow(title, resize); // On initialise notre fenetre
	}
	
	/**
	 * Builds the window of the application
	 * @param width		The width of the window
	 * @param height	The height of the window
	 * @param resize	indicates if the window is resizable or not
	 */ 
	private void buildWindow(String title, boolean resize) {
		setTitle(title);
		setSize(this.width, this.height);
		setLocationRelativeTo(null); // null => The window is centered on the screen
		setResizable(resize); // Resizable window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application musts close when we click on the cross
		setContentPane(buildContentPane(width, height));
		
	}
	
	/**
	 * Builds the elements of the window corresponding to the bases
	 */
	private void buildBases() {
		
		GridBagLayout grill = (GridBagLayout) content.getLayout();
		Game game = Game.getInstance();
		
		for(final Base base: game.getBaseManager().getBases()) {
			base.setBorder(BorderFactory.createLineBorder(Color.black));
			base.setContentAreaFilled(false);
			base.setBounds(0, 0, 1, 1);
			try
		    {
		        base.setIcon(new ImageIcon(ImageIO.read(new File("design/cercle2.png"))));
		    }
			catch (IOException e1)
		    {
				System.out.println("Error ! Loading base image");
		    }
			base.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					base.clicked();
				}
			});
			
			// Am I doing it right ?? (I guess not)
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = (int) base.getPosition().x;
			c.gridy = (int) base.getPosition().y;
			grill.setConstraints(base, c);
			content.add(base, c);
		}
	}
	
	/**
	 * Build the game : creates the informations from a XML file and build all corresponding elements in the window.
	 * @param fileName	The name of the XML file needed to create the game
	 */
	private void buildGame(String fileName) {
		Game game = Game.getInstance();
		game.initGame(fileName);
		buildBases();
		
		// We create the Runnable
		BaseThread generation1 = new BaseThread();
		// We create the Thread for the generation of agents
		Thread threadBase = new Thread(generation1);
		threadBase.start();
		
//		buildAgents();
//		buildTowers();
//		//...
	}
	
	/**
	 * Builds the window ????????????????????????????????
	 * @param width		The width of the window ?????????
	 * @param height	The height of the window ????????
	 * @return			The panel ???????????????????????
	 */
	private Panel buildContentPane(int width, int height) {
		
		int numW = this.getNumOfTileWidth();
		int numH = this.getNumOfTileHeight();
		
		this.content = new Panel();
		
		GridBagLayout grill = new GridBagLayout();
		content.setLayout(grill);
		content.setBackground(Color.WHITE);
//		GridBagConstraints c = new GridBagConstraints();
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
       
		buildGame("game.xml");

		return content;
	}
}
