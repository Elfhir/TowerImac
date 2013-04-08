package game;

public class Bank {
	
	private int money;
	

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("money ");
		sb.append(this.money);
		sb.append("\n");
		return sb.toString();
	}
	
	// ---------------------------------------ctor--------------------------
	public Bank(int money) {
		super();
		this.money = money;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
