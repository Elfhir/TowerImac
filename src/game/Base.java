package game;

import javax.swing.JButton;
import javax.vecmath.Vector2f;

import exceptions.IAPlayerException;
import exceptions.RealPlayerException;

import time.TimerGame;
import time.Timerable;

public class Base extends JButton implements Situable, Timerable{
	
	private int id;
	private int might;
	private Player player;
	private Vector2f position;
	private int nbAgents;
	private float momentOfTheLastGeneration;
	
	public int getMight() {
		return might;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}
		
	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean hasPlayer() {
		if (player != null) {
			return true;
		}
		else return false;
	}
	
	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	public void setNbAgents(int agents) {
		agents = (agents <= 0) ? 0 : agents;
		this.nbAgents = agents;
	}
	
	public int getNbAgents() {
		return this.nbAgents;
	}
	
	public float getMomentOfTheLastGeneration() {
		return momentOfTheLastGeneration;
	}

	public void setMomentOfTheLastGeneration(float momentOfTheLastGeneration) {
		this.momentOfTheLastGeneration = momentOfTheLastGeneration;
	}

	public void attackBase(Base enemy){
		System.out.println("Attack !\n");
		
		int maxAgent = (int) Math.max(this.getNbAgents()*0.5f, enemy.getNbAgents());
		for(int i = 0; i < maxAgent; ++i) {
			// whoever had a LinkedList<Agent> should use attackAgent() here.
			
		}
		return;
	}
	
	public void generateAgent() {
		this.setNbAgents(this.getNbAgents() + 1);
	}
	
	public void deleteAgent() {
		if(this.getNbAgents() <= 0) return;
		this.setNbAgents(this.getNbAgents() - 1);
	}
	
	/**
	 * Deletes a specific number of agents in the base.
	 * @param nbSentAgents	The number of agents to delete
	 */
	public void deleteAgents(int nbComingAgents) {
		if(nbComingAgents >= this.nbAgents) {
			this.nbAgents = 0;
			// It will need to be improved : when the coming agents are superior than the current agents, then the base change of player (and some agents stay).
		}
		else {
			this.nbAgents -= nbComingAgents; 
		}
	}
	
	/**
	 * Add a specific number of agents in the base.
	 * @param nbSentAgents The number of agents to add to the base
	 */
	public void addAgents(int nbComingAgents) {
		this.nbAgents += nbComingAgents; 
	}
	
