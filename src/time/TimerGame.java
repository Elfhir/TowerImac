package time;
import game.agent.Agent;
import game.base.Base;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.vecmath.Vector2f;


/**
 * 
 * @author thanhliem
 * @deprecated
 */
@Deprecated
public class TimerGame extends JFrame {
	
	
	private static final long serialVersionUID = 4728920536811198811L;

	protected Timer timer;
	private Clock clock;

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TimerGame [timer=" + timer + ", clock=" + clock + "]";
	}

	// --------------------------------------------------------constructor------------------------
	// default
	public TimerGame(int delay) throws HeadlessException{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pinage");

		clock = new Clock(0,0,0);
		
		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clock.timeChange();
				System.out.println(clock);
			}
		};
		setVisible(true);
		timer = new Timer(delay, task);
		timer.start();

	}
	
	// Clock initializing
	public TimerGame(int delay, int s, int m, int h) throws HeadlessException{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pinage");

		clock = new Clock(s,m,h);
		
		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clock.timeChange();
				System.out.println(clock);	      
			}
		};
		setVisible(true);
		timer = new Timer(delay, task);
		timer.start();

	}
	
	// With a Timerable class implementing, for executing code from other classes, other stuff
	public TimerGame(int delay, int s, int m, int h, final Timerable t) throws HeadlessException{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pinage");

		clock = new Clock(s,m,h);
		
		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// do whatever you want with a Class implementing Timerable
				// in its compulsory void method runTimer()
				
				t.runTimer();   
				
			}
		};
		// JFrame method ; then a timer is created, executing the task every delay milliseconds. At the instantiation timer
		// is started. There is method for handle timer.
		setVisible(true); 
		timer = new Timer(delay, task);
		timer.start();

	}
	
	// With a Timerable class implementing, for executing code from other classes, other stuff
	public TimerGame(int delay, int s, int m, int h, final Agent a, final Vector2f v) throws HeadlessException{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pinage");

		clock = new Clock(s,m,h);
		
		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// do whatever you want with a Class implementing Timerable
				// in its compulsory void method runTimer()
				
				a.runTimer(v);   
				
			}
		};
		// JFrame method ; then a timer is created, executing the task every delay milliseconds. At the instantiation timer
		// is started. There is method for handle timer.
		setVisible(true); 
		timer = new Timer(delay, task);
		timer.start();

	}
	
	// With a Timerable class implementing, for executing code from other classes, other stuff
		public TimerGame(int delay, int s, int m, int h, final Base base, final boolean b) throws HeadlessException{
			super();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Pinage");

			clock = new Clock(s,m,h);
			
			ActionListener task = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					
					// do whatever you want with a Class implementing Timerable
					// in its compulsory void method runTimer()
					if(b == true) 
						base.runTimer(true);   
					else
						base.runTimer(false);
					
				}
			};
			// JFrame method ; then a timer is created, executing the task every delay milliseconds. At the instantiation timer
			// is started. There is method for handle timer.
			setVisible(true); 
			timer = new Timer(delay, task);
			timer.start();

		}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{	  
		Clock clock = new Clock(0, 0, 0);
		TimerGame tg = new TimerGame(1000, 0, 0, 0, clock);
	}

}
