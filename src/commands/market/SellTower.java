package commands.market;

import window.AppliWindow;
import commands.Command;

import game.Game;
import game.tower.Tower;


public class SellTower extends Command {
	
	Tower tower;
	
	@Override
	public void doCommand() {
		// we add the money to the player
		tower.getOwner().getBank().addMoney(tower.getSellPrice());
		// and we remove the tower
		Game.getInstance().getTowerManager().getTowers().remove(tower);
		AppliWindow.getInstance().getContent().remove(tower);
	}
	
	public SellTower(Tower tower) {
		super();
		this.tower = tower;
	}
}
