package commands.attack;

import commands.Command;

import game.Base;
import game.Game;
import game.IAPlayer;

public class AttackBase extends Command{
	
	IAPlayer IACurrent;
	Base baseCurrent;
	
	public AttackBase(IAPlayer IA, Base baseCurrent) {
		super();
		this.IACurrent = IA;
		this.baseCurrent = baseCurrent;
	}

	@Override
	public void doCommand() {
		Base selectedBases = this.IACurrent.getSelectedBases();
		
		selectedBases.setBackground(this.IACurrent.getColor().brighter());
		int nbSentAgents = selectedBases.getNbAgents() / 2;
		
		System.out.println("case : Attack !!");
		//managed by Engine (FIFO of commands) 
		
		// The number of agents in the Base attacked decrease !
		int lastSurvivor = baseCurrent.getNbAgents();
		baseCurrent.deleteAgents(nbSentAgents);
		
		// Enemy Base is taken !! Add the Agents not dead to the taken Base too !
		if(baseCurrent.getNbAgents() == 0) {
			for(Base b : Game.getInstance().getBaseManager().getBases()) {
				if(b.equals(baseCurrent)) {
					b.addAgents(nbSentAgents - lastSurvivor);
					b.setPlayer(selectedBases.getPlayer());
					b.setBackground(selectedBases.getPlayer().getColor());
				}
			}
		}
		
		// The number of Agents in our selected Base decrease !
		selectedBases.deleteAgents(nbSentAgents);
		this.IACurrent.setSelectedBases(null);
		//baseCurrent.setBackground(this.IACurrent.getColor().brighter());
		
		// Now that we have moved or attacked, we deselect !
		selectedBases = null;
		
	}

}
