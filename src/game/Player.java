package game;

import javax.vecmath.Vector2f;

import commands.DoRandomAction;
import commands.PlaceTower;
import commands.UpgradeTower;

import engine.Engine;

public abstract class Player implements Runnable {
	
	private Bank bank;
	private String name;
	private Base selectedBases;
	
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
	
	public Base getSelectedBases() {
		return selectedBases;
	}

	public void setSelectedBases(Base selectedBases) {
		this.selectedBases = selectedBases;
	}
	
	public void addSelectedBase(Base b) {
		this.selectedBases.add(b);
	}
	
	//----------------------------------------------actions (tests)----------------
	
	public void placeTower(String type, Vector2f position) {
		PlaceTower command = new PlaceTower(this, type, position);
		Engine.getInstance().getCommands().add(command);
	}
	
	public void upgradeTower(Tower tower) {
		UpgradeTower command = new UpgradeTower(this, tower);
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
		
		
	//----------------------------------------------ctor----------------
	public Player(String name, Bank bank) {
		this.name = name;
		this.bank = bank;
	}
	
	public Player(String name) {
		this(name, new Bank(50));
	}
	
	public Player() {
		this("unknown");
	}
	
	
	
}
