package game.base;

import engine.Engine;
import exceptions.ClickedByRealPlayerException;
import exceptions.RealPlayerException;
import game.Game;
import game.Situable;
import game.player.Player;

import javax.swing.JButton;
import javax.vecmath.Vector2f;

import time.Timerable;
import window.AppliWindow;

import commands.attack.AttackBase;
import commands.selection.Move;
import commands.selection.SelectBase;

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
	
	public Vector2f getPositionCenter() {
		int size = AppliWindow.getTilesSize();
		return new Vector2f(position.x + size/2, position.y + size/2);
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
	
	public void changePlayer(Player newPlayer, int nbAgentsToAdd) {
		this.addAgents(nbAgentsToAdd);
		this.setPlayer(newPlayer);
		this.setBackground(newPlayer.getColor());
		AppliWindow.getInstance().updateInfoPlayers();
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
	 * @throws ClickedByRealPlayerException 
	 */
	public void clicked() throws RealPlayerException, ClickedByRealPlayerException {
		
		Player realPlayer = null;
		try {
			realPlayer = Game.getInstance().getPlayerManager().getRealPlayer();
		} catch (RealPlayerException e) {
			e.printStackTrace();
			return;
		}
		Base selectedBases = realPlayer.getSelectedBases();
		
		// 1st case : the player doesn't have any selected base, so current one become his selected base (if it's his base) !
		if(selectedBases == null) {
			
			// if he selects a neutral base : nothing is done
			if(this.getPlayer() == null){
				System.out.println("1st case : it's a neutral base, you can't select it !");
			}
			// if he selects the base of an other player : nothing is done
			else if((this.getPlayer().getName() != realPlayer.getName())){
				System.out.println("1st case : it's not your base, you can't select it !");
			}
			// if he selects one of his base : we add the command selection
			else if((this.getPlayer().getName() == realPlayer.getName())) {
//				realPlayer.setSelectedBases(this);
//				this.setBackground(realPlayer.getColor().darker());
//				System.out.println("1st case : Base from "+this.getPlayer().getName()+" selected!");
				SelectBase selectionCommand = new SelectBase(realPlayer, this);
				Engine.getInstance().getCommands().add(selectionCommand);
				
				//it sets the first point of Line, which is the coordinate of the first Base, i.e the SelectedBase
				AppliWindow.getInstance().getLine().displayFirstPoint(this);
			}
		}
		
		// 2nd case : current base is already selected by the realPlayer : nothing is done
		else if (selectedBases.equals(this)) {
			System.out.println("2nd case : Base from : "+realPlayer.getSelectedBases().getName()+" already selected.");
		}
		
		// 3rd case : the realPlayer has an other base selected : agents can go from the selected base to current one ! (and we deselect the base)
		else {
			
			Move moveCommand = new Move(realPlayer, selectedBases, this);
			Engine.getInstance().getCommands().add(moveCommand);
		}
		
		System.out.println("J'ai cliqué sur la base numéro "+this.getId());
		
		// Since a Base has the focus, we give back to the content the focus
		System.out.println("Le Panel content reprend le focus");
		AppliWindow.getInstance().getContent().requestFocusInWindow();
		
		
		
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
	
	public boolean equals(Base b) {
		if(this.id == b.id) {
			return true;
		} else {
			return false;
		}
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
}
