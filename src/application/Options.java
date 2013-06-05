package application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Vector2f;

import exceptions.RealPlayerException;

import writer.XmlWriter;

import game.Game;
import game.base.Base;
import game.player.IAPlayer;
import game.player.Player;
import game.player.RealPlayer;

public class Options {
	
	final private int adversaireNumber;
	
	
	public int getAdversaireNumber() {
		return adversaireNumber;
	}

	public Options(int adv) {
		Game game = Game.getInstance();
		game.clearGame();
		
		// How many ennemis ?
		this.adversaireNumber = adv;
		
		Player michel = new RealPlayer("Michel", Color.MAGENTA);
		Player jean_luc = new IAPlayer("Jean-Luc", Color.BLUE);
		Player patrick = new IAPlayer("Patrick", Color.GREEN);
		Player germaine = new IAPlayer("Germaine", Color.ORANGE);
		
		switch(this.getAdversaireNumber()) {
		
		case 0 :
			game.getPlayerManager().addPlayer(jean_luc);
			game.getPlayerManager().addPlayer(patrick);
			game.getPlayerManager().addPlayer(germaine);
			
			attributeBases();
			
			break;
		
		case 1 :
			game.getPlayerManager().addPlayer(michel);
			game.getPlayerManager().addPlayer(jean_luc);
			
			attributeBases();
			
			break;
		
		case 2 :
			game.getPlayerManager().addPlayer(michel);
			game.getPlayerManager().addPlayer(jean_luc);
			game.getPlayerManager().addPlayer(patrick);
			
			attributeBases();
			
			break;
		
		case 3 :
			game.getPlayerManager().addPlayer(michel);
			game.getPlayerManager().addPlayer(jean_luc);
			game.getPlayerManager().addPlayer(patrick);
			game.getPlayerManager().addPlayer(germaine);
			
			attributeBases();
			
			break;
		
		default :
			game.getPlayerManager().addPlayer(michel);
			game.getPlayerManager().addPlayer(jean_luc);
			game.getPlayerManager().addPlayer(patrick);
			game.getPlayerManager().addPlayer(germaine);
			
			attributeBases();
			
			break;
		}
		XmlWriter.createXmlFile(game, "GameCustom");
	}
	
	/**
	 * Pour la répartition des bases pour les joueurs, on crée une liste temporaire qui attribue
	 * des bases neutres ou des bases joueurs initiales (IA ou Real)
	 */
	private void attributeBases() {
		Game game = Game.getInstance();
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		
		ArrayList<Player> listTemp = new ArrayList<Player>();
		do {
			listTemp.clear();
			for(int a = 0; a <= 9; ++a) {
				try {
					listTemp.add(a, game.getPlayerManager().getPlayers().get(rand.nextInt(3 + this.getAdversaireNumber())));
				}
				catch(IndexOutOfBoundsException e) {
					listTemp.add(a, null);
				}
			}
		} while(!(listTemp.containsAll(game.getPlayerManager().getPlayers())));
		
		Base base0 = new Base(0, 2, listTemp.get(0), new Vector2f(50, 100), 10);
		Base base1 = new Base(1, 3, listTemp.get(1), new Vector2f(500, 450), 5);
		Base base2 = new Base(2, 2, listTemp.get(2), new Vector2f(30, 380), 10);
		Base base3 = new Base(3, 3, listTemp.get(3), new Vector2f(80, 280), 5);
		Base base4 = new Base(4, 2, listTemp.get(4), new Vector2f(430, 180), 10);
		Base base5 = new Base(5, 3, listTemp.get(5), new Vector2f(750, 185), 5);
		Base base6 = new Base(6, 2, listTemp.get(6), new Vector2f(600, 285), 10);
		Base base7 = new Base(7, 3, listTemp.get(7), new Vector2f(350, 385), 5);
		Base base8 = new Base(8, 2, listTemp.get(8), new Vector2f(550, 385), 10);
		
		game.getBaseManager().addBase(base0);
		game.getBaseManager().addBase(base1);
		game.getBaseManager().addBase(base2);
		game.getBaseManager().addBase(base3);
		game.getBaseManager().addBase(base4);
		game.getBaseManager().addBase(base5);
		game.getBaseManager().addBase(base6);
		game.getBaseManager().addBase(base7);
		game.getBaseManager().addBase(base8);
		
		try {
			game.getPlayerManager().getRealPlayer();
		} catch(RealPlayerException e) {
			game.getPlayerManager().addPlayer(new RealPlayer("Michel", Color.MAGENTA));
		}
		
		listTemp.clear();
	}
}
