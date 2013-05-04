package manager;

import game.Player;
import game.RealPlayer;

import java.util.ArrayList;

public class PlayerManager {
	private ArrayList<Player> players;

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Returns the instance of a player from his name.
	 * @param playerName	the name of the wanted player
	 * @return	the wanted player
	 */
	public Player getPlayer(String playerName) {
		for(Player p:getPlayers()) {
			if (p.getName().equals(playerName)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Return the only instance of RealPlayer in {@link #players}
	 * @return the only RealPlayer
	 */
	public Player getRealPlayer() {
		Player playerFound = null;
		for (Player p:players) {
			if (p instanceof RealPlayer) {
				if(playerFound != null) {
					// Use Exception
					System.out.println("Error : there are several RealPlayers !!");
				}
				else {
					playerFound = p;
				}
			}
		}
		if (playerFound == null) {
			// Use Exception
			System.out.println("Error : no RealPlayer found !");
		}
		return playerFound;
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
	
	public void launchIA() {
		for(Player p : players) {
			p.start();
		}
	}
	
}
