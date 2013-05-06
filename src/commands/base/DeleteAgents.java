package commands.base;

import commands.Command;

public class DeleteAgents extends Command {
	
	int nbComingAgents;
	
	public DeleteAgents(int nbComingAgents) {
		this.nbComingAgents = nbComingAgents;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
