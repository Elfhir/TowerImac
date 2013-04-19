package game;


public class IAPlayer extends Player {
	
	@Override
	public void run() {
		while (Game.getInstance().isRunning()) {
			

			int value = (int)(Math.random() * 10);
			String action;
			
			switch(value) {
			case 0:
				action = " attaque son voisin !";
				break;
			case 1:
				action = " pose une tour.";
				break;
			case 2:
				action = " se brosse les dents.";
				break;
			case 3:
				action = " se dirige vers une autre base.";
				break;
			default:
				action = "";
				break;
			}
			
			if(action != "") {
				System.out.println(this.getName() + action);
			}
			
			try {
				Thread.sleep(800);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public IAPlayer(String name, Bank bank) {
		super(name, bank);
	}
	
	public IAPlayer(String name) {
		super(name);
	}
	
	public IAPlayer() {
		super("unknown");
	}
	
	
}
