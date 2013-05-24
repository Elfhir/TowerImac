package commands.selection;

import game.base.Base;
import game.player.Player;
import window.AppliWindow;

import commands.Command;

public class Move extends Command {
	
	Player player;
	Base baseOrigin;
	Base baseDestination;
	
	public Move(Player player, Base baseOrigin, Base baseDestination) {
		super();
		this.player = player;
		this.baseOrigin = baseOrigin;
		this.baseDestination = baseDestination;
	}

	@Override
	public void doCommand() {
		
		baseOrigin.setBackground(this.player.getColor().brighter());
		
		// Le nombre d'agent prêt à s'envoyer
		int nbSentAgents = baseOrigin.getNbAgents() / 2;
		
		System.out.println("3rd case : Move !!");

		
		
		if(!baseOrigin.equals(baseDestination)) {
			//The number of Agent in the base where we move increase !
			baseDestination.addAgents(nbSentAgents);
		}
		
		// The number of Agents in our selected Base decrease !
		baseOrigin.deleteAgents(nbSentAgents);
		this.player.setSelectedBases(null);
		//baseCurrent.setBackground(this.IACurrent.getColor().brighter());
		
		// Now that we have moved or attacked, we deselect !
		this.player.setSelectedBases(null);
	}

}
