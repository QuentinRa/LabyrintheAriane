package game.evenements;

import engine.window.Window;
import game.GameCore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * Observateur des boutons du jeu principal
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class GameCoreButtonsListener implements ActionListener {
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
	private GameCore gameCore;

	/**
	 *
	 * Cr&#233;e un observateur des boutons du menu
	 *
	 * @param screen l'&#233;cran associ&#233; au menu
	 * @param gameCore la partie principale du jeu
	 *
	 */
	public GameCoreButtonsListener(Window screen, GameCore gameCore){
		this.screen = screen; //ce sur quoi on affiche
		this.gameCore = gameCore; //le menu du jeu
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

		// Vérification des boutons

		switch (commande){
			case "Parcours déterministe" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.gameCore.setRandomType(false); //random = false
				this.gameCore.setMode(); //lance menu de choix du mode
				break;

			case "Parcours aléatoire" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.gameCore.setRandomType(true); //random = true
				this.gameCore.setMode(); //lance menu de choix du mode
				break;

			case "Parcours en mode manuel" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.gameCore.setAutomaticMode(false); //automatique = false
				this.gameCore.gameStart(); //lance le jeu
				break;

			case "Parcours en mode automatique" :
				//Suppression des composants = reset écran
				this.screen.removeAll();
				this.screen.repaint();
				this.gameCore.setAutomaticMode(true); //automatique = true
				this.gameCore.gameStart();  //lance le jeu
				break;
		}
	}
}
