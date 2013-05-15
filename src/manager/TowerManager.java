package manager;

import game.player.Player;
import game.tower.Tower;

import java.util.LinkedList;

public class TowerManager {
	private LinkedList<Tower> towers;
	
	public LinkedList<Tower> getTowers() {
		return towers;
	}
	
	public void addTower(Tower t) {
		this.towers.add(t);
	}
	
	@Override
	public String toString() {
		return "towers=" + towers;
	}
	
	public TowerManager() {
		this.towers = new LinkedList<Tower>();
	}
}
