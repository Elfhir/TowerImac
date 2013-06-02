package engine;

import exceptions.RealPlayerException;
import game.Game;
import game.agent.GroupAgent;
import game.base.Base;
import game.player.Player;
import game.tower.Tower;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.SwingConstants;

import application.Launcher;

import commands.Command;

/**
 * Singleton
 */
public class Engine implements Runnable {

	private ConcurrentLinkedQueue<Command> commands;
	private long initialTime;
	
	public ConcurrentLinkedQueue<Command> getCommands() {
		return commands;
	}
	
	public void start(){
		new Thread(this).start();
    }
	
	/**
	 * Start a new Thread, like start() method in Engine.
	 */
	public void restart(){
		new Thread(this).start();
    }
		
	@Override
	public void run() {
		Game game = Game.getInstance();
		
		while (game.isRunning()) {
			
			// We get the current time at the beginning of the main loop
			long currentTime1 = Calendar.getInstance().getTimeInMillis() - initialTime;
			
			while(!commands.isEmpty()) {
				//This is the "defilage"
				Command command = commands.remove();
				command.doCommand();
			}
			
			// The towers attack !!
			for(Tower t: game.getTowerManager().getTowers()) {
				// we loop on each agent in the game
				for(GroupAgent a: game.getAgentManager().getAgents()) {
					// if the agent is attacking a base of the owner of the base
					if (t.getOwner().equals(a.getBaseDestination().getPlayer()) && !t.getOwner().equals(a.getBaseOrigin().getPlayer())) {
						System.out.println("Tower Attack !!!!");
					}
					else {
						System.out.println("Pas d'attaque tower");
					}
				}
			}
			// We get again the current time at the end of the defilage
			long currentTime2 = Calendar.getInstance().getTimeInMillis() - initialTime;
			
			// We run through all the Collection of Bases
			for(Base baseCurrent : game.getBaseManager().getBases()) {
				// We display the data for the agents for each base.
				baseCurrent.setText(""+baseCurrent.getNbAgents()+"");
				baseCurrent.setVerticalTextPosition(SwingConstants.CENTER);
				baseCurrent.setHorizontalTextPosition(SwingConstants.CENTER);
				
				
				//Test for generation
				float periodOfGeneration = 10000/(baseCurrent.getMight()); // on peut modifier, c'est empirique...
				if((currentTime2 - baseCurrent.getMomentOfTheLastGeneration()) < periodOfGeneration) {
					// if the elapsed time between the moment of the last generation and the current time is inferior
					// to the period of generation, we do nothing for the bases
				}
				else {
					// else we can generate agents
					if(baseCurrent.hasPlayer()) {
						// Neutral bases don't generate agents until they are taken
						baseCurrent.generateAgent();
						// We set the new moment of the last generation which is the current time now
						baseCurrent.setMomentOfTheLastGeneration(Calendar.getInstance().getTimeInMillis() - initialTime);
					} else {
						
					}
				}
			}
			// The Real Player die if he has 0 base.
			if(!Launcher.modeSpectateur) {
				try {
					if(Game.getInstance().getPlayerManager().getRealPlayer().getIsDead()) {
						System.out.println("La partie est terminée, vous êtes éliminé !");
						// Les autres joueurs ne continuent pas la partie tout seul.
						for(Player p : Game.getInstance().getPlayerManager().getPlayers()) {
							p.setIsDead(true);
						}
						Game.getInstance().setRunning(false);
					}
				} catch (RealPlayerException e1) {
					e1.printStackTrace();
				}
			}
			
			// Move and handle units
			for(Iterator<GroupAgent> it = game.getAgentManager().getAgents().iterator(); it.hasNext();) {
				GroupAgent groupAgent = it.next();
				groupAgent.moveOneStep();
			}
			
			// Players get progressively more money
			game.economicalEvolution(0.05F);
			
			// The game is updated with 30 fps
			long waiting = 1000/30 - ((Calendar.getInstance().getTimeInMillis()-initialTime) - currentTime1);
			if(waiting > 0) {
				try {
					Thread.sleep(waiting);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private Engine() {
		super();
		this.commands = new ConcurrentLinkedQueue<Command>();
		// We get the initial time in order to have relative time with the Class Calendar, which uses lots of time method.
		initialTime = Calendar.getInstance().getTimeInMillis();
	}
	
	/* 
	 * 
	 ************    Holder class : for the Singleton pattern implementation   ******************
	 */
	
	private static class EngineHolder
	{		
		// unique instance, not preinitialized
		private final static Engine instance = new Engine();
	}
 
	// Getter for the unique instance of the Singleton
	public static Engine getInstance()
	{
		return EngineHolder.instance;
	}
}
