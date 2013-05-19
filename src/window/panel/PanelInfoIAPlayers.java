package window.panel;

import game.player.Player;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoIAPlayers extends JPanel {
	
	private int widthPanelIAPlayer = 250;
	private int heightPanelIAPlayer = 40;
	
	public PanelInfoIAPlayers() {
		super();
	}
	
	public void buildPanel(int widthWindow, int heightWindow, Player player) {
		this.setBounds(widthWindow - widthPanelIAPlayer, heightWindow - heightPanelIAPlayer, widthPanelIAPlayer, heightPanelIAPlayer);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel(player.getInfosPlayer());
		label.setForeground(player.getColor());
		panel.add(label);
		panel.setBackground(null);
		this.add(panel);
	}
	
	public void updateComponent(int indexIAPlayer, Player player, int nbTotalBases, int nbBasesPlayer) {
		// the panel of the player
		JPanel panel = (JPanel) this.getComponent(indexIAPlayer);
		// the label of the player = it's the first element
		JLabel label = (JLabel) panel.getComponent(0);
		
		StringBuilder sb = new StringBuilder("<html>");
		sb.append(player.getName());
		sb.append("<br /> $");
		sb.append(player.getBank().getMoney());
		sb.append(" | ");
		sb.append(nbBasesPlayer);
		sb.append("/");
		sb.append(nbTotalBases);
		sb.append("</html>");
		
		// we change the text of the label
		label.setText(sb.toString());	
	}
}
