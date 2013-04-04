package game;

public class Player {
	
	private Bank mBank;

	public Bank getBank() {
		return mBank;
	}

	public void setBank(Bank bank) {
		this.mBank = bank;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("mBank ");
		sb.append(this.mBank);
		sb.append("\n");
		return sb.toString();
	}
	//----------------------------------------------ctor----------------
	public Player(Bank bank) {
		this.mBank = bank;
	}
	public static void main(String[] args) {
		Bank bank = new Bank(50);
		Player pinage = new Player(bank);
		
		System.out.println(pinage);
	}
}
