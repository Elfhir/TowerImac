package game.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


import commands.attack.AttackBase;
import commands.selection.Move;
import commands.selection.SelectBase;

import engine.Engine;
import game.Game;
import game.base.Base;


public class IAPlayer extends Player {
	
	private int difficulty;
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	@Override
	public void run() {
		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		while (Game.getInstance().isRunning()) {
			
			int value = (int)(Math.random() * 10);
			
			switch(value) {
			/*case 0:
				placeTower("TourEiffel", new Vector2f(2, 5));
				break;*/
			case 1:
				doRandomAction("se brosse les dents !");
				break;
			case 2:
				upgradeTower(null);
				break;
			case 3: // -------------------------------------------------------------------------------------------
				// Si je n'ai pas de base selected :
				if(this.getSelectedBases() == null) {
					//On crée la liste de toutes les bases de l'IA au moment présent
					ArrayList<Base> IAbases = new ArrayList<Base>();
					// Seulement ses bases
					for(Base b : Game.getInstance().getBaseManager().getBases()) {
						if(b.getPlayer() != null) {
							if(b.getPlayer().getName() == this.getName()) {
								IAbases.add(b);
							}
						}
					}
					// On tire un nombre entier aléatoire entre 0 et le total de ses bases 
					int nb = rand.nextInt(IAbases.size());
					Base base1 = IAbases.get(nb);
					//On ajoute la commande dans la file de commande
					SelectBase command1 = new SelectBase(this, base1);
					Engine.getInstance().getCommands().add(command1);
				}
				// Si j'ai déjà une base selected :
				else {
					doRandomAction("a déjà sa base prête pour déplacer ses troupes, il se tourne les pouces !");
				}
				break; // ------------------------------------------------------------------------------------------
			
			case 4: // ---------------------------------------------------------------------------------------------
				// Je veux faire un déplacement
				
				//On crée la liste de toutes les bases de l'IA au moment présent
				ArrayList<Base> IAbases = new ArrayList<Base>();
				// Seulement ses bases
				for(Base b : Game.getInstance().getBaseManager().getBases()) {
					if(b.getPlayer() != null) {
						if(b.getPlayer().getName() == this.getName()) {
							IAbases.add(b);
						}
						// On retire celle déjà sélectionnée (même s'il y a un test plus tard)
						if(b.getPlayer().getSelectedBases() == b) {
							IAbases.remove(b);
						}
					}
				}
				// On tire un nombre entier aléatoire entre 0 et le total de ses bases 
				int nb = rand.nextInt(IAbases.size());
				Base base1 = IAbases.get(nb);
				
				// Si j'ai déjà un base sélectionnée, je fais mon déplacement
				if(this.getSelectedBases() != null) {
					//On ajoute la commande Move dans la file de commande
					Move command1 = new Move(this, this.getSelectedBases(), base1);
					Engine.getInstance().getCommands().add(command1);
				}
				// Si je n'ai pas de base sélectionnée, je selectionne ma base
				else {
					//On ajoute la commande SelectHisBase dans la file de commande
					SelectBase command1 = new SelectBase(this, base1);
					Engine.getInstance().getCommands().add(command1);
				}
				break; // ------------------------------------------------------------------------------------------
			
			case 5: // ---------------------------------------------------------------------------------------------
				// Je veux faire une attaque
				// Si j'ai déjà une base selectionnée, je choisi une base ennemi et j'attaque
				if(this.getSelectedBases() != null) {
					//On crée la liste de toutes les bases ennemi à l'IA au moment présent
					ArrayList<Base> ennemiBases = new ArrayList<Base>();
					// Seulement les bases ennemi mais aussi les bases neutres
					for(Base b : Game.getInstance().getBaseManager().getBases()) {
						if(b.getPlayer() == null) {
							ennemiBases.add(b);
						}
						if(b.getPlayer() != null) {
							if(b.getPlayer().getName() != this.getName()) {
								ennemiBases.add(b);
							}
						}
					}
					// On tire un nombre entier aléatoire entre 0 et le total des bases ennemis 
					nb = rand.nextInt(ennemiBases.size());
					base1 = ennemiBases.get(nb);
					//On ajoute la commande Attack dans la file de commande
					AttackBase command1 = new AttackBase(this, this.getSelectedBases(), base1);
					if(base1.getPlayer() == null) {
						System.out.println(this.getName()+" envoie ses troupes sur une base neutre");
					} else {
						System.out.println(this.getName()+" envoie ses troupes sur une base de "+base1.getPlayer().getName());
					}
					Engine.getInstance().getCommands().add(command1);
				}
				// Si je n'ai pas de base selectionnée, je selectionne ma base
				else {
					//On crée la liste de toutes les bases de l'IA au moment présent
					IAbases = new ArrayList<Base>();
					// Seulement ses bases
					for(Base b : Game.getInstance().getBaseManager().getBases()) {
						if(b.getPlayer() != null) {
							if(b.getPlayer().getName() == this.getName()) {
								IAbases.add(b);
							}
						}
					}
					// On tire un nombre entier aléatoire entre 0 et le total de ses bases 
					nb = rand.nextInt(IAbases.size());
					base1 = IAbases.get(nb);
					//On ajoute la commande dans la file de commande
					SelectBase command1 = new SelectBase(this, base1);
					Engine.getInstance().getCommands().add(command1);
				}
				break; // ------------------------------------------------------------------------------------------
				
			// Old case with ClickedByIAPlayer
			/*case 4: // 1 Clic virtuel
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
				break;*/
				
				
			default:
				break;
			}
			// We create 2 variables, elle corresponde aux bornes du temps de décisions des IA
			int maxTimeDecision = 1000;
			int minTimeDecision = 1000;
			if(this.getDifficulty() == 1) {
				maxTimeDecision = 4000;
				minTimeDecision = 1700;
			}
			else if(this.getDifficulty() == 2) {
				maxTimeDecision = 3000;
				minTimeDecision = 1500;
			}
			
			int waitingForNewDecision = (int) (rand.nextFloat()*(maxTimeDecision - minTimeDecision) + minTimeDecision);
			
			try {
				Thread.sleep(waitingForNewDecision);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	/**
	 * Returns the String which will be displayed on the panel of the player.
	 */
	public String getInfosPlayer() {
		
		int nbTotalBases = Game.getInstance().getBaseManager().getBases().size();
				
		StringBuilder sb = new StringBuilder("<html>");
		sb.append(this.getName());
		sb.append("<br /> $");
		sb.append(this.getBank().getMoney());
		sb.append(" | ");
		sb.append(this.getNbBases());
		sb.append("/");
		sb.append(nbTotalBases);
		sb.append("</html>");
		
		return sb.toString();
	}
	
	public IAPlayer(String name, Bank bank, Color color) {
		super(name, bank, color);
		this.difficulty = 1;
	}
	
	public IAPlayer(String name, Color color) {
		super(name, color);
		this.difficulty = 1;
	}
	
	public IAPlayer() {
		super("unknown", Color.WHITE);
		this.difficulty = 1;
	}
	
	
}
