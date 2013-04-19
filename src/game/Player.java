package game;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Player implements Runnable {
	
	private Bank bank;
	private String name;
	private LinkedHashSet<Base> selectedBases;

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
	
	public LinkedHashSet<Base> getSelectedBases() {
		return selectedBases;
	}

	public void setSelectedBases(LinkedHashSet<Base> selectedBases) {
		this.selectedBases = selectedBases;
	}
	
	public void addSelectedBase(Base b) {
		this.selectedBases.add(b);
	}
	

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
	
	/*
	 *  
	 ********************* MAIN ********************
	 * 
	 */
	public static void main(String[] args) {
		Bank bank = new Bank(50);
		Player michel = new Player("Michel", bank);
		System.out.println(michel);
		
		michel.start();
	}
	
}
