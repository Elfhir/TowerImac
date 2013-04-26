package manager;

import game.Base;

import java.util.ArrayList;

public class BaseManager {
	private ArrayList<Base> bases;

	public ArrayList<Base> getBases() {
		return bases;
	}
	
	public void addBase(Base b) {
		this.bases.add(b);
	}

	@Override
	public String toString() {
		return "bases=" + bases;
	}
	
	public BaseManager() {
		this.bases = new ArrayList<Base>();
	}

}
