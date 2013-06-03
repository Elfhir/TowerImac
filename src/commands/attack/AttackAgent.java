package commands.attack;

import game.Game;
import game.agent.GroupAgent;

import commands.Command;

public class AttackAgent extends Command{
	
	private GroupAgent agent;
	private int might;
	
	public AttackAgent(GroupAgent agent, int might) {
		super();
		this.agent = agent;
		this.might = might;
	}

	@Override
	public void doCommand() {
		
		int survivors = agent.getNbAgent() - might;
		if (survivors>0) {
			agent.setNbAgent(survivors);
			
		}
		else {
			Game.getInstance().getAgentManager().getAgents().remove(agent);
			this.agent.setVisible(false);
		}
	}

}
