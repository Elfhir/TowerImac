package game.player;

import java.awt.Color;

import commands.market.BuyTower;
import commands.market.SellTower;
import commands.market.UpgradeTower;
import commands.attack.DoRandomAction;

import engine.Engine;
import game.Game;
import game.base.Base;
import game.tower.Tower;

@SuppressWarnings("unused")
public abstract class Player implements Runnable {
	
	private Bank bank;
	private String name;
	private Base selectedBases;
	private Color teamColor;
	private boolean isDead;
	
	//----------------------------------------------accessors----------------
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getColorString() {
		return Integer.toString(this.teamColor.getRGB());
	}
	
	public Color getColor() {
		return this.teamColor;
	}
	
	public Base getSelectedBases() {
		return selectedBases;
	}
	
	/**
	 * Checks if the player still has bases and returns if he is dead or not
	 * @return if the player had lost all his bases
	 */
	public boolean getIsDead() {
		
		if (this.getNbBases() == 0) {
			this.isDead = true;
			return true;
		}
		return false;
	
	}
	
	public void setIsDead(boolean Dead) {
		this.isDead = Dead;
	}


	public void setSelectedBases(Base selectedBases) {
		this.selectedBases = selectedBases;
	}
	
	public void addSelectedBase(Base b) {
		this.selectedBases.add(b);
	}
	
	/**
	 * @return the number of bases owned by the player
	 */
	public int getNbBases() {
		// how many bases does the player have ?
		int nbBasesPlayer = 0;
		for(Base b: Game.getInstance().getBaseManager().getBases()) {
			if (b.getPlayer()!=null && b.getPlayer().equals(this)) {
				++nbBasesPlayer;
			}
		}
		return nbBasesPlayer;
	}
	
	//----------------------------------------------actions (tests)----------------
	
	
	/**
	 *  Buy the given tower (by adding the command in the Engine)
	 * 
	 */
	public void buyTower(Player player, String type, int x, int y) {
		BuyTower buyTower = new BuyTower(player, type, x, y);
		Engine.getInstance().getCommands().add(buyTower);
	}
	
	/**
	 *  Sell the given tower (by adding the command in the Engine)
	 */
	public void sellTower(Tower tower) {
		SellTower command = new SellTower(tower);
		Engine.getInstance().getCommands().add(command);
	}
	
	/**
	 * 	The tower improves its skills ! (by adding the command in the Engine)
	 */
	public void upgradeTower(Tower tower) {
		UpgradeTower command = new UpgradeTower(tower);
		Engine.getInstance().getCommands().add(command);
	}
	
	public void doRandomAction(String text) {
		DoRandomAction command = new DoRandomAction(this, text);
		Engine.getInstance().getCommands().add(command);
	}
	
	
	//----------------------------------------------usefull----------------
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player : \n");
		sb.append("\t name: ");
		sb.append(this.name);
		sb.append("\n");
		sb.append("\t bank: ");
		sb.append(this.bank);
		sb.append("\n");
		return sb.toString();
	}
	
	// Starts the thread of the player
	public void start(){
		new Thread(this).start();
    }
	
	public abstract String getInfosPlayer();
		
		
	//----------------------------------------------constructor----------------
	public Player(String name, Bank bank, Color color) {
		this.name = name;
		this.bank = bank;
		this.teamColor = color;
		this.isDead = false;
	}
	
	public Player(String name, Color color) {
		this(name, new Bank(50), color);
	}
	
	public Player() {
		this("unknown", Color.WHITE);
	}
	
	
	
}
