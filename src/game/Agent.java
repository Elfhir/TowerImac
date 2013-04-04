package game;

import javax.vecmath.Vector2f;

public class Agent implements situable{
	
	private int mPV;
	private int mSpeed;
	private int mStamina;
	private Vector2f mPosition;
	private Player mPlayer;
	
	public int getPV() {
		return mPV;
	}

	public void setPV(int mPV) {
		this.mPV = mPV;
	}

	public int getSpeed() {
		return mSpeed;
	}

	public void setSpeed(int mSpeed) {
		this.mSpeed = mSpeed;
	}

	public int getStamina() {
		return mStamina;
	}

	public void setStamina(int mStamina) {
		this.mStamina = mStamina;
	}

	public Player getPlayer() {
		return mPlayer;
	}

	public void setPlayer(Player mPlayer) {
		this.mPlayer = mPlayer;
	}
	
	public boolean hasPlayer() {
		if (mPlayer != null) {
			return true;
		}
		else return false;
	}
	
	@Override
	public Vector2f getPosition() {
		return mPosition;
	}

	@Override
	public void setPosition(float x, float y) {
		mPosition.x = x;
		mPosition.y = y;
	}
	
	//--------------------------------------------------ctor-----------------------
	public Agent(int PV, int speed, int stamina, Vector2f position, Player player) {
		mPV = PV;
		mSpeed = speed;
		mStamina = stamina;
		mPosition = position;
		mPlayer = player;
		
	}
	
	public static void main(String[] args) {
		
	}

}
