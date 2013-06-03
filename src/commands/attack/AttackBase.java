package commands.attack;

import window.AppliWindow;
import game.Game;
import game.agent.GroupAgent;
import game.base.Base;

import commands.Command;

public class AttackBase extends Command{
	
	Base base;
	GroupAgent groupAgent;
	
	public AttackBase(Base base, GroupAgent groupAgent) {
		super();
		this.base = base;
		this.groupAgent = groupAgent;
	}

	@Override
	public void doCommand() {
		
		// if it's the same player : it's just a move between 2 bases of the same player
		if (groupAgent.getPlayer().equals(base.getPlayer())) {
			base.addAgents(groupAgent.getNbAgent());
		}
		
		// else if the players are different : it's an attack !
		else {
			// The number of agents in the Base attacked decrease !
			int lastSurvivor = base.getNbAgents();
			base.deleteAgents(groupAgent.getNbAgent());
			
			// Enemy Base is taken !! Add the Agents not dead to the taken Base too !
			if(base.getNbAgents() == 0) {
					base.changePlayer(groupAgent.getPlayer(), groupAgent.getNbAgent() - lastSurvivor);
			}
		}	
		
		Game.getInstance().getAgentManager().getAgents().remove(groupAgent);
		this.groupAgent.setVisible(false);
	
		// Display the Line because it sets the second point of it as the Attacked Base
		AppliWindow.getInstance().getLine().displayLastPointDeplacement(base);

	}
}
