package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.MapFileException;

public class MapManager {
	
	private int widthMap, heightMap;
	private int[][] map; 
	
	
	// ------------------ accessors ---------------------
	
	public int getWidthMap() {
		return widthMap;
	}

	public void setWidthMap(int widthMap) {
		this.widthMap = widthMap;
	}

	public int getHeightMap() {
		return heightMap;
	}
	
	public void setHeightMap(int heightMap) {
		this.heightMap = heightMap;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	// ------------------ constructor ---------------------
	
	public MapManager(int widthMap, int heightMap, int[][] map) {
		super();
		this.widthMap = widthMap;
		this.heightMap = heightMap;
		this.map = map;
	}
	
	public MapManager(int widthMap, int heightMap) {
		this(widthMap, heightMap, new int[widthMap][heightMap]);
	}
	
	public MapManager() {
		this(0, 0, null);
	}
	
	// ------------------ other ---------------------
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("map(");
		sb.append(widthMap);
		sb.append(", ");
		sb.append(heightMap);
		sb.append(")\n");
		for(int i=0; i<this.widthMap; ++i) {
			for(int j=0; j<this.heightMap; ++j) {
				sb.append(this.map[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Sets the informations of the map, from a file.
	 * The file must contain integers : width, height of the map and then all the integers for the map :
	 * 0 => lowland
	 * 1 => constructing area of the base n°1
	 * 2=>  constructing area of the base n°2
	 * etc...
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws MapFileException 
	 */
	public void setMapFromFile(String fileName) throws FileNotFoundException, MapFileException {
		
		Scanner scanner = new Scanner(new File("files/"+fileName));
		
		// width
		if (scanner.hasNextInt()) {
			int width = scanner.nextInt();
			if (width >= 0) {
				this.widthMap = width;
			}
			else {
				throw new MapFileException("the given width is negative !");
			}
		}
		else {
			throw new MapFileException("the file doesn't contain any integer !");
		}
		
		//height
		if (scanner.hasNextInt()) {
			int height = scanner.nextInt();
			if (height >= 0) {
				this.heightMap = height;
			}
			else {
				throw new MapFileException("the given height is negative !");
			}
		}
		else {
			throw new MapFileException("the file doesn't contain enough integers !");
		}
		
		// all int from map
		this.map = new int[this.widthMap][this.heightMap];
		
		for(int i=0; i<this.widthMap; ++i) {
			for(int j=0; j<this.heightMap; ++j) {
				if (scanner.hasNextInt()) {
					this.map[i][j] = scanner.nextInt();
				}
				else {
					throw new MapFileException("the file doesn't contain enough integers !");
				}
			}
		}
	}
	
	public static void main(String[] args) throws MapFileException, FileNotFoundException {
		
		MapManager mm = new MapManager();
		System.out.println(mm);
		
		mm.setMapFromFile("map");
		
		System.out.println(mm);

	}

	

}
