package game.player;

import game.Game;
import game.base.Base;

import java.awt.Color;


public class RealPlayer extends Player {
	
	private boolean isBuildingTower;
	
	public boolean isBuildingTower() {
		return isBuildingTower;
	}


	public void setBuildingTower(boolean isBuildingTower) {
		this.isBuildingTower = isBuildingTower;
	}


	@Override
	public void run() {
		while (Game.getInstance().isRunning()) {
			
			
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public RealPlayer(String name, Bank bank, Color color) {
		super(name, bank, color);
	}
	
	public RealPlayer(String name, Color color) {
		super(name, color);
	}
	
	public RealPlayer() {
		super("unknown", Color.WHITE);
	}
	
	@Override
	/**
	 * Returns the String which will be displayed on the panel of the player.
	 */
	public String getInfosPlayer() {
		
		int nbTotalBases = Game.getInstance().getBaseManager().getBases().size();
					
		StringBuilder sb = new StringBuilder(this.getName());
		sb.append(" : $");
		sb.append(this.getBank().getMoney());
		sb.append(" | ");
		sb.append(this.getNbBases());
		sb.append("/");
		sb.append(nbTotalBases);
		
		return sb.toString();
	}
	
	
	/*
	 *  
	 ********************* MAIN ********************
	 * 
	 */
	public static void main(String[] args) {
		Bank bank = new Bank(50);
		Player michel = new RealPlayer("Michel", bank, Color.MAGENTA);
		System.out.println(michel);
		
		michel.start();
	}
}
