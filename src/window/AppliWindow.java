package window;

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

import window.graphic.Line;
import window.graphic.LineCursor;
import window.panel.Panel;
import window.panel.PanelInfoIAPlayers;
import window.panel.PanelInfoRealPlayer;
import window.panel.PanelLose;
import window.panel.PanelTmpTower;
import window.panel.PanelWin;
import exceptions.ClickedByRealPlayerException;
import exceptions.MapFileException;
import exceptions.RealPlayerException;
import game.Game;
import game.agent.GroupAgent;
import game.base.Base;
import game.player.Player;
import game.player.RealPlayer;
/**
 * Singleton
 *
 */
@SuppressWarnings("serial")
public class AppliWindow extends JFrame {

	private int width;
	private int height;
	
	private Panel content;
	
	
	private PanelInfoRealPlayer panelInfoRealPlayer;
	private PanelInfoIAPlayers panelInfoIAPlayers;
	private PanelLose MenuLose;
	private PanelWin MenuWin;
	private PanelTmpTower panelTmpTower = new PanelTmpTower();

	private Label image;
	private Line line;
	private LineCursor lineCursor;
	private Label pause;
	private JButton resumeGame;
	private JButton exitGame;
	private boolean pauseStatus;
	
	
	public PanelInfoRealPlayer getPanelInfoRealPlayer() {
		return this.panelInfoRealPlayer;
	}

	public void setPanelInfoRealPlayer(PanelInfoRealPlayer panelInfoRealPlayer) {
		this.panelInfoRealPlayer = panelInfoRealPlayer;
	}

	public PanelInfoIAPlayers getPanelInfoIAPlayers() {
		return this.panelInfoIAPlayers;
	}

	public void setPanelInfoIAPlayers(PanelInfoIAPlayers panelInfoIAPlayers) {
		this.panelInfoIAPlayers = panelInfoIAPlayers;
	}
	
	public PanelTmpTower getPanelTmpTower() {
		return panelTmpTower;
	}

