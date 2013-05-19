package window.panel;

import game.Game;
import game.player.Player;
import game.player.RealPlayer;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import window.AppliWindow;

public class PanelInfoRealPlayer extends JPanel {
	
	private int widthPanelRealPlayer = 200;
	private int heightPanelRealPlayer = 100;
	private int visibleHeightPanelRealPlayer = 40;
	
	private boolean buildToolsVisible = false;
	
	public boolean isBuildToolsVisible() {
		return this.buildToolsVisible;
	}
	
	public void setBuildToolsVisible(boolean buildToolsVisible) {
		this.buildToolsVisible = buildToolsVisible;
	}

	public PanelInfoRealPlayer() {
		super();
	}
	
	public void buildPanel(int widthWindow, final int heightWindow, final RealPlayer realPlayer) {
		
		this.setBounds(0, heightWindow - visibleHeightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
		this.setVisible(true);
		
		//final RealPlayer realPlayer = (RealPlayer) Game.getInstance().getPlayerManager().getRealPlayer();
		JLabel label = new JLabel(realPlayer.getInfosPlayer());
		
		this.add(label);
		this.setBackground(null);
		
		JPanel panelAvailableBases = new JPanel();
		
		JButton buttonGunTower = new JButton("GunTower");
		buttonGunTower.addActionListener(new ActionListener() {
								
		public void actionPerformed(ActionEvent e) {
			realPlayer.setBuildingTower(true);
			}
		});
		panelAvailableBases.add(buttonGunTower);
		
		this.setBackground(Color.RED);
		this.add(panelAvailableBases);
		
//		// Doesn't work for the moment !! Need to be fixed.
//		for (String s: Game.getInstance().getTowerManager().getAvailableTowerTypes()) {
//			Class cls;
//			try {
//				cls = Class.forName(s);
//				Tower clsInstance = (Tower) cls.newInstance();
//				System.out.println(clsInstance);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//		
		//AppliWindow.getInstance().add(this);
		
		// add actionListener to show or hide the panel
		this.addMouseListener(new MouseListener() {
			
			// shows the panel
			public void showPanel() {
				AppliWindow.getInstance().getPanelInfoRealPlayer().setBounds(0, heightWindow - heightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
				setBuildToolsVisible(true);
			}
			
			// hide the panel
			public void hidePanel() {
				AppliWindow.getInstance().getPanelInfoRealPlayer().setBounds(0, heightWindow - visibleHeightPanelRealPlayer, widthPanelRealPlayer, heightPanelRealPlayer);
				setBuildToolsVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isBuildToolsVisible() ==  true) {
					hidePanel();
				}
				else {
					showPanel();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	
	public void update(Player player, int nbTotalBases, int nbBasesPlayer) {
		
		
		StringBuilder sb = new StringBuilder(player.getName());
		sb.append(" : $");
		sb.append(player.getBank().getMoney());
		sb.append(" | ");
		sb.append(nbBasesPlayer);
		sb.append("/");
		sb.append(nbTotalBases);
		
		// The label is the first Component in the panel
		//JPanel panelInfoRealPlayer = (JPanel) panelRealPlayer.getComponent(0);
		JLabel labelInfoRealPlayer = (JLabel) this.getComponent(0);
		// we can update the text of the button
		labelInfoRealPlayer.setText(sb.toString());
		
		
	}
	
}
