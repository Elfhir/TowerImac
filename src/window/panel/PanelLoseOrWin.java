package window.panel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLoseOrWin extends JPanel{
	
	private int widthPanelLose = 350;
	private int heightPanelLose = 35;
	
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
		}

}
