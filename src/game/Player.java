package game;

public class Player {
	
	private Bank bank;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("bank ");
		sb.append(this.bank);
		sb.append("\n");
		return sb.toString();
	}
	//----------------------------------------------ctor----------------
	public Player(Bank bank) {
		this.bank = bank;
	}
	public static void main(String[] args) {
		Bank bank = new Bank(50);
		Player pinage = new Player(bank);
		
		System.out.println(pinage);
	}
}
