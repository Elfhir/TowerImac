package game;

import javax.vecmath.Vector2f;

import time.Clock;
import time.TimerGame;
import time.Timerable;

public class Agent implements situable, Timerable{
	
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

	public void attackAgent(Agent enemy) {
		// If strength > stamina just a difference, else only 1 damage.
		int damage;
		if(!(enemy.stamina > this.strength)) damage = Math.abs(this.strength - enemy.stamina);
		else damage = 1;
		
		// Compute damages inflicted to PV only if agents last PV.
		if(enemy.PV <= 0) {
			System.out.println("enemy agent is dead");
		}
		if(this.PV <= 0) {
			System.out.println("our agent is dead");
		}
		
		if(!((enemy.PV <= 0) || (this.PV <= 0)) ){	
			enemy.PV = enemy.PV - damage;
			if(enemy.PV < 0) enemy.PV = 0;
			System.out.println("fight");
		}
	}
	
	public String printPV () {
		StringBuilder sb = new StringBuilder("");
		sb.append("has "+this.PV+" PV");
		return sb.toString();
	}
	
	public boolean move(Vector2f to, String how) {
		if(how == "straight") {
			
			// sqrt(x²+y²)
			float distance = (float) Math.sqrt((to.getX()-this.getPosition().getX())*(to.getX()-this.getPosition().getX()) +
												(to.getY() - this.getPosition().getY())*(to.getY()-this.getPosition().getY()));
			float increment = (float) (0.1*distance);
			
			// slope and y-intercept
			float a = ( to.getY()-this.getPosition().getY() ) / ( to.getX()-this.getPosition().getX() );
			float b = this.getPosition().getY() - a*this.getPosition().getX();
			// parametric equation
			float X = (float) (increment*this.getPosition().getX() + a);
			float Y = (float) (increment*this.getPosition().getY() + b);
			
			Vector2f newPosition = new Vector2f(X, Y);
			
			this.setPosition(newPosition);
			return true;
		}
		else return false;
	}
	
	@Override
	public void runTimer() {
		//
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

	//--------------------------------------------------ctor-----------------------
	public Agent(int PV, int speed, int stamina, int strength, Vector2f position, Player player) {
		this.PV = PV;
		this.speed = speed;
		this.stamina = stamina;
		this.strength = strength;
		this.position = position;
		this.player = player;
		
	}
	
	public static void main(String[] args) {
		
		Clock clock = new Clock(0, 0, 0);
		
		TimerGame tg = new TimerGame(1000, 0, 0, 0, clock);
		
	}


}
