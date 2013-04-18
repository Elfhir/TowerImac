package time;

import javax.vecmath.Vector2f;

public interface Timerable {
	
	// runTimer is a general surroundings for executing whatever you want
	//
	//
	public void runTimer();
	public void runTimer(boolean b);
	public void runTimer(Timerable t);
	public void runTimer(Vector2f v);
}
