package window.graphic;

import game.agent.GroupAgent;

import java.util.ArrayList;

import javax.swing.JLabel;

public class LabelAgents extends JLabel {

	private static final long serialVersionUID = 5634611408324183184L;
	private ArrayList<GroupAgent> groupAgents;
	
	public ArrayList<GroupAgent> getGroupAgents() {
		return groupAgents;
	}
	public void setGroupAgents(ArrayList<GroupAgent> groupAgents) {
		this.groupAgents = groupAgents;
	}
	
	public LabelAgents() {
		super();
	}
}
