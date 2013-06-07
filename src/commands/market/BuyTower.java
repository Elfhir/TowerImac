package commands.market;

import game.Game;
import game.Theme;
import game.player.Player;
import game.player.RealPlayer;
import game.tower.GunTower;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.vecmath.Vector2f;

import window.AppliWindow;

import commands.Command;

@SuppressWarnings("unused")
public class BuyTower extends Command {
	
	private Player player;
	private String typeTower;
	private Vector2f position;
	
	@Override
	public void doCommand() {
		
		GunTower gt = new GunTower(this.position.x, this.position.y, this.player);
		
		// Can the player allow it ?
		if(player.getBank().getMoney() > gt.getBuyingPrice()) {
			
			// we add the tower to the game
			Game.getInstance().getTowerManager().addTower(gt);	

			if (this.player instanceof RealPlayer) {
				((RealPlayer)this.player).setBuildingTower(false);
			}
			
			
			gt.setBorder(BorderFactory.createLineBorder(Color.black));
			gt.setContentAreaFilled(false);
			gt.setBounds((int)gt.getPosition().x, (int)gt.getPosition().y, 40, 40);
			gt.setOpaque(true);
			try
			{
				gt.setIcon(new ImageIcon(ImageIO.read(new File(Theme.pathImageTower))));
				gt.setBackground(Color.RED);
	
			}
			catch (IOException e1)
			{
				System.out.println(e1);
			}
			
			// we add the tower to the window
			AppliWindow.getInstance().getContent().add(gt);
			AppliWindow.getInstance().giveFocusToPanel();
			
			// we remove the money
			this.player.getBank().setMoney(this.player.getBank().getMoney() - gt.getBuyingPrice());
			
		} else {
			System.out.println("Out of Money sorry, poor !");
		}
	}
	
	public BuyTower(Player player, String typeTower, int x, int y) {
		super();
		this.player = player;
		this.typeTower = typeTower;
		this.position = new Vector2f(x, y);
	}
}
