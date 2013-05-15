package game.agent;

//import javax.swing.JLabel;

public class GroupAgent extends Agent{
	private int NbAgents;
	
	public GroupAgent(){
		
	}
	
	public int getNb(){
		return NbAgents;
	}
	
	public int GroupSize(){
		int Size;
		Size = NbAgents/2;
		return Size;		
	}
	
	
	
}
