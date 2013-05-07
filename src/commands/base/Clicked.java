package commands.base;

import commands.Command;

public class Clicked extends Command{

	public Clicked() {
		
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");
	}

}
