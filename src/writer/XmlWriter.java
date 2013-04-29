package writer;

import game.Agent;
import game.Base;
import game.Game;
import game.IAPlayer;
import game.Player;
import game.RealPlayer;
import game.Tower;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlWriter {
	
	public static void createXmlFile(Game game) {
		Element gameElement = new Element("game");
		Document document = new Document(gameElement);
		
		gameElement.addContent(getPlayersElement(game));
		gameElement.addContent(getBasesElement(game));
		gameElement.addContent(getTowersElement(game));
		gameElement.addContent(getAgentsElement(game));
		
		saveFile("game.xml", document);
	}
	
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
			playerElement.setAttribute(name);
			playerElement.setAttribute(money);
			playerElement.setAttribute(type);
		}
		
		return playersElement;
	}
	
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
	
	public static void saveFile(String fileName, Document document) {
		
		try {
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(document, new FileOutputStream(fileName));
		}
		catch(java.io.IOException e){}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}

}