	/**
	 *  Manages the click on a Base.
	 *  A click is obviously an action from the RealPlayer. 
	 *  So we manage what to do according to the data of this unique realPlayer : if he has a selected base or not.
	 * @throws RealPlayerException  
	 * @deprecated Use the Commands now see in commands.selection and try in AppliWindow
	 */
	@Deprecated
	public void clickedByRealPlayer() throws RealPlayerException {
		Player realPlayer = Game.getInstance().getPlayerManager().getRealPlayer();
		Base selectedBases = realPlayer.getSelectedBases();
		
		// 1st case : the player doesn't have any selected base, so this one become his selected base !
		if(selectedBases == null) {
			//select a neutral base
	
			if(this.getPlayer() == null){
				realPlayer.setSelectedBases(null);
				System.out.println("1st case : it's a neutral base !");
			}
			//select one of his bases
			else if((this.getPlayer().getName() == realPlayer.getName()) && (this.getPlayer() != null)) {
				realPlayer.setSelectedBases(this);
				System.out.println("1st case : Base from "+this.getPlayer().getName()+" selected!");
			}
			//select an other player's base
			else if((this.getPlayer().getName() != realPlayer.getName()) && (this.getPlayer() != null)){
				realPlayer.setSelectedBases(null);
				System.out.println("1st case : it's not your base !");
			}

		}
		// 2nd case : this base is already selected by the realPlayer : nothing is done
		else if (selectedBases.equals(this)) {
			System.out.println("2nd case : Base from : "+realPlayer.getSelectedBases().getName()+" already selected.");
		}
		
		// 3rd case : the realPlayer has an other base selected : agents can go from the selected base to this one ! (and we deselect the base)
		else {
			int nbSentAgents = selectedBases.getNbAgents() / 2;
			// the selected base send the agents
			
			// 3rd case : Attack
			if(this.getPlayer() != selectedBases.getPlayer()) {
				System.out.println("3rd case : Attack !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				
				// The number of agents in the Base attacked decrease !
				this.deleteAgents(nbSentAgents);
				
				// Enemy Base is taken !!
				if(this.getNbAgents() == 0) {
					this.setPlayer(selectedBases.getPlayer());
					this.setBackground(selectedBases.getPlayer().getColor());
				}
			} 
			
			// 3rd case : Move
			else {
				
				System.out.println("3rd case : Move !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				if(!selectedBases.equals(this)) {
					//The number of Agent in the base where we move increase !
					this.addAgents(nbSentAgents);
				}
			}
			// The number of Agents in our selected Base decrease !
			selectedBases.deleteAgents(nbSentAgents);
			realPlayer.setSelectedBases(null);
			
			// Now that we have moved or attacked, we deselect !
			selectedBases = null;
		}
		
		System.out.println("J'ai cliqué sur la base numéro "+this.getId());
		if(selectedBases == null) {
			System.out.println("Pas de base selected !");
		}else{
			System.out.println("Ma base selected est celle de : "+selectedBases.getPlayer().getName());
		}
	}
	
	/**
	 *  Manages a random click from IA on the bases.
	 *  
	 * @deprecated 
	 * @throws IAPlayerException  
	 */
	@Deprecated
	public void clickedByIAPlayer() throws IAPlayerException {
		
		// Select a random IA player
		int lower = 0;
		int higher = Game.getInstance().getPlayerManager().getPlayers().size();
		int random = (int)(Math.random() * (higher-lower)) + lower;
		
		Player IAPlayer = null;
		try {
			IAPlayer = Game.getInstance().getPlayerManager().getIAPlayer(random);
		} catch (IAPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Base selectedBases = IAPlayer.getSelectedBases();

		// 1st case : the IA player doesn't have any selected base, so this one become his selected base !
		if(selectedBases == null) {
			
			IAPlayer.setSelectedBases(this);
			IAPlayer.getSelectedBases().setName(IAPlayer.getName());
			
			// Check if the current base selected is a base the IA player owned
			for(int i = 0; i<Game.getInstance().getBaseManager().getBases().size(); ++i) {
				if(IAPlayer.getSelectedBases().getName() == Game.getInstance().getBaseManager().getBases().get(i).getName()){
					break;
				}
				else 
					System.out.println("C'est pas à toi !");
					
					IAPlayer.getSelectedBases().setName(null);
					IAPlayer.setSelectedBases(null);	
					return;
			}
			
			
			System.out.println("Base from : "+IAPlayer.getSelectedBases().getName()+" selected!");
		}
		// 2nd case : this base is already selected by the IAPlayer : nothing is done
		else if (selectedBases.equals(this)) {
			System.out.println(selectedBases);
			System.out.println("Base from : "+IAPlayer.getSelectedBases().getName()+" already selected.");
		}
		
		// 3rd case : the IA Player has an other base selected : agents can go from the selected base to this one ! (and we deselect the base)
		else {
			System.out.println("Go go !!");
			
			int nbSentAgents = selectedBases.getNbAgents() / 2;
			// the selected base send the agents
			
			if(this.getPlayer() != selectedBases.getPlayer()) {
				// It's an attack !
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				this.deleteAgents(nbSentAgents);
				if(this.getNbAgents() == 0) {
					this.setPlayer(selectedBases.getPlayer());
					this.setBackground(selectedBases.getPlayer().getColor());
				}
			} else {
				// It's only a move !
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				if(!selectedBases.equals(this)) {
					this.addAgents(nbSentAgents);
				}
			}
			// and the agents of this base are killed !
			selectedBases.deleteAgents(nbSentAgents);
			IAPlayer.getSelectedBases().setName(null);
			IAPlayer.setSelectedBases(null);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Base :\n");
		sb.append("might ");
		sb.append(this.might);
		sb.append("\n");
		sb.append("player: ");
		sb.append(this.player);
		sb.append("\n");
		sb.append("position: ");
		sb.append(this.position);
		sb.append("\n");
		sb.append("nbAgents: ");
		sb.append(this.getNbAgents());
		sb.append("\n");
		return sb.toString();
	}
	// -------------------------------------------------- Timerable ---------------
	
	@Override
	public void runTimer() {
		System.out.println(this.getNbAgents());
		this.generateAgent();
	}

	@Override
	public void runTimer(Timerable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runTimer(Vector2f v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void runTimer(boolean b) {
		System.out.println(this.getNbAgents());
		if(b == true)
			this.generateAgent();
		else
			this.deleteAgent();
	}

	
	// ----------------------------------------------------constructor-------------
	
	public Base() {
		this(0);
	}
	
	public Base(int might){
		this(might, null);
	}
	
	public Base(int might, Player player){
		this(might, player, new Vector2f(0, 0));
	}
	
	public Base(int might, Player player, Vector2f position){
		this(might, player, position, 0);
	}
	
	public Base(int might, Player player, Vector2f position, int nbAgents){
		super();
		this.might = might;
		this.player = player;
		this.position = position;
		this.nbAgents = nbAgents;
		this.momentOfTheLastGeneration = 0.0f;
	}
	
	public Base(int id, int might, Player player, Vector2f position, int nbAgents){
		super();
		this.setId(id);
		this.might = might;
		this.player = player;
		this.position = position;
		this.nbAgents = nbAgents;
		this.momentOfTheLastGeneration = 0.0f;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//Agent strongAgent = new Agent(true, 10, 10, 10, 10, null, null);
		//Agent weakAgent = new Agent(true, 5, 7, 5, 5, null, null);
		
		Base base1 = new Base(5, null, new Vector2f(0.5f, 0.5f), 5);
		//System.out.println(base1);
		
		Base base2 = new Base(5, null, new Vector2f(-0.5f, -0.5f), 5);
		//System.out.println(base2);
		
		
		// If one attacks, the other defenses also !
		base2.attackBase(base1);
		

		TimerGame tg = new TimerGame(1000/base1.getMight(), 0, 0, 0, base1, false);

		
	}




}
