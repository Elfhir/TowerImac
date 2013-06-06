package commands.market;

import window.AppliWindow;
import commands.Command;

import game.Game;
import game.tower.Tower;


public class SellTower extends Command {
	
	Tower tower;
	
	@Override
	public void doCommand() {
		tower.getOwner().getBank().addMoney(tower.getSellPrice());
		Game.getInstance().getTowerManager().getTowers().remove(tower);
		AppliWindow.getInstance().getContent().remove(tower);
	}
	
	public SellTower(Tower tower) {
		super();
		this.tower = tower;
	}
}
