package game;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 *  SINGLETON : technique du Holder, fonctionne en environnement multithreadé, sans nécessiter de synchronisation explicite.
 *  cf http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java 
 */

public class Game {
	
	private ArrayList<Player> players;
	private ArrayList<Base> bases;
	//private Map map;
	//private LinkedList<Tower> towers;
	private LinkedList<Agent> agents;
	
	// Constructeur : privé !
	private Game() {
		super();
		this.players = new ArrayList<Player>();
		this.bases = new ArrayList<Base>();
		this.agents = new LinkedList<Agent>();
	}
	
	public void initGame(InputStream input) {
 		
 		// read xml file
//		this.players = 
//		this.bases = 
//		this.agents = 
	}
	
	/** Holder */
	private static class GameHolder
	{		
		/** Instance unique non préinitialisée */
		private final static Game instance = new Game();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Game getInstance()
	{
		return GameHolder.instance;
	}
		
	public static void main(String[] args) {
		
	}
	
	
}
