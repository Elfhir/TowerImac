package game;

import javax.vecmath.Vector2f;

public class Base implements Situable{
	
	private int size;
	private static int MAX_SIZE;
	private int diameter;
	private Player player;
	private Vector2f position;	
	private int agents;
	
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
	
	public void setAgents(int agents) {
		agents = agents <= 0 ? -agents : agents;
		this.agents = agents;
	}
	
	public int getAgents() {
		return this.agents;
	}
	
	public void attackBase(Base enemy){
		System.out.println("Attack !\n");
		
		int maxAgent = this.getAgents() <= enemy.getAgents() ? this.getAgents() : enemy.getAgents();
		for(int i = 0; i < maxAgent; ++i) {
			// whoever had a LinkedList<Agent> should use attackAgent() here.
		}
		return;
	}
	
	//generateAgent()
	
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
		sb.append(this.getAgents());
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
	
	public Base(int size, int diameter, Player player, Vector2f position, int agents){
		super();
		this.size = size;
		this.diameter = diameter;
		this.player = player;
		this.position = position;
		this.agents = agents;		
	}
	
	public static void main(String[] args) {
		
		//Agent strongAgent = new Agent(true, 10, 10, 10, 10, null, null);
		//Agent weakAgent = new Agent(true, 5, 7, 5, 5, null, null);
		
		Base base1 = new Base(5, 5, null, new Vector2f(0.5f, 0.5f), 5);
		//System.out.println(base1);
		
		Base base2 = new Base(5, 5, null, new Vector2f(-0.5f, -0.5f), 5);
		//System.out.println(base2);
		
		
		// If one attacks, the other defenses also !
		base2.attackBase(base1);
		
	}


}
