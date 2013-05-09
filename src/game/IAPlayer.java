package game;

import java.awt.Color;

import javax.vecmath.Vector2f;

import commands.attack.DoRandomAction;
import commands.selection.PlaceTower;

import engine.Engine;


public class IAPlayer extends Player {
	
	@Override
	public void run() {
		while (Game.getInstance().isRunning()) {
			
			int value = (int)(Math.random() * 10);
			
			switch(value) {
			case 0:
				doRandomAction("attaque son voisin !");
				break;
			case 1:
				placeTower("TourEiffel", new Vector2f(2, 5));
				break;
			case 2:
				doRandomAction("se brosse les dents !");
				break;
			case 3:
				upgradeTower(null);
				break;
			default:
				break;
			}
			
			try {
				Thread.sleep(1500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public IAPlayer(String name, Bank bank, Color color) {
		super(name, bank, color);
	}
	
	public IAPlayer(String name, Color color) {
		super(name, color);
	}
	
	public IAPlayer() {
		super("unknown", Color.WHITE);
	}
	
	
}
