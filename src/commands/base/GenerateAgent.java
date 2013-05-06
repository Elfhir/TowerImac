package commands.base;

import commands.Command;

public class GenerateAgent extends Command{

	public GenerateAgent() {
	
	}
	
	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
