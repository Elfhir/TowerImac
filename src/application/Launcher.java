package application;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import org.jdom2.JDOMException;

import exceptions.MapFileException;
import exceptions.RealPlayerException;

import window.AppliWindow;
import window.panel.Panel;

public class Launcher {

	public static void main(String[] args) {
		
// Une JWindow pour le Menu:
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//On crée une nouvelle instance de notre JWindow
				final JWindow startMenu = new JWindow();
				startMenu.setSize(550, 400); // On lui donne une taille pour la voir
				startMenu.setLocationRelativeTo(null);// On centre le menu
				Panel content = new Panel();
				startMenu.getContentPane().add(content);
				startMenu.setVisible(true); // On l'a rend visible
				content.setBackground(new Color(212,154,76));
				
				JLabel title = new JLabel("Tower Carlotta Towa");
				title.setPreferredSize(new Dimension(380, 50));
				title.setBounds(120,10,380,50);
				Font font = new Font("Serif", Font.PLAIN, 32);
				title.setFont(font);
				content.add(title);
				
				JButton startGame = new JButton("Start the Game !");
				content.setLayout(null);
				startGame.setBounds(200, 200, 160, 25);
				startGame.setBackground(new Color(108,146,212));
				startGame.setBorderPainted(false);
				content.add(startGame);
				
				startGame.addActionListener(new ActionListener() {
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
						//We hide the menu starter
						startMenu.dispose();
					}
				});
				
				
				JButton quitGame = new JButton("Exit");
				quitGame.setBounds(200, 230, 160, 25);
				quitGame.setBackground(new Color(108,146,212));
				quitGame.setBorderPainted(false);
				content.add(quitGame);
				
				quitGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0); // The application musts close when we click
					}
				});
				
				JButton options = new JButton("1 adv");
				options.setBounds(200, 260, 80, 15);
				options.setBackground(new Color(108,116,212));
				options.setBorderPainted(false);
				content.add(options);
				
				options.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Options option1 = new Options(1);
					}
				});
				JButton options2 = new JButton("2 adv");
				options2.setBounds(281, 260, 80, 15);
				options2.setBackground(new Color(108,116,212));
				options2.setBorderPainted(false);
				content.add(options2);
				
				options2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Options option2 = new Options(2);
					}
				});
				JButton options3 = new JButton("3 adv");
				options3.setBounds(200, 276, 80, 15);
				options3.setBackground(new Color(108,116,212));
				options3.setBorderPainted(false);
				content.add(options3);
				
				options3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Options option3 = new Options(3);
					}
				});
			}
		});
	}
}
