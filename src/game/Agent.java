package game;

import javax.vecmath.Vector2f;

public class Agent implements situable{
	
	private int PV;
	private int speed;
	private int strength;
	private int stamina;
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
	
	public void attackAgent(Agent enemy) {
		// If strength > stamina just a diff, else only 1 damage.
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
	
	public void moveTo(Vector2f dest) {
		this.setPosition(dest.x, dest.y);
	}
	
	public boolean move(Vector2f from, Vector2f to, String how) {
		if(how == "straight") {
			
			//float distance = (float) Math.sqrt((to.x-from.x)*(to.x-from.x) + (to.y - from.y)*(to.y-from.y));
			
			//timer, timertask ?
			
			this.moveTo(to);
			return true;
		}
		else return false;
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
		
	}

}
