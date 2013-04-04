package game;

import javax.vecmath.Vector2f;

public class Base implements situable{
	
	private int mSize;
	private static int MAX_SIZE;
	private int mDiameter;
	private Player mPlayer;
	private Vector2f mPosition;
	
	public int getSize() {
		return mSize;
	}
	
	public int getDiameter() {
		return mDiameter;
	}
	
	public Player getPlayer() {
		if(hasPlayer()) {
			return mPlayer;
		}
		else return null;
	}
	
	public int getMaxSize() {
		return Base.MAX_SIZE;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("mSize ");
		sb.append(this.mSize);
		sb.append("\n");
		sb.append("MAX_SIZE: ");
		sb.append(Base.MAX_SIZE);
		sb.append("\n");
		sb.append("mDiameter: ");
		sb.append(this.mDiameter);
		sb.append("\n");
		sb.append("mPlayer: ");
		sb.append(this.mPlayer);
		sb.append("\n");
		sb.append("mPosition: ");
		sb.append(this.mPosition);
		sb.append("\n");
		return sb.toString();
	}
	// ----------------------------------------------------ctor-------------
	
	public Base(int size, int diameter){
		super();
		mSize = size;
		mDiameter = diameter;
		mPlayer = null;
	}
	
	public Base(int size, int diameter, Player player){
		super();
		mSize = size;
		mDiameter = diameter;
		mPlayer = player;
	}
	
	public Base(int size, int diameter, Player player, Vector2f position){
		super();
		mSize = size;
		mDiameter = diameter;
		mPlayer = player;
		mPosition = position;
	}
	
	public static void main(String[] args) {
		//Base base = new Base(5, 5, null, new Vector2f(0.5f, 0.5f));
		//System.out.println(base);

	}



}
