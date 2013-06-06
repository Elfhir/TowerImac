package game.tower;

import game.player.Player;

public class GelTower extends Tower {
	
	private static final long serialVersionUID = 7630721498155085037L;

	public GelTower(float x, float y, Player owner) {
		super(10, 10, 50, 100, 80, 5, owner);
	}

}
