package game.agent;

import game.base.Base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.vecmath.Vector2f;

//import javax.swing.JLabel;

public class GroupAgent extends Agent {
	
	private int nbAgent;
	private Base baseOrigin;
	private Base baseDestination;
	private Vector2f vectorDirector;
	private BufferedImage image;
	
	public GroupAgent(int nbInitialAgent, Base source, Base destination){
		
		this.nbAgent = nbInitialAgent;
		this.baseOrigin = source;
		this.baseDestination = destination;
		this.position = new Vector2f(source.getPosition().x, source.getPosition().y);
		this.speed = 1;
		this.computeVectorDirector();

		this.setBackground(source.getPlayer().getColor());
		
		Dimension dim = new Dimension(this.nbAgent, this.nbAgent);
		this.setSize(dim);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);

		try {
			this.image = ImageIO.read(new File("design/groupe.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void computeVectorDirector() {
		this.vectorDirector = new Vector2f();
		this.vectorDirector.x = this.baseDestination.getPosition().x-baseOrigin.getPosition().x;
		this.vectorDirector.y = baseDestination.getPosition().y-baseOrigin.getPosition().y;
		this.vectorDirector.normalize();
	}
	
	public int getNbAgent() {
		return this.nbAgent;
	}
	
	/*public boolean AuthorizedMove(){
		position = getPosition();
		map[x][y] = -1;
		if(position = map[x][y]) setMoving();
		return true;
	}*/
	
	public void moveOneStep(){
		float x = this.position.x + this.vectorDirector.x * this.speed;
		float y = this.position.y += this.vectorDirector.y * this.speed;
		this.setPosition(x, y);
	}
	
}	

