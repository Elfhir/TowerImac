package time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerGame{
	
	private Timer timer; 
	private boolean flag; 
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	//--------------------------------------ctor-------------------------
	public TimerGame(int delay, final Timerable t) {
		super();
		
		// delay in milliseconds
		timer = new Timer(delay, new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		    	
		        // An object implementing Timerable can execute runTimer() here.
		        // runTimer() can execute whatever you want : display polygon,
		    	// display time, boiled bacon ...
		    	//
		    	
		    	t.runTimer();
		    	
		    }
		    
		});
	}
	
	public static void main (String[] args){
		
		
		TimerGame clockOfGame = new TimerGame(1000, new Clock(0,0,0) );
		
		if(clockOfGame.isFlag() == true) clockOfGame.getTimer().start();
		
		
		
	}

}
