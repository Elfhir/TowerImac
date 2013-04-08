package game;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.LinkedList;

import manager.AgentManager;
import manager.BaseManager;
import manager.PlayerManager;
import manager.TowerManager;

/*
 *  SINGLETON : Holder technical, Singleton pattern implementation in a multithreaded environment (without explicit synchronization)
 *  cf http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java 
 */

public class Game {
	
	private PlayerManager playerManager;
	private BaseManager baseManager;
	private AgentManager agentManager;
	private TowerManager towerManager;
	//private Map map;
	
	
	/*
	 *   Getters
	 */
	public ArrayList<Player> getPlayers() {
		return this.playerManager.getPlayers();
	}
	
	public ArrayList<Base> getBases() {
		return this.baseManager.getBases();
	}
	
	public LinkedList<Agent> getAgents() {
		return this.agentManager.getAgents();
	}
	
	public LinkedList<Tower> getTowers() {
		return this.towerManager.getTowers();
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
	}
	
	public void initGame(InputStream input) {
 		
 		// read xml file
//		this.players = 
//		this.bases = 
//		this.agents = 
	}
	
	/* 
	 * Holder class : for the Singleton pattern implementation
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
		Game game = Game.getInstance();
		
		Bank bank1 = new Bank(10);
		Player p1 = new Player("Jean-Marc", bank1);
		
		Bank bank2 = new Bank(12);
		Player p2 = new Player("Jean-Gui", bank2);
		
		game.getPlayerManager().addPlayer(p1);
		game.getPlayerManager().addPlayer(p2);
		
		System.out.println(game);
	}
	
	
}
