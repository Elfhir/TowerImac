package manager;

import game.GroupAgent;

import java.util.LinkedList;

public class AgentManager {
	private LinkedList<GroupAgent> agents;

	public LinkedList<GroupAgent> getAgents() {
		return agents;
	}
	
	public void addAgent(GroupAgent a) {
		this.agents.add(a);
	}

	@Override
	public String toString() {
		return "agents=" + agents;
	}
	
	public AgentManager() {
		this.agents = new LinkedList<GroupAgent>();
	}

}
