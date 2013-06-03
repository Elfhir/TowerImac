package game.tower;

import game.Situable;
import game.agent.Agent;
import game.player.Player;

import javax.swing.JButton;
import javax.vecmath.Vector2f;

import window.AppliWindow;

public abstract class Tower extends JButton implements Situable {

	protected float cadence;
	protected float radiusAreaOfAction;
	protected int sellPrice;
	protected int buyingPrice;
	protected int upgradePrice;
	protected int might;
	protected Player owner;
	protected Vector2f position;
	protected long momentOfLastShot;
	
	public Tower(float cadence, float radiusAreaOfAction, int sellPrice, int buyingPrice, int upgradePrice, int might, Player owner) {
		super();
		this.cadence = cadence;
		this.radiusAreaOfAction = radiusAreaOfAction;
		this.sellPrice = sellPrice;
		this.buyingPrice = buyingPrice;
		this.upgradePrice = upgradePrice;
		this.might = might;
		this.owner = owner;
	}
	
	public Tower(float x, float y, float cadence, float radiusAreaOfAction, int sellPrice, int buyingPrice, int upgradePrice, int might, Player owner) {
		this(cadence, radiusAreaOfAction, sellPrice, buyingPrice, upgradePrice, might, owner);
		this.setPosition(x, y);
		AppliWindow.getInstance().getContent().add(this);
	}


	public float getCadence() {
		return cadence;
	}


	public void setCadence(float cadence) {
		this.cadence = cadence;
	}


	public float getRadiusAreaOfAction() {
		return radiusAreaOfAction;
	}


	public void setRadiusAreaOfAction(float radiusAreaOfAction) {
		this.radiusAreaOfAction = radiusAreaOfAction;
	}


	public int getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}


	public int getBuyingPrice() {
		return buyingPrice;
	}


	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}


	public int getUpgradePrice() {
		return upgradePrice;
	}


	public void setUpgradePrice(int upgradePrice) {
		this.upgradePrice = upgradePrice;
	}


	public int getMight() {
		return might;
	}


	public void setMight(int might) {
		this.might = might;
	}


	public Player getOwner() {
		return owner;
	}


	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public Vector2f getPosition() {
		return this.position;
	}


	@Override
	public void setPosition(float x, float y) {
		if (this.position == null) {
			this.position = new Vector2f(x, y);
		}
		else {
			this.position.x = x;
			this.position.y = y;
		}
		
	}
	
	public long getMomentOfLastShot() {
		return this.momentOfLastShot;
	}
	
	public void setMomentOfLastShot(long momentOfLastShot) {
		this.momentOfLastShot = momentOfLastShot;
	}
	
	/**
	 * @param agent	 	the agent to shot
	 * @return if this tower can reach and shot an agent
	 */
	public boolean canShot(Agent agent) {
		double distance = Math.sqrt(Math.pow(this.position.x - agent.getPosition().x, 2) + Math.pow(this.position.y - agent.getPosition().y, 2));
		return distance < this.getRadiusAreaOfAction();
	}


	public String toString(String type) {
		StringBuilder sb = new StringBuilder("Tower [type=");
		sb.append(type);
		sb.append(", cadence=");
		sb.append(cadence);
		sb.append(", radiusAreaOfAction=");
		sb.append(radiusAreaOfAction);
		sb.append(", sellPrice=");
		sb.append(sellPrice);
		sb.append(", buyingPrice=");
		sb.append(buyingPrice);
		sb.append(", upgradePrice=");
		sb.append(upgradePrice);
		sb.append(", might=");
		sb.append(might);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", position=");
		sb.append(position);
		sb.append("]");
		return sb.toString();
	}
	
}
