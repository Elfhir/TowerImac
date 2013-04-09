package time;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimerGame extends JFrame
{
	private static final long serialVersionUID = 4728920536811198811L;


	protected Timer timer;

	public TimerGame() throws HeadlessException{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(":)");

		int delay = 1000; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println(java.util.Calendar.getInstance().getTime().toString());		      
			}
		};
		setVisible(true);
		timer = new Timer(delay, taskPerformer);
		timer.start();

	}

	public static void main(String[] args)
	{	  
		new TimerGame();

	}

}
