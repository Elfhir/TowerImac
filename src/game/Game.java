package game;

import java.io.IOException;
import java.io.InputStream;

import org.jdom2.JDOMException;

import engine.Engine;

import writer.XmlReader;

import manager.AgentManager;
import manager.BaseManager;
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
	//private Map map;
	
	
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

	/*
	 *   Private constructor (Singleton)
	 */
	private Game() {
		super();
		this.playerManager = new PlayerManager();
		this.baseManager = new BaseManager();
		this.agentManager = new AgentManager();
		this.towerManager = new TowerManager();
		this.running = true;
	}
	
	public void initGame(String fileName) {
 		
 		try {
			XmlReader.createGame(this, fileName);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
	
	public void start() {

		Engine.getInstance().start();
		
		for(Player p: getPlayerManager().getPlayers()) {
			p.start();
		}
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

	public static void main(String[] args) {
		
	}
	
	
}
