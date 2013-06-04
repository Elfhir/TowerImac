package commands.market;

import commands.Command;

import game.player.Player;
import game.tower.Tower;


public class UpgradeTower extends Command {
	
	Tower tower;
	
	@Override
	public void doCommand() {
		// tower.upgrade();
		System.out.println(tower.getOwner().getName() + " améliore la tour : " + tower);
		
	}
	
	public UpgradeTower(Tower tower) {
		super();
		this.tower = tower;
	}
}
