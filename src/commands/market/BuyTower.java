package commands.market;

import commands.Command;

import game.player.Player;
import game.tower.Tower;


public class BuyTower extends Command {
	
	Player player;
	Tower tower;
	
	@Override
	public void doCommand() {
		// tower.upgrade();
		System.out.println(player.getName() + " achète la tour : " + tower);
		
	}
	
	public BuyTower(Player player, Tower tower) {
		super();
		this.player = player;
		this.tower = tower;
	}
}
