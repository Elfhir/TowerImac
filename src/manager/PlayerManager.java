package manager;

import game.Player;

import java.util.ArrayList;

public class PlayerManager {
	private ArrayList<Player> players;

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(String playerName) {
		for(Player p:getPlayers()) {
			if (p.getName().equals(playerName)) {
				return p;
			}
		}
		return null;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}
	
	@Override
	public String toString() {
		return "players=" + players;
	}
	
	public PlayerManager() {
		this.players = new ArrayList<Player>();
	}
}
