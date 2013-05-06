package commands.player;

import commands.Command;

import game.Player;

public class DoRandomAction extends Command {
	
	Player player;
	String action;
	
	@Override
	public void doCommand() {
		System.out.println(player.getName()+ " " + action);
		
	}
	
	public DoRandomAction(Player player, String action) {
		super();
		this.player = player;
		this.action = action;
	}
}
