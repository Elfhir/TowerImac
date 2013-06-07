package window;

import java.awt.Color;
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

import org.jdom2.JDOMException;

import application.Launcher;

import window.graphic.LabelAgents;
import window.graphic.Line;
import window.graphic.LineCursor;
import window.panel.Panel;
import window.panel.PanelInfoIAPlayers;
import window.panel.PanelInfoRealPlayer;
import window.panel.PanelLose;
import window.panel.PanelTmpTower;
import window.panel.PanelWin;
import exceptions.MapFileException;
import exceptions.RealPlayerException;
import game.Game;
import game.Theme;
import game.agent.GroupAgent;
import game.base.Base;
import game.player.Player;
import game.player.RealPlayer;
import game.tower.Tower;
/**
 * Singleton
 *
 */
@SuppressWarnings("serial")
public class AppliWindow extends JFrame {

	private int width;
	private int height;
	
	private static Panel content;
	
	
	private PanelInfoRealPlayer panelInfoRealPlayer;
	private PanelInfoIAPlayers panelInfoIAPlayers;
	private PanelLose MenuLose;
	private PanelWin MenuWin;
	private PanelTmpTower panelTmpTower = new PanelTmpTower();

	private Label image;
	private Line line;
	private LineCursor lineCursor;
	private LabelAgents labelAgent;
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
		return content;
	}

	public void setContent(Panel newContent) {
		content = newContent;
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

	public LabelAgents getLabelAgent() {
		return labelAgent;
	}

	public void setLabelAgent(LabelAgents labelAgent) {
		this.labelAgent = labelAgent;
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
				base.setIcon(new ImageIcon(ImageIO.read(new File(Theme.pathImageBase))));
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
					}

				}
			});

			content.add(base);
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
		
		if(!Launcher.modeSpectateur) {
			try {
				if(Game.getInstance().getPlayerManager().getRealPlayer().getIsDead()) {
					content.add(MenuLose);
					MenuLose.setVisible(true);
				}
			} catch (RealPlayerException e) {
				System.out.println("Mode Spectateur !");
			}
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
		buildLabelAgents(0, 0, getWidth(), getHeight());
		game.initGame(xmlFileName, mapFileName);
		buildBases();
		// Add a line
		buildLine(0, 0, getWidth(), getHeight());
		buildLineCursor(0, 0, getWidth(), getHeight());
		buildInfoPlayers();
		
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

		content = new Panel();

		content.setLayout(null);
		content.setBackground(Color.GRAY);
		
		if(Launcher.customGame) {
			buildGame("GameCustom.xml", "map");
		} else {
			buildGame("game.xml", "map");
		}
		
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
		content.add(this.image);

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
		for(GroupAgent a : Game.getInstance().getAgentManager().getAgents()){
			a.setVisible(false);
		}
		for(Tower t : Game.getInstance().getTowerManager().getTowers()) {
			t.setVisible(false);
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
		for(GroupAgent a : Game.getInstance().getAgentManager().getAgents()){
			a.setVisible(true);
		}
		for(Tower t : Game.getInstance().getTowerManager().getTowers()) {
			t.setVisible(true);
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
		content.requestFocusInWindow();
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
	
	/**
	 * 
	 * Holder class for the Singleton
	 */
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

	/**
	 * Build a Line which will be opaque except the line
	 * 
	 * @param x1 Coordinates of first point of the line
	 * @param y1 Coordinates of first point of the line
	 * @param x2 Coordinates of second point of the line
	 * @param y2 Coordinates of second point of the line
	 */
	public void buildLabelAgents(int x1, int y1, int x2, int y2) {
		LabelAgents l = new LabelAgents();
		
		content.setLayout(null);
		l.setVisible(true);
		l.setBounds(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		l.setLayout(null);
		l.setBorder(null);
	
		this.setLabelAgent(l);
		content.add(l);
	}
	
}
