package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel{

	// LList of Base extends JButton ?
	
	/**
	 * Il le faut à chaque fois
	 */
	private static final long serialVersionUID = -1788677938220552169L;
	
	/*
	 * Does not work
	 */
	public void drawGrid(Graphics g) {
	    //x1, y1, x2, y2
	    g.drawLine((this.getWidth()/2), 0, (this.getWidth()/2), this.getHeight());
	    g.drawLine(0, (this.getHeight()/2), this.getWidth(), (this.getHeight()/2));
	}   
	
	/** 
	 * Build a grid of Tiles, ( numOfTileWidth x numOfTileHeight ) sized
	 * Tiles are square, so size is sizeOfTile, like 50px for instance.
	 * 
	 * @param	numOfTileWidth	how many tiles on a row
	 * @param	numOfTileHeigh	how many tiles on a column
	 * @param	sizeOfTile	the size of a tile
	 * @param	c	A GridBagConstraint, because of a GribBagLayout surrending.
	 * 
	 * @return	an ArrayList of ArrayList of Panel, a 2-D Tab. 
	 */
	public ArrayList<ArrayList<Panel>> newGrid(int numOfTileWidth, int numOfTileHeight, int sizeOfTile, Panel content, GridBagLayout grille, GridBagConstraints c) {
		
		ArrayList<ArrayList<Panel>> cells = null;
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		Panel cell = new Panel();
		
		for(int i = 0; i<numOfTileHeight; ++i) {
			for(int j = 0; j<numOfTileWidth; ++j) {
				cell.setBackground(new Color(50,i*10,j*10));
				cell.setPreferredSize(new Dimension(sizeOfTile, sizeOfTile));
				c.gridx = i;
				c.gridy = j;
				grille.setConstraints(cell, c);
				content.add(cell, c);
				System.out.println("i: "+i+" -- j: "+j);
			}
				c.gridwidth = GridBagConstraints.REMAINDER;
		}
			
		return cells;
	}
	
	public Panel() {
		super();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
