package game.evenements;

import engine.graph.Grille;
import engine.window.Window;
import engine.exceptions.InvalidDataException;
import engine.exceptions.MessagePopup;
import game.GameCore;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * Observateur des &#233;lements de la partie principale du jeu
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class CreateGameListener implements ActionListener {

	/**
	 *
	 * Fenêtre associ&#234;e au jeu
	 *
	 */
	private Window screen;
	/**
	 *
	 * La partie principale du jeu
	 *
	 */
	private GameCore gameCore;
	/**
	 *
	 * La grille de jeu
	 *
	 */
	private Grille grille;

	/**
	 *
	 * Permet la saisie de la taille de la grille, et les int&#233;ractions avec les boutons
	 *  jouer et r&233;g&#233;nerer
	 *
	 * @param screen fen&#234;tre associée au jeu
	 * @param grille La grille de jeu
	 * @param gameCore la partie principale du jeu
	 *
	 */
	public CreateGameListener(Window screen, Grille grille, GameCore gameCore){
		this.screen = screen; //ce sur quoi on affiche
		this.grille = grille;
		this.gameCore = gameCore;
	}

	/**
	 *
	 * Invoqu&#233; lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	 * associ&#233;. Remplie automatiquement.
	 *
	 * @param event l'object de l'&#233;v&#233;nement sous la forme d'un ActionEvent
	 *
	 * @see ActionEvent
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String commande = event.getActionCommand();

		//Si on n'a pas de taille alors, on en rentre une
		if (this.grille.getSize() == 0) {
			try {
				this.grille.setSize(Integer.parseInt(commande));
				//Suppression des composants = reset
				this.screen.removeAll();
				this.screen.repaint();
				//Charge la méthode pour créer la map
				this.gameCore.createNewGame();
			} catch (NumberFormatException e) {
				//On vide l'étiquette @recommencez !
				JTextField champ = (JTextField) event.getSource();
				champ.setText("");
				String message = "La valeur saisie n'est pas reconnue comme un nombre.";
				MessagePopup popup = new MessagePopup(this.screen, message);
			} catch (InvalidDataException e) {
				//On vide l'étiquette @recommencez !
				JTextField champ = (JTextField) event.getSource();
				champ.setText("");
				MessagePopup popup = new MessagePopup(this.screen, e.getMessage());
			}
		} else if(commande.equals("Jouer")) {
			if(this.grille.check()) {
				//retire listener
				this.grille.removeAllListeners();
				//Suppression des composants = reset
				this.screen.removeAll();
				this.screen.repaint();
				//Charge la méthode pour créer la map
				String save = this.gameCore.getSavePath();
				if (save == null || save.length() == 0) {
					//On lance le jeu avec la grille
					this.gameCore.setType();
				} else {
					//On charge la sauvegarde de la grille
					this.gameCore.run();
				}
			} else {
				String message = "La grille doit contenir un joueur et une sortie.";
				MessagePopup popup = new MessagePopup(this.screen, message);
			}
		} else if (commande.equals("Re-générer")){
			//Charge la méthode pour créer la map
			this.gameCore.createNewGame();
		}
	}
}

