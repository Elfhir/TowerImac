package writer;

import game.Agent;
import game.Base;
import game.Game;
import game.IAPlayer;
import game.Player;
import game.RealPlayer;
import game.Tower;

import java.awt.Color;
import java.io.FileOutputStream;

import javax.vecmath.Vector2f;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlWriter {
	
	/**
	 * Creates a XML file from all informations known. All informations needed are accessible from the class Game, so it's the only parameter.
	 * 
	 * @param game	the instance of the Game singleton
	 * @see	Game
	 * @see	XmlReader
	 */
	public static void createXmlFile(Game game) {
		Element gameElement = new Element("game");
		Document document = new Document(gameElement);
		if(game != null){
			gameElement.addContent(getPlayersElement(game));
			gameElement.addContent(getBasesElement(game));
			gameElement.addContent(getTowersElement(game));
			gameElement.addContent(getAgentsElement(game));
		}
		saveFile("files/game.xml", document);
	}
	
	/**
	 * Returns the XML element corresponding to the players informations.
	 * @param game the instance of the Game singleton
	 * @return an XML Element
	 * @see Element
	 * @see Document
	 * @see #getAgentsElement(Game)
	 * @see #getBasesElement(Game)
	 * @see #getTowersElement(Game)
	 */
	public static Element getPlayersElement(Game game) {
		/* Players */
		Element playersElement = new Element("players");
		for(Player player: game.getPlayerManager().getPlayers()) {
			// new player
			Element playerElement = new Element("player");
			playersElement.addContent(playerElement);
			// attributes 
			Attribute name = new Attribute("name", player.getName());
			Attribute money = new Attribute("money", String.valueOf(player.getBank().getMoney()));
			Attribute type = new Attribute("type", (player instanceof IAPlayer) ? "IAPlayer" : "RealPlayer");
			Attribute color = new Attribute("color", player.getColorString());
			playerElement.setAttribute(name);
			playerElement.setAttribute(money);
			playerElement.setAttribute(type);
			playerElement.setAttribute(color);
		}
		
		return playersElement;
	}
	
	/**
	 * Returns the XML element corresponding to the bases informations.
	 * @param game the instance of the Game singleton
	 * @return an XML Element
	 * @see Element
	 * @see Document
	 * @see #getAgentsElement(Game)
	 * @see #getPlayersElement(Game)
	 * @see #getTowersElement(Game)
	 */
	public static Element getBasesElement(Game game) {
		/* Players */
		Element basesElement = new Element("bases");
		for(Base base: game.getBaseManager().getBases()) {
			// new player
			Element baseElement = new Element("base");
			basesElement.addContent(baseElement);
			// attributes 
			Attribute x = new Attribute("x", String.valueOf(base.getPosition().x));
			Attribute y = new Attribute("y", String.valueOf(base.getPosition().y));
			Attribute player = new Attribute("player", base.getPlayer().getName());
			Attribute nbAgents = new Attribute("nbAgents", String.valueOf(base.getNbAgents()));
			Attribute might = new Attribute("might", String.valueOf(base.getMight()));
			baseElement.setAttribute(x);
			baseElement.setAttribute(y);
			baseElement.setAttribute(player);
			baseElement.setAttribute(nbAgents);
			baseElement.setAttribute(might);
			
		}
		
		return basesElement;
	}
	
	/**
	 * Returns the XML element corresponding to the agents informations.
	 * @param game the instance of the Game singleton
	 * @return an XML Element
	 * @see Element
	 * @see Document
	 * @see #getPlayersElement(Game)
	 * @see #getBasesElement(Game)
	 * @see #getTowersElement(Game)
	 */
	public static Element getAgentsElement(Game game) {
		/* Players */
		Element agentsElement = new Element("agents");
		for(Tower tower: game.getTowerManager().getTowers()) {
			// new player
			Element agentElement = new Element("agent");
			agentsElement.addContent(agentElement);
			// attributes 
			//...
			
		}
		
		return agentsElement;
	}
	
	/**
	 * Returns the XML element corresponding to the towers informations.
	 * @param game the instance of the Game singleton
	 * @return an XML Element
	 * @see Element
	 * @see Document
	 * @see #getAgentsElement(Game)
	 * @see #getBasesElement(Game)
	 * @see #getPlayersElement(Game)
	 */
	public static Element getTowersElement(Game game) {
		/* Players */
		Element towersElement = new Element("towers");
		for(Tower tower: game.getTowerManager().getTowers()) {
			// new player
			Element towerElement = new Element("tower");
			towersElement.addContent(towerElement);
			// attributes 
			//...
			
		}
		
		return towersElement;
	}
	
	/**
	 * Saves the document created by {@link #createXmlFile(Game)}
	 * @param fileName	The name of the XML file created
	 * @param document	The document to write
	 * @see Document
	 */
	public static void saveFile(String fileName, Document document) {
		
		try {
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(document, new FileOutputStream(fileName));
		}
		catch(java.io.IOException e){}
	}
	
	
	public static void main(String[] args) {
		
		//Create THE game and all datas
		Game game = Game.getInstance();
		
		Player michel = new RealPlayer("Michel", Color.MAGENTA);
		Player jean_luc = new IAPlayer("Jean-Luc", Color.BLACK);
		Player patrick = new IAPlayer("Patrick", Color.GREEN);
		Player germaine = new IAPlayer("Germaine", Color.ORANGE);
		
		game.getPlayerManager().addPlayer(michel);
		game.getPlayerManager().addPlayer(jean_luc);
		game.getPlayerManager().addPlayer(patrick);
		game.getPlayerManager().addPlayer(germaine);
		
		game.getBaseManager().addBase(new Base(5, michel, new Vector2f(50, 100), 5));
		game.getBaseManager().addBase(new Base(5, jean_luc, new Vector2f(500, 450), 5));
		game.getBaseManager().addBase(new Base(5, jean_luc, new Vector2f(30, 380), 5));
		game.getBaseManager().addBase(new Base(5, jean_luc, new Vector2f(30, 380), 5));
		game.getBaseManager().addBase(new Base(5, jean_luc, new Vector2f(430, 180), 5));
		game.getBaseManager().addBase(new Base(5, jean_luc, new Vector2f(750, 185), 5));
		XmlWriter.createXmlFile(game);
		
	}

}
