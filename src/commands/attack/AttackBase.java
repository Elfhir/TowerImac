package commands.attack;

import commands.Command;

import game.Base;
import game.Game;
import game.Player;

public class AttackBase extends Command{
	
	Player player;
	Base baseOrigin;
	Base baseDestination;
	
	public AttackBase(Player player, Base baseOrigin, Base baseDestination) {
		super();
		this.player = player;
		this.baseOrigin = baseOrigin;
		this.baseDestination = baseDestination;
	}

	@Override
	public void doCommand() {
		
		baseOrigin.setBackground(this.player.getColor().brighter());
		int nbSentAgents = baseOrigin.getNbAgents() / 2;
		
		System.out.println("case : Attack !!");
		//managed by Engine (FIFO of commands) 
		
		// The number of agents in the Base attacked decrease !
		int lastSurvivor = baseDestination.getNbAgents();
		baseDestination.deleteAgents(nbSentAgents);
		
		// Enemy Base is taken !! Add the Agents not dead to the taken Base too !
		if(baseDestination.getNbAgents() == 0) {
			for(Base b : Game.getInstance().getBaseManager().getBases()) {
				if(b.equals(baseDestination)) {
					b.addAgents(nbSentAgents - lastSurvivor);
					b.setPlayer(baseOrigin.getPlayer());
					b.setBackground(baseOrigin.getPlayer().getColor());
				}
			}
		}
		
		// The number of Agents in our selected Base decrease !
		baseOrigin.deleteAgents(nbSentAgents);
		this.player.setSelectedBases(null);
		//baseCurrent.setBackground(this.IACurrent.getColor().brighter());
		
		// Now that we have moved or attacked, we deselect the base !
		this.player.setSelectedBases(null);
		
	}

}
