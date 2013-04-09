package manager;

import game.Tower;

import java.util.LinkedList;

public class TowerManager {
	private LinkedList<Tower> towers;
	
	public LinkedList<Tower> getTowers() {
		return towers;
	}
	
	@Override
	public String toString() {
		return "towers=" + towers;
	}
	
	public TowerManager() {
		this.towers = new LinkedList<Tower>();
	}
}
