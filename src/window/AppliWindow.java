package window;


import java.awt.BorderLayout;
import engine.Engine;
import exceptions.ClickedByRealPlayerException;
import exceptions.MapFileException;
import game.Base;
import game.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.vecmath.Vector2f;

import org.jdom2.JDOMException;

import exceptions.MapFileException;
import exceptions.RealPlayerException;
import game.Base;
import game.Game;
import game.Player;
import game.RealPlayer;
/**
 * Singleton
 *
 */
@SuppressWarnings("serial")
public class AppliWindow extends JFrame {

	private int width;
	private int height;
	
	private Panel content;
	private boolean buildToolsVisible = false;
	
	private JPanel panelInfoRealPlayer;
	private JPanel panelInfoIAPlayers;
	
	private Label image;
	private Label pause;
	private JButton resumeGame;
	private JButton exitGame;
	private boolean pauseStatus;
	
	
	public JPanel getPanelInfoRealPlayer() {
		return this.panelInfoRealPlayer;
	}

	public void setPanelInfoRealPlayer(JPanel panelInfoRealPlayer) {
		this.panelInfoRealPlayer = panelInfoRealPlayer;
	}

	public JPanel getPanelInfoIAPlayers() {
		return this.panelInfoIAPlayers;
	}

	public void setPanelInfoIAPlayers(JPanel panelInfoIAPlayers) {
		this.panelInfoIAPlayers = panelInfoIAPlayers;
	}

	public boolean isBuildToolsVisible() {
		return this.buildToolsVisible;
	}
	
	public void setBuildToolsVisible(boolean buildToolsVisible) {
		this.buildToolsVisible = buildToolsVisible;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Panel getContent() {
		return this.content;
	}

	public void setContent(Panel content) {
		this.content = content;
	}

	public int getTilesSize() {
		return 50;
	}

	public int getNumOfTile() {
		return ((this.width/getTilesSize()) * (this.height/getTilesSize()));
	}

	// How many tiles on the width : for 800px it's 16, because 16x50=800
	public int getNumOfTileWidth() {
		return (this.width/getTilesSize());
	}

	public int getNumOfTileHeight() {
		return (this.height/getTilesSize());
	}

	public Label getImage() {
		return this.image;
	}

	public void setImage(Label image) {
		this.image = image;
	}

	public Label getPause() {
		return this.pause;
	}

	public void setPause(Label pause) {
		this.pause = pause;
	}

	/**
	 * @return the pauseStatus
	 */
	public boolean isPauseStatus() {
		return this.pauseStatus;
	}

	/**
	 * @param pauseStatus the pauseStatus to set
	 */
	public void setPauseStatus(boolean pauseStatus) {
		this.pauseStatus = pauseStatus;
	}

	/**
	 * @return the resumeGame
	 */
	public JButton getResumeGame() {
		return this.resumeGame;
	}

	/**
	 * @param resumeGame the resumeGame to set
	 */
	public void setResumeGame(JButton resumeGame) {
		this.resumeGame = resumeGame;
	}

	/**
	 * @return the exitGame
	 */
	public JButton getExitGame() {
		return this.exitGame;
	}

	/**
	 * @param exitGame the exitGame to set
	 */
	public void setExitGame(JButton exitGame) {
		this.exitGame = exitGame;
	}


	/**
	 * Builds the window of the application
	 * @param width		The width of the window
	 * @param height	The height of the window
	 * @param resize	indicates if the window is resizable or not
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws MapFileException 
	 */ 
	private void buildWindow(String title, boolean resize) throws MapFileException, JDOMException, IOException {
		setTitle(title);
		setSize(this.width, this.height);
		setLocationRelativeTo(null); // null => The window is centered on the screen
		setResizable(resize); // Resizable window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application musts close when we click on the cross
		setContentPane(buildContentPane(width, height));
		this.addKeyListenerToPanel(this.getContent());

	}

	/**
	 * Builds the elements of the window corresponding to the bases
	 */
	private void buildBases() {

		Game game = Game.getInstance();

		for(final Base base: game.getBaseManager().getBases()) {
			base.setBorder(BorderFactory.createLineBorder(Color.black));
			base.setContentAreaFilled(false);
			base.setBounds((int)base.getPosition().x, (int)base.getPosition().y, getTilesSize(), getTilesSize());
			base.setOpaque(true);
			try
			{
				base.setIcon(new ImageIcon(ImageIO.read(new File("design/cercle2.png"))));
				if(base.hasPlayer()) {
					base.setBackground(base.getPlayer().getColor());
				}
				else {
					base.setBackground(Color.GRAY);
				}
			}
			catch (IOException e1)
			{
				System.err.println("Error ! Loading base image");
			}
			base.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					// all actions are managed by the base				

					try {
						base.clickedByRealPlayer();
					} catch (RealPlayerException e1) {
						System.err.println("Error with RealPlayer : can't manage the click.");
					} catch (ClickedByRealPlayerException e2) {
						e2.printStackTrace();
					}

					// deprecated 
					/*
					try {
						base.clickedByIAPlayer();
					} catch (IAPlayerException e2) {
						System.err.println("Error with IAPlayer : can't manage the IA click.");
					}
					 */
				}
			});

