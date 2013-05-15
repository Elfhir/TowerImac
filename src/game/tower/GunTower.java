package game.tower;

import javax.vecmath.Vector2f;

import game.Game;
import game.player.Player;

public class GunTower extends Tower {
	
	
	public GunTower(float x, float y, Player owner) {
		super(x, y, 10, 10, 50, 100, 80, 5, owner);
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
