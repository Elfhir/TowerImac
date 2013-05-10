package commands.selection;

import game.Base;
import game.IAPlayer;
import commands.Command;

public class ClickedByIAPlayer extends Command{

	IAPlayer IACurrent;
	Base baseCurrent;
	
	@Override
	public void doCommand() {
		
		Base selectedBases = this.IACurrent.getSelectedBases();
		
		// 1st case : the player doesn't have any selected base, so baseCurrent one become his selected base !
		if(selectedBases == null) {
			//select a neutral base
	
			if(baseCurrent.getPlayer() == null){
				this.IACurrent.setSelectedBases(null);
				System.out.println("1st case : it's a neutral base !");
			}
			//select one of his bases
			else if((baseCurrent.getPlayer().getName() == this.IACurrent.getName()) && (baseCurrent.getPlayer() != null)) {
				this.IACurrent.setSelectedBases(baseCurrent);
				System.out.println("1st case : Base from "+baseCurrent.getPlayer().getName()+" selected!");
			}
			//select an other player's base
			else if((baseCurrent.getPlayer().getName() != this.IACurrent.getName()) && (baseCurrent.getPlayer() != null)){
				this.IACurrent.setSelectedBases(null);
				System.out.println("1st case : it's not your base !");
			}

		}
		// 2nd case : baseCurrent base is already selected by the IAPlayer : nothing is done
		else if (selectedBases.equals(baseCurrent)) {
			System.out.println("2nd case : Base from : "+this.IACurrent.getSelectedBases().getName()+" already selected.");
		}
		
		// 3rd case : the IAPlayer has an other base selected : agents can go from the selected base to baseCurrent one ! (and we deselect the base)
		else {
			int nbSentAgents = selectedBases.getNbAgents() / 2;
			// the selected base send the agents
			
			// 3rd case : Attack
			if(baseCurrent.getPlayer() != selectedBases.getPlayer()) {
				System.out.println("3rd case : Attack !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				
				// The number of agents in the Base attacked decrease !
				baseCurrent.deleteAgents(nbSentAgents);
				
				// Enemy Base is taken !!
				if(baseCurrent.getNbAgents() == 0) {
					baseCurrent.setPlayer(selectedBases.getPlayer());
					baseCurrent.setBackground(selectedBases.getPlayer().getColor());
				}
			} 
			
			// 3rd case : Move
			else {
				
				System.out.println("3rd case : Move !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				if(!selectedBases.equals(baseCurrent)) {
					//The number of Agent in the base where we move increase !
					baseCurrent.addAgents(nbSentAgents);
				}
			}
			// The number of Agents in our selected Base decrease !
			selectedBases.deleteAgents(nbSentAgents);
			this.IACurrent.setSelectedBases(null);
			
			// Now that we have moved or attacked, we deselect !
			selectedBases = null;
		}
		
		System.out.println("J'ai cliqué sur la base numéro "+baseCurrent.getId());
		if(selectedBases == null) {
			System.out.println("Pas de base selected !");
		}else{
			System.out.println("Ma base selected est celle de : "+selectedBases.getPlayer().getName());
		}
	}
	
	public ClickedByIAPlayer(IAPlayer IA, Base baseCurrent) {
		super();
		this.IACurrent = IA;
		this.baseCurrent = baseCurrent;
	}

}
