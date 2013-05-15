package game.tower;

import game.player.Player;

public class Tower {

	private float cadence;
	private float radiusAreaOfAction;
	private int sellPrice;
	private int buyingPrice;
	private int upgradePrice;
	private int might;
	private Player owner;
	
	
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
	
	
}
