package commands.selection;

import game.Game;
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

		// The number of Agents in our selected Base decrease !
		baseOrigin.deleteAgents(nbSentAgents);
		this.player.setSelectedBases(null);
		
		Game.getInstance().getAgentManager().addGroupAgent(nbSentAgents, baseOrigin, baseDestination, player);
		
	}

}
