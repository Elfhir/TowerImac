package game.agent;

import engine.Engine;
import game.base.Base;
import game.player.Player;

import java.awt.Dimension;
import java.io.IOException;

import javax.vecmath.Vector2f;

import window.AppliWindow;

import commands.attack.AttackBase;

public class GroupAgent extends Agent {

	private static final long serialVersionUID = 5627032306218841026L;
	private int nbAgent;
	private Base baseOrigin;
	private Base baseDestination;
	private Vector2f vectorDirector;

	public GroupAgent(int nbInitialAgent, Base source, Base destination, Player player) throws IOException{
		super(true, 10, 10, 15, 15, new Vector2f(source.getPositionCenter().x, source.getPositionCenter().y), player);
		this.nbAgent = nbInitialAgent;
		this.baseOrigin = source;
		this.baseDestination = destination;
		this.position = new Vector2f(source.getPositionCenter().x, source.getPositionCenter().y);
		this.speed = 1;
		this.player = player;
		this.computeVectorDirector();

		this.setBackground(source.getPlayer().getColor());

		Dimension dim = new Dimension(this.nbAgent, this.nbAgent);
		this.setSize(dim);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		
	}

	public Base getBaseOrigin() {
		return baseOrigin;
	}

	public void setBaseOrigin(Base baseOrigin) {
		this.baseOrigin = baseOrigin;
	}

	public Base getBaseDestination() {
		return baseDestination;
	}

	public void setBaseDestination(Base baseDestination) {
		this.baseDestination = baseDestination;
	}

	public Vector2f getVectorDirector() {
		return vectorDirector;
	}

	public void setVectorDirector(Vector2f vectorDirector) {
		this.vectorDirector = vectorDirector;
	}

	public void setNbAgent(int nbAgent) {
		this.nbAgent = nbAgent;
	}

	/**
	 * Compute the Vector director, i.e the good direction agent to go
	 */
	private void computeVectorDirector() {
		this.vectorDirector = new Vector2f();
		this.vectorDirector.x = this.baseDestination.getPositionCenter().x - baseOrigin.getPositionCenter().x;
		this.vectorDirector.y = baseDestination.getPositionCenter().y - baseOrigin.getPositionCenter().y;
		this.vectorDirector.normalize();
	}

	public int getNbAgent() {
		return this.nbAgent;
	}

	public void moveOneStep(){

		int sizeBase = AppliWindow.getInstance().getTilesSize();
		
		boolean agentArrived = ((this.position.x > baseDestination.getPosition().x) && (this.position.x < baseDestination.getPosition().x + sizeBase)) &&
				((this.position.y > baseDestination.getPosition().y) && (this.position.y < baseDestination.getPosition().y + sizeBase));
		
		if(!agentArrived) {
			float x = this.position.x + this.vectorDirector.x * this.speed;
			float y = this.position.y += this.vectorDirector.y * this.speed;
			this.setPosition(x, y);
		}
		else{
			
			AttackBase attackCommand = new AttackBase(baseDestination, this);
			Engine.getInstance().getCommands().add(attackCommand);
			return;
		}
	}

}	

