package window.panel;

import exceptions.MapFileException;
import exceptions.RealPlayerException;
import game.Game;
import game.player.Player;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdom2.JDOMException;

import window.AppliWindow;
import application.Launcher;

public class PanelWin extends JPanel{
	
	private static final long serialVersionUID = -4257546153337749375L;
	private int widthPanelWin = 615;
	private int heightPanelWin = 65;
	
	public PanelWin() {
		super();
		buildPanel();
	}
	
	@SuppressWarnings("unused")
	public void buildPanel() {
		this.setBounds(150, 250, widthPanelWin, heightPanelWin);
		this.setVisible(false);
		this.setBackground(Color.BLACK);
		
		JLabel label = new JLabel();
		label.setText("Hourra ! Vous vous en êtes bien tiré ! Vous avez éliminé tous vos adversaires ! Bravo !");
		this.add(label);
		
		//Nouveau bouton Relancer une partie
		JButton newGameButton = new JButton("New Game ?");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						// We create the new instance of the JFrame to run the game
						AppliWindow window;
						try {
							// AppliWindow is a Singleton
							window = AppliWindow.getInstance();
							window.init("Tower-IMAC-Nano Prout !", 800, 600, true, "design/testMapGrille.png");
							window.setVisible(true); // And we make it visible
						} catch (MapFileException e) {
							e.printStackTrace();
						} catch (JDOMException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (RealPlayerException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		this.add(newGameButton);
		
		//Nouveau bouton Menu Principal
		JButton menuPrincipalButton = new JButton("Menu Principal");
		menuPrincipalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On relance le jeu si on veut recommencer une partie
				for(Player p : Game.getInstance().getPlayerManager().getPlayers()) {
					p = null;
				}
				//On nettoie la fenêtre courante et tous ses élèments
				AppliWindow.getInstance().dispose();
				System.out.println("Youhou ! new Game");
				// On relance le jeu en appelant la methode statique main() du Launcher
				Launcher.main(null);
			}
		});
		this.add(menuPrincipalButton);
	}
}
