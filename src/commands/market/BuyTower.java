package commands.market;

import javax.vecmath.Vector2f;

import commands.Command;

import game.Game;
import game.player.Player;
import game.player.RealPlayer;
import game.tower.GunTower;
import game.tower.Tower;


public class BuyTower extends Command {
	
	private Player player;
	private String typeTower;
	private Vector2f position;
	
	@Override
	public void doCommand() {
		
		GunTower gt = new GunTower(this.position.x, this.position.y, this.player);
		Game.getInstance().getTowerManager().addTower(gt);
		
		if (this.player instanceof RealPlayer) {
			((RealPlayer)this.player).setBuildingTower(false);
		}
		System.out.println(Game.getInstance().getTowerManager());
	}
	
	public BuyTower(Player player, String typeTower, int x, int y) {
		super();
		this.player = player;
		this.typeTower = typeTower;
		this.position = new Vector2f(x, y);
	}
}