			/*
			// Am I doing it right ?? (I guess not)
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = (int) base.getPosition().x;
			c.gridy = (int) base.getPosition().y;
			 */
			content.add(base);

		}
	}

	/**
	 * 
	 * Constructs and adds to the window the panels corresponding to the players' informations : money and number of bases
	 * For the RealPlayer, the panel can be enlarged to provide tools to buy towers.
	 * 
	 */
	public void buildInfoPlayers() {
		
		// info of the size of the different panels
		final int widthPanelRealPlayer = 200;
		final int heightPanelRealPlayer = 100;
		final int visibleHeightPanelRealPlayer = 40;
		int widthPanelIAPlayer = 250;
		int heightPanelIAPlayer = 40;
		
		int nbTotalBases = Game.getInstance().getBaseManager().getBases().size();
		
		// panel for the Real Player
		JPanel panelRealPlayer = this.panelInfoRealPlayer;
		panelRealPlayer.setBounds(0, height - visibleHeightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
		panelRealPlayer.setVisible(true);
		panelRealPlayer.setBackground(Color.RED);
		
		// panel for the IA Players
		JPanel panelIAPlayers = this.panelInfoIAPlayers;
		panelIAPlayers.setBounds(width - widthPanelIAPlayer, height - heightPanelIAPlayer, widthPanelIAPlayer, heightPanelIAPlayer);
		panelIAPlayers.setVisible(true);
		panelIAPlayers.setBackground(Color.BLACK);
		
		// we loop on each player
		for(Player p: Game.getInstance().getPlayerManager().getPlayers()) {
			
			// how many bases does the player have ?
			int nbBasesPlayer = 0;
			for(Base b: Game.getInstance().getBaseManager().getBases()) {
				if (b.getPlayer()!=null && b.getPlayer().equals(p)) {
					++nbBasesPlayer;
				}
			}
			
			// if it is the RealPlayer, we fill the panelRealPlayer
			if(p instanceof RealPlayer) {
				
				StringBuilder sb = new StringBuilder(p.getName());
				sb.append(" : $");
				sb.append(p.getBank().getMoney());
				sb.append(" | ");
				sb.append(nbBasesPlayer);
				sb.append("/");
				sb.append(nbTotalBases);
				
				JButton buttonRealPlayer = new JButton(sb.toString());
				
				// add actionListener to show or hide the panel
				buttonRealPlayer.addActionListener(new ActionListener() {
					
					// action on click : shows or hides the panel
					public void actionPerformed(ActionEvent e) {
						if(isBuildToolsVisible() ==  true) {
							hidePanel();
						}
						else {
							showPanel();
						}
					}
					
					// shows the panel
					public void showPanel() {
						AppliWindow.getInstance().getPanelInfoRealPlayer().setBounds(0, height - heightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
						setBuildToolsVisible(true);
					}
					
					// hide the panel
					public void hidePanel() {
						AppliWindow.getInstance().getPanelInfoRealPlayer().setBounds(0, height - visibleHeightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
						setBuildToolsVisible(false);
					}
				});
				
				panelRealPlayer.add(buttonRealPlayer);

			}
			
			// else if it is an IA JPlayer we fill the panelIAPlayers with a new JPanel for each IAPlayer
			else {
				JPanel panel = new JPanel();
				
				StringBuilder sb = new StringBuilder("<html>");
				sb.append(p.getName());
				sb.append("<br /> $");
				sb.append(p.getBank().getMoney());
				sb.append(" | ");
				sb.append(nbBasesPlayer);
				sb.append("/");
				sb.append(nbTotalBases);
				sb.append("</html>");
				
				JLabel label = new JLabel(sb.toString());
				label.setForeground(p.getColor());
				panel.add(label);
				panel.setBackground(null);
				panelIAPlayers.add(panel);
			}
		}
		
		// finally we add the two panels (panelRealPlayer and panelIAPlayers) to the window
		
//		content.removeAll();
//		
//		setPanelInfoRealPlayer(panelRealPlayer);
		content.add(panelRealPlayer);
//		
//		setPanelInfoIAPlayers(panelIAPlayers);
		content.add(panelIAPlayers);
		
	}
	
	/**
	 * Updates the infos of the players in the corresponding panels.
	 * This function must be called each time a base is taken or a player earn money.
	 * 
	 */
	public void updateInfoPlayers() {

		int nbTotalBases = Game.getInstance().getBaseManager().getBases().size();
		
		// panel for the Real Player
		JPanel panelRealPlayer = this.panelInfoRealPlayer;
		
		// panel for the IA Players
		JPanel panelIAPlayers = this.panelInfoIAPlayers;
		
		// we loop on each player
		int indexIAPlayer = 0;
		for(Player p: Game.getInstance().getPlayerManager().getPlayers()) {
			
			// how many bases does the player have ?
			int nbBasesPlayer = 0;
			for(Base b: Game.getInstance().getBaseManager().getBases()) {
				if (b.getPlayer()!=null && b.getPlayer().equals(p)) {
					++nbBasesPlayer;
				}
			}
			
			// if it is the RealPlayer, we update the text of the button of the panelRealPlayer
			if(p instanceof RealPlayer) {
				
				StringBuilder sb = new StringBuilder(p.getName());
				sb.append(" : $");
				sb.append(p.getBank().getMoney());
				sb.append(" | ");
				sb.append(nbBasesPlayer);
				sb.append("/");
				sb.append(nbTotalBases);
				
				// The button is the first Component in the pannel
				JButton buttonRealPlayer = (JButton)panelRealPlayer.getComponent(0);
				// we can update the text of the button
				buttonRealPlayer.setText(sb.toString());
			}
			
			// else if it is an IA Player we change the text of the label in each panel of panelIAPlayers (= corresponding to each IAPlayer)
			else {
				// the panel of the player
				JPanel panel = (JPanel) panelIAPlayers.getComponent(indexIAPlayer);
				// the label of the player = it's the first element
				JLabel label = (JLabel) panel.getComponent(0);
				
				StringBuilder sb = new StringBuilder("<html>");
				sb.append(p.getName());
				sb.append("<br /> $");
				sb.append(p.getBank().getMoney());
				sb.append(" | ");
				sb.append(nbBasesPlayer);
				sb.append("/");
				sb.append(nbTotalBases);
				sb.append("</html>");
				
				// we change the text of the label
				label.setText(sb.toString());	
				
				++indexIAPlayer;
			}
		}
	}
	
	/**
	 * Add JPanel Tiles where there is nothing else.
	 * In a bulk, it adds square, the same as Base, but with nothing to do on it.
	 * Just as a map, a game map.
	 * 
	 * Still need a more random disposition of Tiles.
	 * @deprecated The map is no more built with Tiles
	 */
	@Deprecated
	public void buildTiles() {

		Game game = Game.getInstance();

		// How many tiles
		int tilesToBuild = this.getNumOfTile() - game.getBaseManager().getBases().size();

		// Tab of the Base coords where to not draw tiles (2D integers)
		int tab[][] = new int[game.getBaseManager().getBases().size()][game.getBaseManager().getBases().size()];

		// Initialize the tab with the good coordinates
		for(int k = 0; k<tab.length; ++k) {
			tab[k][0] = (int) game.getBaseManager().getBases().get(k).getPosition().x;
			tab[k][1] = (int) game.getBaseManager().getBases().get(k).getPosition().y;
		}

		// ArrayList of Vector2f where to build.
		ArrayList<Vector2f> tabOfWhereToBuild = new ArrayList<Vector2f>(tilesToBuild);

		// Creating all the ArrayList of Vector2f for Tiles, even the bad one
		for(int i = 0; i<getNumOfTileWidth(); ++i) {
			for(int j = 0; j<getNumOfTileHeight(); ++j) {	
				tabOfWhereToBuild.add(new Vector2f(i, j));
			}	
		}

		// DEBUG : tous les vecteurs dans l'ordre.
		for(int i = 0; i<this.getNumOfTile(); ++i) {
			System.out.println(tabOfWhereToBuild.get(i));
		}

		// Remove the bad one and add what we wanted. That's the better way I find after 4h of trying it, deal with it =D
		for(int k=0; k<tab.length; ++k) {
			tabOfWhereToBuild.remove( ( tab[k][1] +  tab[k][0]*(getNumOfTileHeight()) ) -k );
			tabOfWhereToBuild.add(new Vector2f(tab[k][0], tab[k][1]));
			System.out.println("Current index of the Tiles : "+ ( tab[k][1] +  tab[k][0]*(getNumOfTileHeight()) -k ) );
			System.out.println("Tiles'coordinates : ("+tab[k][0]+"; "+tab[k][1]+") ");
		}	

		for(int k = 0; k < tilesToBuild; ++k) {
			JButton pan = new JButton();
			pan.setBorder(BorderFactory.createLineBorder(Color.black));
			pan.setContentAreaFilled(false);
			pan.setBounds(0, 0, 1, 1);
			try
			{	
				//Stupid random =D
				if(k==5 || k==6 || k==7 || k==8 || k==9 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==18 || k==19 || k==20 || k==30)
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==180 || k==181 || k==182 || k==183 || k==184 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==170 || k==171 || k==172 || k==160 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==46 || k==58 || k==70 || k==82 || k==94 || k==106 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==59 || k==71 || k==72 || k==83 || k==84 || k==85 || k==95 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==69 || k==83 || k==93 || k==105 || k==117 || k==129 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));
				else if(k==79 ||k==80 ||k==81 ||k==92||k==104 ||k==116|| k==105 ||k==91 ||k==90)
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock2-tile.png"))));


				else if(k==39 || k==51 || k == 120|| k == 125)
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/pit1-tile.png"))));

				else if(k==45 || k==57 || k==69 || k==81 || k==93 || k==105 )
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/gravel-tile.png"))));
				else if(k==20 || k==21 || k==17|| k==16 || k==4|| k==169 || k==179 || k==173 || k==155 || k==156 || k==161)
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/gravel-tile.png"))));

				else
					pan.setIcon(new ImageIcon(ImageIO.read(new File("design/rock1-tile.png"))));
			}
			catch (IOException e1)
			{
				System.err.println("Failed to load Tiles image!");
			}
			/*
			c.gridx = (int)tabOfWhereToBuild.get(k).getX();
			c.gridy = (int)tabOfWhereToBuild.get(k).getY();
			 */
		}

	}


	/**
	 * Build the game : creates the informations from a XML file and build all corresponding elements in the window.
	 * @param fileName	The name of the XML file needed to create the game
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws MapFileException 
	 */
	private void buildGame(String xmlFileName, String mapFileName) throws MapFileException, JDOMException, IOException {
		Game game = Game.getInstance();
		game.initGame(xmlFileName, mapFileName);
		buildBases();

		buildInfoPlayers();
//		buildAgents();
//		buildTowers();
//		//...
		
		game.start();


	}

	/**
	 * Builds the window ????????????????????????????????
	 * @param width		The width of the window ?????????
	 * @param height	The height of the window ????????
	 * @return			The panel ???????????????????????
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws MapFileException 
	 */
	private Panel buildContentPane(int width, int height) throws MapFileException, JDOMException, IOException {

		this.content = new Panel();

		content.setLayout(null);
		content.setBackground(Color.GRAY);

		buildGame("game.xml", "testMap");

		return content;
	}

	/**
	 * Add a background image, which will fit all the window.
	 * @param pathImage		A String for the image path
	 */
	private void buildBackground(String pathImage) {
		// This try/catch is used for trying to set a background !
		try {
			this.image =  new Label(new ImageIcon(ImageIO.read(new File(pathImage))), JLabel.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.getImage().setBounds(0, 0, getWidth(), getHeight());
		this.content.add(this.image);

	}

	/**
	 * Add KeyListener to a panel and set it Focusable
	 * Called in buildwindow.
	 * For more infos see <a>http://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html<a>
	 */
	private void addKeyListenerToPanel(Panel panel) {
		panel.addKeyListener(panel);
		panel.setFocusable(true);
	}

	/**
	 * Add KeyListener to a lanel and set it Focusable
	 * Called in buildwindow.
	 * For more infos see <a>http://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html<a>
	 */
	private void addKeyListenerToLabel(Label label) {
		label.addKeyListener(label);
		label.setFocusable(true);
	}

	/**
	 * Build a Label Pause, and 2 JButton (exit the game and resume the game)
	 * Set their position, color. Add two ActionListener on the JButton, for leaving the game or resuming it.
	 * The actions done in it are the same as in Panel image for key pressed 'p' anq 'q'.
	 * @param width
	 * @param height
	 */
	private void buildPause(int width, int height){

		this.pause =  new Label("", JLabel.CENTER);
		
		this.addKeyListenerToLabel(this.pause);
		pause.setLayout(null);
		pause.setBackground(Color.lightGray);
		pause.setBorder(BorderFactory.createLineBorder(Color.black));
		pause.setBounds(width, height/3, width, height);
		pause.setVisible(false);
		pause.setOpaque(true);
		
		this.resumeGame = new JButton("Resume");

		content.setLayout(null);
		resumeGame.setBounds(width+70, height/2, width/2, height/4);
		resumeGame.setBackground(new Color(108,146,212));
		resumeGame.setBorderPainted(false);
		resumeGame.setVisible(false);
		content.add(resumeGame);

		
		this.exitGame = new JButton("Exit");

		exitGame.setBounds(width+70, height/2 +100, width/2, height/4);
		exitGame.setBackground(new Color(108,146,212));
		exitGame.setBorderPainted(false);
		exitGame.setVisible(false);
		content.add(exitGame);



		resumeGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				hidePause();
				showGame();
				Game.getInstance().setRunning(true);
				Game.getInstance().start();
			}
		});

		// The application musts close when we click
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				System.exit(0); 
				// Should be improve with saving in xml ?
			}
		}); 

		//pause.getGraph().drawLine(1,1,1,200);
		
		pause.requestFocus();
		
		// Do not remove it!
		content.add(pause);
	}

	/**
	 * Set all Components of the Pause invisible
	 */
	public void hidePause() {
		pause.setVisible(false);
		resumeGame.setVisible(false);
		exitGame.setVisible(false);
		setPauseStatus(false);
	}

	/**
	 * Set all Components of the Pause visible
	 */
	public void showPause() {
		pause.setVisible(true);
		resumeGame.setVisible(true);
		exitGame.setVisible(true);
		setPauseStatus(true);
	}

	/**
	 * Hide the image and the Bases.
	 */
	public void hideGame() {
		image.setVisible(false);
		for (Base b : Game.getInstance().getBaseManager().getBases()) {
			b.setVisible(false);
		}
	}

	/**
	 * Show the image and Bases.
	 */
	public void showGame() {
		image.setVisible(true);
		for (Base b : Game.getInstance().getBaseManager().getBases()) {
			b.setVisible(true);
		}
	}
	
	public void giveFocusToPanel() {
		this.content.requestFocusInWindow();
	}
	
	/* 
	 * 
	 ************    Holder class : for the Singleton pattern implementation   ******************
	 */
	private AppliWindow() {
		super();
		this.width = 0;
//		private int height;
//		private Panel content;
//		private boolean buildToolsVisible = false;
//		
//		private JPanel panelInfoRealPlayer;
//		private JPanel panelInfoIAPlayers;
//		
//		private Label image;
//		private Label pause;
//		private JButton resumeGame;
//		private JButton exitGame;
//		private boolean pauseStatus;
	}
	
	
	private static class AppliWindowHolder
	{		
		// unique instance, not preinitialized
		private final static AppliWindow instance = new AppliWindow();
	}
 
	// Getter for the unique instance of the Singleton
	public static AppliWindow getInstance()
	{
		return AppliWindowHolder.instance;
	}
	
	/**
	 * Initializes the AppliWindow the first time
	 * @param title		The title of the window
	 * @param width		The width of the window
	 * @param height	The height of the window
	 * @param resize	Indicates if the window is resizable or not
	 * @param pathImage		The path of the background image (String)
	 * @throws MapFileException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void init(String title, int width, int height, boolean resize, String pathImage) throws MapFileException, JDOMException, IOException{
		this.width = width;
		this.height = height;
		
		this.panelInfoIAPlayers =  new JPanel();
		this.panelInfoRealPlayer = new JPanel();
		
		buildWindow(title, resize); // On initialise notre fenetre (We initiate the window)
		
		// Add a background, providing the path
		buildBackground(pathImage);
		
		// Add a pause menu
		buildPause(width/3, height/2);

	}


}
