package commands.agent;

import javax.vecmath.Vector2f;

import commands.Command;

public class RunTimer extends Command{
	
	Vector2f v;
	
	public RunTimer(Vector2f v) {
		this.v = v;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");
	}

}
