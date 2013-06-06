package manager;

import game.tower.Tower;

import java.util.ArrayList;
import java.util.LinkedList;

public class TowerManager {
	
	private ArrayList<String> availableTowerTypes;
	private LinkedList<Tower> towers;
	
	public ArrayList<String> getAvailableTowerTypes() {
		return availableTowerTypes;
	}
	
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
		this.availableTowerTypes = new ArrayList<String>();
		this.availableTowerTypes.add("game.tower.GunTower");
	}
		
}
