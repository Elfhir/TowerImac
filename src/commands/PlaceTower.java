package commands;

import javax.vecmath.Vector2f;

import game.Player;


public class PlaceTower extends Command {
	
	Player player;
	String type;
	Vector2f position;
	
	@Override
	public void doCommand() {
		// new Tower + add tower
		System.out.println(player.getName() + " pose une tour de type " + type + " en " + position + " !");
		
	}
	
	public PlaceTower(Player player, String type, Vector2f position) {
		super();
		this.player = player;
		this.position = position;
		this.type = type;
	}
	
}
