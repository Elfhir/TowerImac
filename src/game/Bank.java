package game;

public class Bank {
	
	private int mMoney;
	

	public int getMoney() {
		return mMoney;
	}

	public void setMoney(int money) {
		this.mMoney = money;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("mMoney ");
		sb.append(this.mMoney);
		sb.append("\n");
		return sb.toString();
	}
	
	// ---------------------------------------ctor--------------------------
	public Bank(int money) {
		super();
		mMoney = money;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
