package commands;

import game.Player;
import game.Tower;


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
