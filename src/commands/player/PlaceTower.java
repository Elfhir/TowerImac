package commands.player;

import javax.vecmath.Vector2f;

import commands.Command;

public class PlaceTower extends Command {

	String type;
	Vector2f position;
		
	public PlaceTower(String type, Vector2f position) {
		this.type = type;
		this.position = position;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}
