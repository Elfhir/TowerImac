package manager;

import game.Agent;

import java.util.LinkedList;

public class AgentManager {
	private LinkedList<Agent> agents;

	public LinkedList<Agent> getAgents() {
		return agents;
	}

	@Override
	public String toString() {
		return "agents=" + agents;
	}
	
	public AgentManager() {
		this.agents = new LinkedList<Agent>();
	}

}