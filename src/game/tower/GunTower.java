package game.tower;

import game.player.Player;

public class GunTower extends Tower {
	
	private static final long serialVersionUID = -8782275432848249067L;
	private Version version;	
	
	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}


	public enum Version {
		NORMAL(0, 0, 50, 30),
		SUPER(10, 10, 80, 20),
		CHUCKNORRIS(25, 30, Integer.MAX_VALUE, 50);
		
		private float extraCadence;
		private float extraRadius;
		private int upgradePrice;
		private int extraSellPrice;
		
		Version(float extraCadence, float extraRadius, int upgradePrice, int extraSellPrice) {
			this.extraCadence = extraCadence;
			this.extraRadius = extraRadius;
			this.upgradePrice = upgradePrice;
			this.extraSellPrice = extraSellPrice;
		}

		public float getExtraCadence() {
			return extraCadence;
		}

		public void setExtraCadence(float extraCadence) {
			this.extraCadence = extraCadence;
		}

		public float getExtraRadius() {
			return extraRadius;
		}

		public void setExtraRadius(float extraRadius) {
			this.extraRadius = extraRadius;
		}

		public int getUpgradePrice() {
			return upgradePrice;
		}

		public void setUpgradePrice(int upgradePrice) {
			this.upgradePrice = upgradePrice;
		}

		public int getExtraSellPrice() {
			return extraSellPrice;
		}

		public void setExtraSellPrice(int extraSellPrice) {
			this.extraSellPrice = extraSellPrice;
		}
		
	}
	
	@Override
	public float getCadence() {
		return super.getCadence() + version.getExtraCadence();
	}
	
	@Override
	public float getRadiusAreaOfAction() {
		return super.getRadiusAreaOfAction() + version.getExtraRadius();
	}
	
	public GunTower(float x, float y, Player owner) {
		// x, y, cadence, radiusAreaOfAction, sellPrice, buyingPrice, upgradePrice, might, owner
		super(x, y, 10, 10, 50, 100, 80, 5, owner);
		this.version = Version.NORMAL;
	}
	
	@Override
	public int getSellPrice() {
		return super.getSellPrice() + this.version.getExtraSellPrice();
	}
	
	@Override
	public int getUpgradePrice() {
		return this.version.getUpgradePrice();
	}
	
	
	@Override
	public String toString() {
		return super.toString("GunTower");
	}
	
}
