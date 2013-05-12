package commands.selection;

import javax.swing.text.AbstractDocument.Content;

import window.AppliWindow;
import game.Base;
import game.Game;
import game.Player;
import commands.Command;
import exceptions.ClickedByRealPlayerException;
import exceptions.RealPlayerException;

/**
 * 
 * @author Elfhir, au.gre, GuillaumeSeg
 * @deprecated
 */
public class ClickedByRealPlayer extends Command {

	Base current;
	
	@Override
	public void doCommand() {
		Player realPlayer = null;
		try {
			realPlayer = Game.getInstance().getPlayerManager().getRealPlayer();
		} catch (RealPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Base selectedBases = realPlayer.getSelectedBases();
		
		// 1st case : the player doesn't have any selected base, so current one become his selected base !
		if(selectedBases == null) {
			//select a neutral base
	
			if(current.getPlayer() == null){
				realPlayer.setSelectedBases(null);
				System.out.println("1st case : it's a neutral base !");
			}
			//select one of his bases
			else if((current.getPlayer().getName() == realPlayer.getName()) && (current.getPlayer() != null)) {
				realPlayer.setSelectedBases(current);
				current.setBackground(realPlayer.getColor().darker());
				System.out.println("1st case : Base from "+current.getPlayer().getName()+" selected!");
			}
			//select an other player's base
			else if((current.getPlayer().getName() != realPlayer.getName()) && (current.getPlayer() != null)){
				realPlayer.setSelectedBases(null);
				System.out.println("1st case : it's not your base !");
			}

		}
		// 2nd case : current base is already selected by the realPlayer : nothing is done
		else if (selectedBases.equals(current)) {
			System.out.println("2nd case : Base from : "+realPlayer.getSelectedBases().getName()+" already selected.");
		}
		
		// 3rd case : the realPlayer has an other base selected : agents can go from the selected base to current one ! (and we deselect the base)
		else {
			selectedBases.setBackground(realPlayer.getColor().brighter());
			int nbSentAgents = selectedBases.getNbAgents() / 2;
			// the selected base send the agents
			
			// 3rd case : Attack
			if(current.getPlayer() != selectedBases.getPlayer()) {
				System.out.println("3rd case : Attack !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				
				// The number of agents in the Base attacked decrease !
				int lastSurvivor = current.getNbAgents();
				current.deleteAgents(nbSentAgents);
				
				// Enemy Base is taken !! Add the Agents not dead to the taken Base too !
				if(current.getNbAgents() == 0) {
					for(Base b : Game.getInstance().getBaseManager().getBases()) {
						if(b.equals(current)) {
							b.addAgents(nbSentAgents - lastSurvivor);
							b.setPlayer(selectedBases.getPlayer());
							b.setBackground(selectedBases.getPlayer().getColor());
						}
					}
				}
			} 
			
			// 3rd case : Move
			else {
				
				System.out.println("3rd case : Move !!");
				/*
				 * Will be managed by Engine (FIFO of commands) 
				 */
				if(!selectedBases.equals(current)) {
					//The number of Agent in the base where we move increase !
					current.addAgents(nbSentAgents);
				}
			}
			// The number of Agents in our selected Base decrease !
			selectedBases.deleteAgents(nbSentAgents);
			realPlayer.setSelectedBases(null);
			current.setBackground(realPlayer.getColor().brighter());
			
			// Now that we have moved or attacked, we deselect !
			selectedBases = null;
		}
		
		System.out.println("J'ai cliqué sur la base numéro "+current.getId());
		if(selectedBases == null) {
			System.out.println("Pas de base selected !");
		}else{
			System.out.println("Ma base selected est celle de : "+selectedBases.getPlayer().getName());
		}
		
		// Since a Base has the focus, we give back to the content the focus
		System.out.println("Le Panel content reprend le focus");
		AppliWindow.getInstance().getContent().requestFocusInWindow();
		
	}

	
	
	public ClickedByRealPlayer(Base current) throws ClickedByRealPlayerException{
		super();
		this.current = current;
	}

}
