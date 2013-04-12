package game;

import java.util.Iterator;
import java.util.LinkedList;
import javax.vecmath.Vector2f;

public class Base implements Situable, Iterable<Agent>{
	
	private int size;
	private static int MAX_SIZE;
	private int diameter;
	private Player player;
	private Vector2f position;
	
	private LinkedList<Agent> agents;
	
	public int getSize() {
		return size;
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public Player getPlayer() {
		if(hasPlayer()) {
			return player;
		}
		else return null;
	}
	
	public int getMaxSize() {
		return Base.MAX_SIZE;
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
	
	public void setAgents(LinkedList<Agent> agents) {
		this.agents = agents;
	}
	
	public LinkedList<Agent> getAgents() {
		return this.agents;
	}
	
	public Agent getAgent(int index) {
		return agents.get(index);
	}
	
	@Override
	public Iterator<Agent> iterator() {
		return agents.iterator();
	}
	
	public void attackBase(Base enemy){
		System.out.println("Attack !\n");
		
		for(Agent it : this.agents) {
			if(it.getPV() == 0) continue;
			for(Agent jt : enemy.agents) {
				if(it.getPV() == 0 || jt.getPV() == 0) break;
				it.attackAgent(jt);
				jt.attackAgent(it);
			}
		}
	}
	
	public boolean generateAgent(Agent e) {
		if(this.hasPlayer()) {
			
			// timer, timerTask ?
			// need to use also size of Base
			this.getAgents().add(e);
			
			return true;
		}
		else return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("size ");
		sb.append(this.size);
		sb.append("\n");
		sb.append("MAX_SIZE: ");
		sb.append(Base.MAX_SIZE);
		sb.append("\n");
		sb.append("diameter: ");
		sb.append(this.diameter);
		sb.append("\n");
		sb.append("player: ");
		sb.append(this.player);
		sb.append("\n");
		sb.append("position: ");
		sb.append(this.position);
		sb.append("\n");
		sb.append("agents: ");
		sb.append(this.agents.size());
		sb.append("\n");
		return sb.toString();
	}
	// ----------------------------------------------------constructor-------------
	
	public Base(int size, int diameter){
		super();
		this.size = size;
		this.diameter = diameter;
		player = null;
	}
	
	public Base(int size, int diameter, Player player){
		super();
		this.size = size;
		this.diameter = diameter;
		this.player = player;
	}
	
	public Base(int size, int diameter, Player player, Vector2f position){
		super();
		this.size = size;
		this.diameter = diameter;
		this.player = player;
		this.position = position;
	}
	
	public Base(int size, int diameter, Player player, Vector2f position, Agent agent, int nbAgent){
		super();
		this.size = size;
		this.diameter = diameter;
		this.player = player;
		this.position = position;
		this.agents = new LinkedList<Agent>();
		
		// No constructor LinkedList with a number of entities WTF? So this it :
		for(int i = 0; i<nbAgent; ++i){
			agents.add(agent);
		}
		
	}
	
	public static void main(String[] args) {
		
		Agent strongAgent = new Agent(true, 10, 10, 10, 10, null, null);
		Agent weakAgent = new Agent(true, 5, 7, 5, 5, null, null);
		
		Base base1 = new Base(5, 5, null, new Vector2f(0.5f, 0.5f), strongAgent, 5);
		//System.out.println(base1);
		
		Base base2 = new Base(5, 5, null, new Vector2f(-0.5f, -0.5f), weakAgent, 5);
		//System.out.println(base2);
		
		
		// If one attacks, the other defenses also !
		base2.attackBase(base1);
		
	}

}
