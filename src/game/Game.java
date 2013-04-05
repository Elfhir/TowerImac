package game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	
	private ArrayList<Player> players;
	private ArrayList<Base> bases;
	//private Map map;
	//private LinkedList<Tower> towers;
	private LinkedList<Agent> agents;
	
	
	public Game() {
		super();
		this.players = new ArrayList<Player>();
		this.bases = new ArrayList<Base>();
		this.agents = new LinkedList<Agent>();
	}
	
	public Game(ArrayList<Player> players, ArrayList<Base> bases, LinkedList<Agent> agents) {
		super();
		this.players = players;
		this.bases = bases;
		this.agents = agents;
	}

	// Constructor from a file
	public Game(InputStream input) {
		
		// read xml file
		//this(players, bases, agents);
	}
		
	public static void main(String[] args) {
		
		
	}
	
	
}