	public void setPanelTmpTower(PanelTmpTower panelTmpTower) {
		this.panelTmpTower = panelTmpTower;
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

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	public LineCursor getLineCursor() {
		return lineCursor;
	}

	public void setLineCursor(LineCursor lineCursor) {
		this.lineCursor = lineCursor;
	}

	/**
	 * Builds the window of the application
	 * @param width		The width of the window
	 * @param height	The height of the window
	 * @param resize	indicates if the window is resizable or not
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws MapFileException 
	 * @throws RealPlayerException 
	 */ 
	private void buildWindow(String title, boolean resize) throws MapFileException, JDOMException, IOException, RealPlayerException {
		setTitle(title);
		setSize(this.width, this.height);
		setLocationRelativeTo(null); // null => The window is centered on the screen
		setResizable(resize); // Resizable window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application musts cMenuLoseOrWin when we click on the cross
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
						
						base.clicked();
						
					} catch (RealPlayerException e1) {
						System.err.println("Error with RealPlayer : can't manage the click.");
					} catch (ClickedByRealPlayerException e2) {
						e2.printStackTrace();
					}

				}
			});

			content.add(base);

		}
	}
	
	private void buildGroupAgent() {
		Game game = Game.getInstance();

		for(GroupAgent groupAgent: game.getAgentManager().getAgents()){
			JLabel groupe = new JLabel();
			groupe.setBounds((int)groupAgent.getPosition().x, (int)groupAgent.getPosition().y, getTilesSize(), getTilesSize());
			groupe.setOpaque(true);
			groupe.setBackground(groupAgent.getPlayer().getColor());
			groupe.setSize(groupAgent.GroupSize(),groupAgent.GroupSize());
			try {
				groupe.setIcon(new ImageIcon(ImageIO.read(new File("design/groupe.jpeg"))));
			} catch (IOException e2) {
				System.err.println("Error ! Loading GroupAgent image");
			}
		}
	}

	/**
	 * 
	 * Constructs and adds to the window the panels corresponding to the players' informations : money and number of bases
	 * For the RealPlayer, the panel can be enlarged to provide tools to buy towers.
	 * @throws RealPlayerException 
	 * 
	 */
	public void buildInfoPlayers() throws RealPlayerException {
		
		// panel for the Real Player
		PanelInfoRealPlayer panelRealPlayer = this.panelInfoRealPlayer;
		// panel for the IA Players
		PanelInfoIAPlayers panelIAPlayers = this.panelInfoIAPlayers;
		
		// we loop on each player
		for(final Player p: Game.getInstance().getPlayerManager().getPlayers()) {
			
			// if it is the RealPlayer, we fill the panelRealPlayer
			if(p instanceof RealPlayer) {
				
				panelRealPlayer.buildPanel(width, height, (RealPlayer)p);
			}	
			
			// else if it is an IA JPlayer we fill the panelIAPlayers with a new JPanel for each IAPlayer
			else {
				panelIAPlayers.buildPanel(width, height, p);
			}
		}
		
		// finally we add the two panels (panelRealPlayer and panelIAPlayers) to the window
		content.add(panelRealPlayer);
		content.add(panelIAPlayers);
		content.add(MenuLose);
		content.add(MenuWin);
		
		// and we add the temporarily panel displayed when a base is being created (on mouseMoved).
		// By default, this panel is not visible.
		content.add(panelTmpTower);
		
	}
	
	/**
	 * Updates the infos of the players in the corresponding panels.
	 * This function must be called each time a base is taken or a player earn money.
	 * 
	 */
	public void updateInfoPlayers() {

		int nbTotalBases = Game.getInstance().getBaseManager().getBases().size();
		
		// panel for the Real Player
		//JPanel panelRealPlayer = this.panelInfoRealPlayer;
		
		// panel for the IA Players
		//JPanel panelIAPlayers = this.panelInfoIAPlayers;
		
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
			
			// if it is the RealPlayer, we update the text of the label of the panelRealPlayer
			if(p instanceof RealPlayer) {
				panelInfoRealPlayer.update(p, nbTotalBases, nbBasesPlayer);
			}
			
			// else if it is an IA Player we change the text of the label in each panel of panelIAPlayers (= corresponding to each IAPlayer)
			else {
				
				panelInfoIAPlayers.updateComponent(indexIAPlayer, p, nbTotalBases, nbBasesPlayer);
				++indexIAPlayer;
			}
		}
		int nbPlayerDead = 0;
		for(Player p : Game.getInstance().getPlayerManager().getPlayers()) {
			if(p instanceof RealPlayer) {
				
			}
			else if(p.getIsDead() == true) {
				nbPlayerDead++;
			}
		}
		if(nbPlayerDead == (Game.getInstance().getPlayerManager().getPlayers().size()-1)) {
			MenuWin.setVisible(true);
		}
		nbPlayerDead = 0;
		
		try {
			if(Game.getInstance().getPlayerManager().getRealPlayer().getIsDead()) {
				MenuLose.setVisible(true);
			}
		} catch (RealPlayerException e) {
			e.printStackTrace();
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
	 * @throws RealPlayerException 
	 */
	private void buildGame(String xmlFileName, String mapFileName) throws MapFileException, JDOMException, IOException, RealPlayerException {
		Game game = Game.getInstance();
		game.initGame(xmlFileName, mapFileName);
		buildBases();
		// Add a line
		buildLine(0, 0, getWidth(), getHeight());
		buildLineCursor(0, 0, getWidth(), getHeight());
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
	 * @throws RealPlayerException 
	 */
	private Panel buildContentPane(int width, int height) throws MapFileException, JDOMException, IOException, RealPlayerException {

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

		// The application musts cMenuLoseOrWin when we click
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				System.exit(0); 
				// Should be improve with saving in xml ?
			}
		}); 
		
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
		line.setVisible(false);
		lineCursor.setVisible(false);
		panelInfoRealPlayer.setVisible(false);
		panelInfoIAPlayers.setVisible(false);
	}

	/**
	 * Show the image and Bases.
	 */
	public void showGame() {
		image.setVisible(true);
		for (Base b : Game.getInstance().getBaseManager().getBases()) {
			b.setVisible(true);
		}
		line.setVisible(true);
		lineCursor.setVisible(true);
		panelInfoRealPlayer.setVisible(true);
		panelInfoIAPlayers.setVisible(true);
	}
	
	/**
	 * Give to the content the Focus (requestFocusInWindow())
	 */
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
	 * @throws RealPlayerException 
	 */
	public void init(String title, int width, int height, boolean resize, String pathImage) throws MapFileException, JDOMException, IOException, RealPlayerException{
		this.width = width;
		this.height = height;
		
		this.panelInfoIAPlayers =  new PanelInfoIAPlayers();
		this.panelInfoRealPlayer = new PanelInfoRealPlayer();
		this.MenuLose = new PanelLose();
		this.MenuWin = new PanelWin();
		
		buildWindow(title, resize); // On initialise notre fenetre (We initiate the window)
		
		// Add a background, providing the path
		buildBackground(pathImage);

		// Add a pause menu
		buildPause(width/3, height/2);

	}
	
	/**
	 * Build a Line which will be opaque except the line
	 * 
	 * @param x1 Coordinates of first point of the line
	 * @param y1 Coordinates of first point of the line
	 * @param x2 Coordinates of second point of the line
	 * @param y2 Coordinates of second point of the line
	 */
	public void buildLine(int x1, int y1, int x2, int y2) {
		Line line1 = new Line(x1, y1, x2, y2, new Color(200, 0, 0));
		
		content.setLayout(null);
		line1.setVisible(true);
		line1.setBounds(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		line1.setLayout(null);
		line1.setBorder(null);
		line1.displayLine(0, 0, 0, 0);
		this.setLine(line1);
		content.add(line1);
	}
	
	/**
	 * Build a LineCursor which will be opaque except the lineCursor
	 * It will follow the mouse cursor !
	 * 
	 * @param x1 Coordinates of first point of the lineCursor
	 * @param y1 Coordinates of first point of the lineCursor
	 * @param x2 Coordinates of second point of the lineCursor
	 * @param y2 Coordinates of second point of the lineCursor
	 */
	public void buildLineCursor(int x1, int y1, int x2, int y2) {
		LineCursor lineCursor = new LineCursor(x1, y1, x2, y2, new Color(0, 150, 250));
		
		content.setLayout(null);
		lineCursor.setVisible(true);
		lineCursor.setBounds(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		lineCursor.setLayout(null);
		lineCursor.setBorder(null);
		lineCursor.displayLine(0, 0, 0, 0);
		this.setLineCursor(lineCursor);
		content.add(lineCursor);
	}


}
