package commands.attack;

import window.AppliWindow;
import commands.Command;

import game.Game;
import game.base.Base;
import game.player.Player;

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
		
		AppliWindow.addGroupAgent(baseOrigin, baseDestination);
		int nbAgentToSend = baseOrigin.getNbAgents()/2;
		
		System.out.println("case : Attack !!");
		//managed by Engine (FIFO of commands) 
		
		// The number of agents in the Base attacked decrease !
		int lastSurvivor = baseDestination.getNbAgents();
		baseDestination.deleteAgents(nbAgentToSend);
		
		// Enemy Base is taken !! Add the Agents not dead to the taken Base too !
		if(baseDestination.getNbAgents() == 0) {
			for(Base b : Game.getInstance().getBaseManager().getBases()) {
				if(b.equals(baseDestination)) {
					b.changePlayer(baseOrigin.getPlayer(), nbAgentToSend - lastSurvivor);
				}
			}
		}
		
		// The number of Agents in our selected Base decrease !
		baseOrigin.deleteAgents(nbAgentToSend);
		this.player.setSelectedBases(null);
		//baseCurrent.setBackground(this.IACurrent.getColor().brighter());
		
		// Now that we have moved or attacked, we deselect the base !
		this.player.setSelectedBases(null);
		
	}

}
