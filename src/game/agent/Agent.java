package game.agent;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import game.Situable;
import game.Theme;
import game.player.Player;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.vecmath.Vector2f;

import time.Timerable;

public abstract class Agent extends JLabel implements Situable, Timerable{
	
	private static final long serialVersionUID = 1591504544865674942L;
	protected int PV;
	protected int speed;
	protected int strength;
	protected int stamina;
	protected boolean moving;
	protected Vector2f position;
	protected Player player;
	
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
		this.setLocation((int)x, (int)y);
		this.repaint();
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
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
	}
	
	@Override
	public void runTimer(Timerable t) {
	}
	
	@Override
	public void runTimer(boolean b) {
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
	public Agent(boolean moving, int PV, int speed, int stamina, int strength, Vector2f position, Player player) throws IOException {
		//super(new ImageIcon(ImageIO.read(new File("design/unites_"+(new Random().nextInt(10)+1)+".png"))), JLabel.CENTER);
		super(new ImageIcon(ImageIO.read(new File(Theme.pathImageUnite+(new Random().nextInt(10)+1)+".png"))), JLabel.CENTER);
		this.moving = moving;
		this.PV = PV;
		this.speed = speed;
		this.stamina = stamina;
		this.strength = strength;
		this.position = position;
		this.player = player;
		this.setVisible(true);
		this.setBounds(0, 0, 150, 150);
	}
	
	public Agent() throws IOException {
		this(false, 0, 0, 0, 0, new Vector2f(0,0), null);
	}
	
	/*
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		Agent a1 = new Agent(true, 10, 5, 0, 0, new Vector2f(0.0f, 0.0f), null);
		//Agent a2 = new Agent(true, 6, 5, 0, 0, new Vector2f(2.0f, 2.0f), null);

		Vector2f dest = new Vector2f(2.0f, 3.0f);
		
		TimerGame tg = new TimerGame(1000, 0, 0, 0, a1, dest);
		
	}*/

}
