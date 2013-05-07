package commands.selection;

import javax.vecmath.Vector2f;

import commands.Command;

public class Move extends Command {

	Vector2f to;
	String how;
	
	public Move(Vector2f to, String how) {
		this.to = to;
		this.how = how;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");
	}

}
