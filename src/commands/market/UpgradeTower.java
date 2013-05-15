package commands.market;

import commands.Command;

import game.player.Player;
import game.tower.Tower;


public class UpgradeTower extends Command {
	
	Player player;
	Tower tower;
	
	@Override
	public void doCommand() {
		// tower.upgrade();
		System.out.println(player.getName() + " améliore la tour : " + tower);
		
	}
	
	public UpgradeTower(Player player, Tower tower) {
		super();
		this.player = player;
		this.tower = tower;
	}
}
