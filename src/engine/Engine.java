package engine;

import game.Game;
import game.agent.Agent;
import game.agent.GroupAgent;
import game.base.Base;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.SwingConstants;

import window.AppliWindow;

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
	 * Not sure if very good to do it like this, but it seems to work.
	 */
	public void restart(){
		new Thread(this).start();
    }
		
	@Override
	public void run() {
		while (Game.getInstance().isRunning()) {
			
			// We get the current time at the beginning of the main loop
			long currentTime1 = Calendar.getInstance().getTimeInMillis() - initialTime;
			
			while(!commands.isEmpty()) {
				//This is the defilage
				Command command = commands.remove();
				command.doCommand();
				
			}
			// We get again the current time at the end of the defilage
			long currentTime2 = Calendar.getInstance().getTimeInMillis() - initialTime;
			
			// We run through all the Collection of Bases
			for(Base baseCurrent : Game.getInstance().getBaseManager().getBases()) {
				// We display the data for the agents for each base.
				baseCurrent.setText(""+baseCurrent.getNbAgents()+"");
				baseCurrent.setVerticalTextPosition(SwingConstants.CENTER);
				baseCurrent.setHorizontalTextPosition(SwingConstants.CENTER);
				
				//System.out.println(currentTime+"\n");
				
				//Test for generation
				float periodOfGeneration = 10000/(baseCurrent.getMight()); // on peut modifier, c'est empirique...
				if((currentTime2 - baseCurrent.getMomentOfTheLastGeneration()) < periodOfGeneration) {
					// if the ellapsed time between the moment of the last generation and the current time is inferior
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
			
			// Moove and handle units
			for(Iterator<GroupAgent> it = Game.getInstance().getAgentManager().getAgents().iterator(); it.hasNext();) {
				GroupAgent groupAgent = it.next();
				groupAgent.moveOneStep();
			}
			
			// Le Thread se relance toutes les 30 fois par seconde.
			long waiting = 1000/30 - ((Calendar.getInstance().getTimeInMillis()-initialTime) - currentTime1);
			if(waiting < 0) {
				waiting = 0;
			}
			try {
				Thread.sleep(waiting);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
