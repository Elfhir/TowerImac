package commands.market;

import game.tower.GelTower;
import game.tower.GunTower;
import game.tower.Tower;

import commands.Command;


public class UpgradeTower extends Command {
	
	Tower tower;
	
	@Override
	public void doCommand() {
		
		// if the player can afford the update of the tower we do nothing
		if (tower.getOwner().getBank().getMoney() < tower.getUpgradePrice()) {
			System.out.println("The player is too poor to update the tower !");
			return;
		}
		
		if (tower instanceof GunTower) {
			System.out.println("version: "+  ((GunTower) tower).getVersion());
			
			GunTower.Version version = ((GunTower) tower).getVersion();
			switch (version) {
			case NORMAL:
				System.out.println("on passe à SUPER");
				((GunTower) tower).setVersion(GunTower.Version.SUPER);
				tower.getOwner().getBank().addMoney(-tower.getUpgradePrice());
				break;
			case SUPER:
				System.out.println("on passe à CHUCKNORRIS");
				((GunTower) tower).setVersion(GunTower.Version.CHUCKNORRIS);
				tower.getOwner().getBank().addMoney(-tower.getUpgradePrice());
				break;
			default:
				System.out.println("pas d'évolution");
				break;
			}
		}
		else if (tower instanceof GelTower)  {
			// ...
		}
		
	}
	
	public UpgradeTower(Tower tower) {
		super();
		this.tower = tower;
	}
}
