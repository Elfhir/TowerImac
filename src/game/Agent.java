package game;

import javax.vecmath.Vector2f;


import time.TimerGame;
import time.Timerable;

public class Agent implements Situable, Timerable{
	
	private int PV;
	private int speed;
	private int strength;
	private int stamina;
	private boolean mouving;
	private Vector2f position;
	private Player player;
	
	public int getPV() {
		return PV;
	}

	public void setPV(int PV) {
		this.PV = PV;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
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
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean isMouving() {
		return mouving;
	}

	public void setMouving(boolean mouving) {
		this.mouving = mouving;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	/**
	* Instantly do the fight of two agents - 2 setPV(0) + control
	*
	*@param	enemy	Agent enemy to attack
	*
	*@return void
	*
	*/
	public void attackAgent(Agent enemy) {

		if(enemy.getPV() <= 0) {
			System.out.println("enemy agent is dead");
		}
		if(this.getPV() <= 0) {
			System.out.println("our agent is dead");
		}

		System.out.println("fight");
		enemy.setPV(0);
		this.setPV(0);
	}
	
	public String printPV () {
		StringBuilder sb = new StringBuilder("");
		sb.append("has "+this.getPV()+" PV");
		return sb.toString();
	}
	
	/**
	* move in a linear mode. Not pathfinding yet
	*
	*@param	to	Vector2f of destination
	*@param	how	String of how to move
	*
	*
	*@return	boolean		if the guy is moving or not.
	*
	*/
	public boolean move(Vector2f to, String how) {
		if(how == "straight") {
			
			// sqrt(x²+y²)
			//float distance = (float) Math.sqrt((to.getX()-this.getPosition().getX())*(to.getX()-this.getPosition().getX()) +
			//									(to.getY() - this.getPosition().getY())*(to.getY()-this.getPosition().getY()));
			//float increment = (float) (0.1*distance);
			
			float increment = 0.2f;
			
			// slope and y-intercept
			//float a = ( to.getY()-this.getPosition().getY() ) / ( to.getX()-this.getPosition().getX() );
			//float b = this.getPosition().getY() - a*this.getPosition().getX();
			// parametric equation
			float X = (float) (this.getPosition().getX() + increment*(to.getX() - this.getPosition().getX()));
			float Y = (float) (this.getPosition().getY() + increment*(to.getY() - this.getPosition().getY()));
			
			Vector2f newPosition = new Vector2f(X, Y);
			
			this.setPosition(newPosition);
			return true;
		}
		else return false;
	}
	

	@Override
	public void runTimer() {
		// example
		//System.out.println(this);
	}
	
	@Override
	public void runTimer(Timerable t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void runTimer(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	* If agent not arrived at the destination, he moves
	*
	*@param	v	Vector2f of destination
	*
	*@return	void
	*
	*/
	@Override
	public void runTimer(Vector2f v) {
		System.out.println(this.getPosition());
		if(this.getPosition().equals(v)) {
			return;
		}
		else {
			this.move(v, "straight");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("PV ");
		sb.append(this.PV);
		sb.append("\n");
		sb.append("speed: ");
		sb.append(this.speed);
		sb.append("\n");
		sb.append("stamina: ");
		sb.append(this.stamina);
		sb.append("\n");
		sb.append("strength: ");
		sb.append(this.strength);
		sb.append("\n");
		sb.append("position: ");
		sb.append(this.position);
		sb.append("\n");
		sb.append("player: ");
		sb.append(this.player);
		sb.append("\n");
		return sb.toString();
	}

	//--------------------------------------------------constructor-----------------------
	public Agent(boolean mouving, int PV, int speed, int stamina, int strength, Vector2f position, Player player) {
		this.mouving = mouving;
		this.PV = PV;
		this.speed = speed;
		this.stamina = stamina;
		this.strength = strength;
		this.position = position;
		this.player = player;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		Agent a1 = new Agent(true, 10, 5, 0, 0, new Vector2f(0.0f, 0.0f), null);
		//Agent a2 = new Agent(true, 6, 5, 0, 0, new Vector2f(2.0f, 2.0f), null);
		
		Vector2f dest = new Vector2f(2.0f, 3.0f);
		
		TimerGame tg = new TimerGame(1000, 0, 0, 0, a1, dest);
		
		
		
		
	}


}
