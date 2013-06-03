package manager;

import game.Game;
import game.agent.GroupAgent;
import game.base.Base;
import game.player.Player;

import java.io.IOException;
import java.util.LinkedList;

import window.AppliWindow;

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
	
	
	public void addGroupAgent(int nbAgents, Base source, Base destination, Player player) {
		
		Game game = Game.getInstance();
	
		try {
			GroupAgent groupAgent = new GroupAgent(nbAgents, source, destination, player);
			AppliWindow.getInstance().getLabelAgent().add(groupAgent);
			game.getAgentManager().addAgent(groupAgent);
		} catch (IOException e) {
			System.out.println("failed "+e);
			e.printStackTrace();
		}
		
	}
}
