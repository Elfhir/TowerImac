package window.panel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import window.AppliWindow;
import application.Launcher;

import game.Game;
import game.player.Player;

public class PanelLoseOrWin extends JPanel{
	
	private int widthPanelLose = 350;
	private int heightPanelLose = 55;
	
	public PanelLoseOrWin() {
		super();
		buildPanel();
	}
	
	public void buildPanel() {
		this.setBounds(250, 250, widthPanelLose, heightPanelLose);
		this.setVisible(false);
		this.setBackground(Color.BLACK);
		
		JLabel label = new JLabel("La partie est terminée, vous êtes éliminé !");
		this.add(label);
		
		//Nouveau bouton New Game
		JButton newGameButton = new JButton("New Game ?");
		newGameButton.addActionListener(new ActionListener() {
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
		this.add(newGameButton);
	}
}
