package commands.selection;

import game.Base;
import game.IAPlayer;
import commands.Command;

public class SelectHisBase extends Command {

	IAPlayer IACurrent;
	Base baseCurrent;
	
	public SelectHisBase(IAPlayer IA, Base baseCurrent) {
		super();
		this.IACurrent = IA;
		this.baseCurrent = baseCurrent;
	}

	@Override
	public void doCommand() {
		
		this.IACurrent.setSelectedBases(baseCurrent);
		baseCurrent.setBackground(this.IACurrent.getColor().darker());
		System.out.println("1st case : Base from "+baseCurrent.getPlayer().getName()+" selected!");
	}

}
