package time;

public class Clock implements Timerable {
	
	int seconds;
	int minutes;
	int hour;
	
	@Override
	public String toString() {
		return "Clock [seconds=" + seconds + ", minutes=" + minutes + ", hour="
				+ hour + "]";
	}
	
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
	// -------------------------------------------- clock running ---------------
	
	public void timeChange() {
		this.integrityTime();
		this.setSeconds(this.getSeconds()+1);
		this.integrityTime();
	}
	
	public void integrityTime() {
		if(this.getHour() == 24) {
			this.setHour(0);
		}
		if(this.getMinutes() == 60) {
			this.setMinutes(0);
			this.setHour(this.getHour()+1);
		}	
		if(this.getSeconds() == 60) {
			this.setSeconds(0);
			this.setMinutes(this.getMinutes()+1);
		}
	}

	//---------------------------------------------------------ctor-------------
	public Clock() {
		super();
		this.seconds = 0;
		this.minutes = 0;
		this.hour = 0;
	}
	
	public Clock(int sec, int min, int h) {
		super();
		
		// Simple correction if arguments are crazy, it should be better with second and minute reports.
		h = h >= 24 ? 0 : h ;
		min = min >= 60 ? 0 : min ;
		sec = sec >= 60 ? 0 : sec ;
		
		this.seconds = sec;
		this.minutes = min;
		this.hour = h;
	}
	
	
	// ------------------------------- Timerable methods --------------------------
	@Override
	public void runTimer() {
		this.timeChange();
		System.out.println(this);
	}
	

	public static void main(String[] args) {
		
		/* Calling timeChange() on an instance of Clock emulate a clock. 
		 * Using a Timer from Swing in TimerGame for instance
		 */
		Clock test = new Clock(0,0,0);
		
		for(int i = 0; i<65; ++i) {
			test.timeChange();
			System.out.println(test);
		}
		

	}
}
