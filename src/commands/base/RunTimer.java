package commands.base;

import commands.Command;

public class RunTimer extends Command {

	boolean b;
		
	public RunTimer(boolean b) {
		this.b = b;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
