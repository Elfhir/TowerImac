package manager;

import game.base.Base;

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
	
	/**
	 * Launch the intern incrementation of agents with a thread group. Each base generate, one by one, agents and the
	 * period of generation depends of the base might.
	 *
	public void launchGeneration() {
		ThreadGroup threadGroupBase = new ThreadGroup("groupe bases");
		
		for(Base b : bases) {
			// We create the Runnable which correspond for each base
			BaseThread generation1 = new BaseThread(b);
			// We create the Thread for the generation of agents with the Runnable
			Thread threadBase = new Thread(threadGroupBase, generation1, "generation d'agents");
			threadBase.start();
		}
	}
	*/

}
