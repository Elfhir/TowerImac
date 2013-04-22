package game;


public class RealPlayer extends Player {
	
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
	
	
	public RealPlayer(String name, Bank bank) {
		super(name, bank);
	}
	
	public RealPlayer(String name) {
		super(name);
	}
	
	public RealPlayer() {
		super("unknown");
	}
	
	
	/*
	 *  
	 ********************* MAIN ********************
	 * 
	 */
	public static void main(String[] args) {
		Bank bank = new Bank(50);
		Player michel = new RealPlayer("Michel", bank);
		System.out.println(michel);
		
		michel.start();
	}
}
