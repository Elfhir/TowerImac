package commands.selection;

import game.Base;
import game.Player;
import commands.Command;

public class SelectBase extends Command {

	Player player;
	Base baseSelected;
	
	public SelectBase(Player player, Base baseSelected) {
		super();
		this.player = player;
		this.baseSelected = baseSelected;
	}

	@Override
	public void doCommand() {
		
		this.player.setSelectedBases(baseSelected);
		baseSelected.setBackground(this.player.getColor().darker());
		System.out.println("1st case : Base from "+baseSelected.getPlayer().getName()+" selected!");
	}

}
