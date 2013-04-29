package writer;

import game.Agent;
import game.Bank;
import game.Base;
import game.Game;
import game.IAPlayer;
import game.Player;
import game.RealPlayer;
import game.Tower;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.vecmath.Vector2f;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlReader {

	
	public static void createGame(Game game, String fileName) throws JDOMException, IOException {
		
		SAXBuilder sxb = new SAXBuilder();
		
		Document document = sxb.build(new File(fileName));
		Element root = document.getRootElement();
		
		Element playersElement = root.getChild("players");
		createPlayers(game, playersElement);
		
		Element basesElement = root.getChild("bases");
		createBases(game, basesElement);
		
		Element towersElement = root.getChild("towers");
		createTowers(game, towersElement);
		
		Element agentsElement = root.getChild("agents");
		createAgents(game, agentsElement);

	}
	
	public static void createPlayers(Game game, Element playersElement) {
		List<Element> players = playersElement.getChildren("player");
		Iterator<Element> i = players.iterator();
		while (i.hasNext()) {
			Element playerElement = (Element)i.next();
			
			String name = playerElement.getAttributeValue("name");
			String type = playerElement.getAttributeValue("type");
			int money = Integer.parseInt(playerElement.getAttributeValue("money"));
			
			Player player = (type == "RealPlayer") ? 
					new RealPlayer(name, new Bank(money)) : new IAPlayer(name, new Bank(money)); 
			
			game.getPlayerManager().addPlayer(player);
		}
	}
	
	public static void createBases(Game game, Element basesElement) {
		List<Element> bases = basesElement.getChildren("base");
		Iterator<Element> i = bases.iterator();
		while (i.hasNext()) {
			Element baseElement = (Element)i.next();
			
			float x = Float.parseFloat(baseElement.getAttributeValue("x"));
			float y = Float.parseFloat(baseElement.getAttributeValue("y"));
			String playerName = baseElement.getAttributeValue("player");
			int size = Integer.parseInt(baseElement.getAttributeValue("size"));
			int diameter = Integer.parseInt(baseElement.getAttributeValue("diameter"));
			int nbAgents = Integer.parseInt(baseElement.getAttributeValue("nbAgents"));
			
			Vector2f position = new Vector2f(x, y);
			Player player = game.getPlayerManager().getPlayer(playerName);
			Base base = new Base(size, diameter, player, position, nbAgents);
			game.getBaseManager().addBase(base);
			
		}
	}
	
	public static void createTowers(Game game, Element towersElement) {
		List<Element> towers = towersElement.getChildren("tower");
		Iterator<Element> i = towers.iterator();
		while (i.hasNext()) {
			Element towerElement = (Element)i.next();
			
			Tower tower = new Tower();
			
			game.getTowerManager().addTower(tower);
		}
	}
	
	public static void createAgents(Game game, Element agentsElement) {
		List<Element> agents = agentsElement.getChildren("agent");
		Iterator<Element> i = agents.iterator();
		while (i.hasNext()) {
			Element agentElement = (Element)i.next();
			
			// ...
			
			Agent agent = new Agent();
			
			game.getAgentManager().addAgent(agent);
		}
	}
	
	
	
	public static void main(String[] args) {
		
		Game game = Game.getInstance();
		
		try {
			createGame(game, "game.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(game);
	}

}
