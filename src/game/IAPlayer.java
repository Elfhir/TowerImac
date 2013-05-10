package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Vector2f;

import commands.attack.DoRandomAction;
import commands.selection.ClickedByIAPlayer;
import commands.selection.ClickedByRealPlayer;
import commands.selection.PlaceTower;

import engine.Engine;
import exceptions.ClickedByIAPlayerException;
import exceptions.ClickedByRealPlayerException;


public class IAPlayer extends Player {
	
	@Override
	public void run() {
		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
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
			case 4: // 1 Clic virtuel
				//On crée la liste de toutes les bases de l'IA au moment présent
				ArrayList<Base> IAbases = new ArrayList<Base>();
				// Seulement ses bases
				for(Base b : Game.getInstance().getBaseManager().getBases()) {
					if(b.getPlayer() != null) {
						if(b.getPlayer().getName() == this.getName()) {
							IAbases.add(b);
						}
						// On retire celle déjà sélectionnée
						if(b.getPlayer().getSelectedBases() == b) {
							IAbases.remove(b);
						}
					}
				}
				// On tire un nombre entier aléatoire entre 0 et le total de ses bases 
				int nb = rand.nextInt(IAbases.size());
				Base base1 = IAbases.get(nb);
				//On ajoute la commande dans la file de commande
				ClickedByIAPlayer command1 = new ClickedByIAPlayer(this, base1);
				Engine.getInstance().getCommands().add(command1);
				break;
			default:
				break;
			}
			
			try {
				Thread.sleep(2000);
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
