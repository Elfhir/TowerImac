package commands.agent;

import game.Agent;
import commands.Command;

public class AttackAgent extends Command{
	
	Agent enemy;

	public AttackAgent(Agent enemy) {
		this.enemy = enemy;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
