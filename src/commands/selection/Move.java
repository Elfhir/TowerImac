package commands.selection;

import game.Base;
import game.IAPlayer;
import commands.Command;

public class Move extends Command {
	
	IAPlayer IACurrent;
	Base baseCurrent;
	
	public Move(IAPlayer IA, Base baseCurrent) {
		super();
		this.IACurrent = IA;
		this.baseCurrent = baseCurrent;
	}

	@Override
	public void doCommand() {
		Base selectedBases = this.IACurrent.getSelectedBases();
		
		selectedBases.setBackground(this.IACurrent.getColor().brighter());
		// Le nombre d'agent prêt à s'envoyer
		int nbSentAgents = selectedBases.getNbAgents() / 2;
		
		System.out.println("3rd case : Move !!");
		//managed by Engine (FIFO of commands) 
		if(!selectedBases.equals(baseCurrent)) {
			//The number of Agent in the base where we move increase !
			baseCurrent.addAgents(nbSentAgents);
		}
		
		// The number of Agents in our selected Base decrease !
		selectedBases.deleteAgents(nbSentAgents);
		this.IACurrent.setSelectedBases(null);
		//baseCurrent.setBackground(this.IACurrent.getColor().brighter());
		
		// Now that we have moved or attacked, we deselect !
		selectedBases = null;
	}

}
