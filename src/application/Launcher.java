package application;

import window.Panel;

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

import window.AppliWindow;

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
								// On crée la nouvelle instance de la JFrame pour lancer le jeu
								AppliWindow window;
								try {
									window = new AppliWindow("Tower-IMAC-Nano Prout !", 800, 600, true, "design/testMapGrille.png");
									window.setVisible(true); // Et on la rend visible.
								} catch (MapFileException e) {
									e.printStackTrace();
								} catch (JDOMException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
						//We hide the menu starter
						startMenu.setVisible(false);
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
			}
		});
	}
}
