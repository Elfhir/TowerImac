package game.tower;

import java.awt.event.MouseEvent;

import javax.vecmath.Vector2f;

import game.Game;
import game.player.Player;

public class GunTower extends Tower {
	
	private Version version;
	
	public enum Version {
		NORMAL(0, 0),
		SUPER(10, 10),
		CHUCKNORRIS(25, 30);
		
		private float extraCadence;
		private float extraRadius;
		
		Version(float extraCadence, float extraRadius) {
			this.extraCadence = extraCadence;
			this.extraRadius = extraRadius;
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
	public String toString() {
		return super.toString("GunTower");
	}



	public static void main(String[] args) {
		GunTower gunTower = new GunTower(50, 100, null);
		Game game = Game.getInstance();
		game.getTowerManager().addTower(gunTower);
		
		System.out.println(gunTower);
	}
	
}
