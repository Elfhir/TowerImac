package commands.player.IAPlayer;

import commands.Command;

public class Run extends Command {

	public Run() {
		
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
