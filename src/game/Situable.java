package game;

import javax.vecmath.Vector2f;

public interface Situable {
	
	public Vector2f position = new Vector2f();
	public Vector2f getPosition();
	public void setPosition(float x, float y);

}
