package manager;

import exceptions.IAPlayerException;
import exceptions.RealPlayerException;
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
	 * Returns the only instance of RealPlayer in {@link #players}
	 * @return the unique RealPlayer
	 * @throws RealPlayerException 
	 */
	public Player getRealPlayer() throws RealPlayerException {
		Player playerFound = null;
		for (Player p:players) {
			if (p instanceof RealPlayer) {
				if(playerFound != null) {
					throw new RealPlayerException("there are several RealPlayers !");
				}
				else {
					playerFound = p;
				}
			}
		}
		if (playerFound == null) {
			throw new RealPlayerException("no RealPlayer found !");
		}
		return playerFound;
	}
	/**
	 * Provide an IAPlayer, based on its index in the players array of the PlayerManager.
	 * If it's a RealPlayer, another one is getting
	 * @param index
	 * @return IAPlayer
	 * @throws IAPlayerException
	 */
	public Player getIAPlayer(int index) throws IAPlayerException {
		Player IA = players.get(index);
		if(IA instanceof RealPlayer) {
			if((index+1) < players.size()) 
				IA = players.get(index+1);
			else
				IA = players.get(index-1);
		}
		return IA;
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
	
//	public void launchIA() {
//		for(Player p : players) {
//			p.start();
//		}
//	}
	
}
