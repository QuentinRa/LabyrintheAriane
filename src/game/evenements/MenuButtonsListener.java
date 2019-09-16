package game.evenements;

import engine.window.Window;
import game.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * Observateur des boutons du menu
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class MenuButtonsListener implements ActionListener {

	/**
	 *
	 *  La fen&#234;tre associ&#233;e au menu
	 *
	 */
	private Window screen;
	/**
	 *
	 *  le menu
	 *
	 */
	private Menu menu;

	/**
	 *
	 * Cr&#233;e un observateur des boutons du menu
	 *
	 * @param screen l'&#233;cran associ&#233; au menu
	 * @param menu le menu
	 *
	 */
	public MenuButtonsListener(Window screen, Menu menu){
		this.screen = screen; //ce sur quoi on affiche
		this.menu = menu; //le menu du jeu
	}

	/**
	 *
	 * Invoqu&#233; lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	 * associ&#233;. Remplie automatiquement.
	 *
	 * @param event l'object de l'&#233;v&#233;nement sous la forme d'un
	 * ActionEvent
	 *
	 * @see ActionEvent
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String commande = event.getActionCommand();

		switch (commande){
			case "Charger un labyrinthe" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.menu.charger();
				break;

			case "Nouveau labyrinthe" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.menu.nouveau();
				break;

			case "Pré-remplir la grille" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				//Lancement du jeu, sans sauvegarde, aléatoire
				this.menu.gameStart(null,true);
				break;

			case "Grille vide" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				//Lancement du jeu, sans sauvegarde, normal
				this.menu.gameStart(null,false);
				break;

			case "Jouer" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				//Lancement du jeu, sans sauvegarde, normal
				this.menu.choix();
				break;
		}
	}
}
