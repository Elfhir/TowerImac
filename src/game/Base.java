package game;

import javax.swing.JButton;
import javax.vecmath.Vector2f;

import time.TimerGame;
import time.Timerable;

public class Base extends JButton implements Situable, Timerable{
	
	private int might;
	private Player player;
	private Vector2f position;
	private int nbAgents;
	
	public int getMight() {
		return might;
	}
	
	public Player getPlayer() {
		return player;
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
	
	public void attackBase(Base enemy){
		System.out.println("Attack !\n");
		
		int maxAgent = Math.max(this.getNbAgents(), enemy.getNbAgents());
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
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
	
	public Base(int might){
		super();
		this.might = might;
		this.player = null;
		this.position = new Vector2f(0, 0);
		this.nbAgents = 0;
	}
	
	public Base(int might, Player player){
		super();
		this.might = might;
		this.player = player;
		this.position = new Vector2f(0, 0);
	}
	
	public Base(int might, Player player, Vector2f position){
		super();
		this.might = might;
		this.player = player;
		this.position = position;
		this.nbAgents = 0;
	}
	
	public Base(int might, Player player, Vector2f position, int nbAgents){
		super();
		this.might = might;
		this.player = player;
		this.position = position;
		this.nbAgents = nbAgents;		
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
