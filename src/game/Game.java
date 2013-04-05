package game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	
	private ArrayList<Player> mPlayers;
	private ArrayList<Base> mBases;
	//private Map map;
	//private LinkedList<Tower> mTowers;
	private LinkedList<Agent> mAgents;
	
	
	public Game() {
		super();
		mPlayers = new ArrayList<Player>();
		mBases = new ArrayList<Base>();
		mAgents = new LinkedList<Agent>();
	}
	
	public Game(ArrayList<Player> mPlayers, ArrayList<Base> mBases, LinkedList<Agent> mAgents) {
		super();
		this.mPlayers = mPlayers;
		this.mBases = mBases;
		this.mAgents = mAgents;
	}

	// Constructor from a file
	public Game(InputStream input) {
		
		// read xml file
		//this(players, bases, agents);
	}
		
	public static void main(String[] args) {
		
		
	}
	
	
}
