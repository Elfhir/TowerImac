package game;

import java.io.IOException;

import org.jdom2.JDOMException;

import engine.Engine;
import exceptions.MapFileException;
import game.player.Player;

import window.AppliWindow;
import writer.XmlReader;

import manager.AgentManager;
import manager.BaseManager;
import manager.MapManager;
import manager.PlayerManager;
import manager.TowerManager;

/*
 *  SINGLETON : Holder technical, Singleton pattern implementation in a multithreaded environment (without explicit synchronization)
 *  cf http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java 
 */

public class Game {
	
	private boolean running;  // Indicates if the game is currently running or not

	private PlayerManager playerManager;
	private BaseManager baseManager;
	private AgentManager agentManager;
	private TowerManager towerManager;
	private MapManager mapManager;
	
	
	/*
	 *   Getters
	 */
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public BaseManager getBaseManager() {
		return baseManager;
	}

	public void setBaseManager(BaseManager baseManager) {
		this.baseManager = baseManager;
	}

	public AgentManager getAgentManager() {
		return agentManager;
	}

	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}
	
	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public void setMapManager(MapManager mapManager) {
		this.mapManager = mapManager;
	}
	
	public MapManager getMapManager() {
		return mapManager;
	}

	/*
	 *   Private constructor (Singleton)
	 */
	private Game() {
		super();
		this.playerManager = new PlayerManager();
		this.baseManager = new BaseManager();
		this.agentManager = new AgentManager();
		this.towerManager = new TowerManager();
		this.mapManager = new MapManager();
		this.running = true;
	}
	
	public void initGame(String xmlFileName, String mapFileName) throws MapFileException, JDOMException, IOException {
 		
		// Nettoie les élèments des listes pour pouvoir recréer après.
		this.clearGame();
		
		// we create the game from the XML file
 		XmlReader.createGame(this, xmlFileName);
 		
 		// we set the map grill from the mapFile
 		Game.getInstance().getMapManager().setMapFromFile(mapFileName);
 		
 		System.out.println("initial map :");
 		System.out.println(Game.getInstance().getMapManager());
 		
 		// we calculate the areas of each base
 		Game.getInstance().getMapManager().calculateAreas(800, 600); // need to be improved
 		
 		System.out.println("calculated map :");
 		System.out.println(Game.getInstance().getMapManager());
	}
	
	/**
	 * Pour nettoyer les élèments de Jeu
	 */
	private void clearGame() {
		this.playerManager.getPlayers().clear();
 		this.baseManager.getBases().clear();
 		this.agentManager.getAgents().clear();
 		this.towerManager.getTowers().clear();
 		this.running = true;
	}

	public void start() {

		Engine.getInstance().start();
		
		for(Player p: getPlayerManager().getPlayers()) {
			p.start();
		}
	}
	
	/**
	 * Evolution of the money of all players, bases on a wealth coefficient,
	 * multiply the number of base (the more you had, the more you get)
	 * @param wealth	coefficient x player.getNbBases();
	 */
	public void economicalEvolution(float wealth) {
		for(Player p : Game.getInstance().getPlayerManager().getPlayers()) {
			p.getBank().setMoney(p.getBank().getMoney() + (wealth*p.getNbBases()/5));
		}
		AppliWindow.getInstance().updateInfoPlayers();
	}
	
	/* 
	 * 
	 ************    Holder class : for the Singleton pattern implementation   ******************
	 */
	
	private static class GameHolder
	{		
		// unique instance, not preinitialized
		private final static Game instance = new Game();
	}
 
	// Getter for the unique instance of the Singleton
	public static Game getInstance()
	{
		return GameHolder.instance;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Game : \n");
		sb.append(playerManager);
		sb.append("\n");
		sb.append(baseManager);
		sb.append("\n");
		sb.append(agentManager);
		sb.append("\n");
		sb.append(towerManager);
		sb.append("\n");
		return sb.toString();
	}

}
