package window.panel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdom2.JDOMException;

import exceptions.MapFileException;
import exceptions.RealPlayerException;

import window.AppliWindow;
import application.Launcher;

import game.Game;
import game.Theme;
import game.player.Player;

public class PanelLose extends JPanel{
	
	private static final long serialVersionUID = -1031783861852942710L;
	private int widthPanelLose = 350;
	private int heightPanelLose = 55;
	
	public PanelLose() {
		super();
		buildPanel();
	}
	
	@SuppressWarnings("unused")
	public void buildPanel() {
		this.setBounds(250, 250, widthPanelLose, heightPanelLose);
		this.setVisible(false);
		this.setBackground(Color.BLACK);
		
		JLabel label = new JLabel("La partie est terminée, vous êtes éliminé !");
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
									window.init("Tower-IMAC-Nano Prout !", 800, 600, true, Theme.pathImageMap);
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
		
		//Nouveau bouton New Game
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
