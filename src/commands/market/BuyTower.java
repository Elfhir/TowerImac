package commands.market;

import commands.Command;

import game.Player;
import game.Tower;


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
