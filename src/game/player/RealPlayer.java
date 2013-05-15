package game.player;

import game.Game;

import java.awt.Color;


public class RealPlayer extends Player {
	
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
