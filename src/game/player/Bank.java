package game.player;

public class Bank {
	
	private float money;
	

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	// --------------------------------------- toString ----------------------------
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("money ");
		sb.append(this.money);
		sb.append("\n");
		return sb.toString();
	}
	
	// ---------------------------------------constructor--------------------------
	public Bank(float money) {
		super();
		this.money = money;
	}

}
