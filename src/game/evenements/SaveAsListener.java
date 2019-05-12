package game.evenements;

import engine.window.Window;
import engine.exceptions.ErrorPopup;
import engine.exceptions.MessagePopup;
import game.GameCore;
import game.utils.WriteLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * Observateur des boutons du menu
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class SaveAsListener implements ActionListener {

	/**
	 *
	 * &#233;cran o&#249; afficher les messages
	 *
	 */
	private final Window screen;
	/**
	 *
	 * la partie principale du jeu, contient la grille
	 *
	 */
	private final GameCore gameCore;

	/**
	 *
	 * Le chemin o&#249; sauvegarder
	 *
	 */
	private static String globalPath = "ressources/sav/";

	/**
	 *
	 * Sur un JTextField, enregistre &#224; l'adresse saisie par l'utilisateur une grille
	 *
	 * @param screen &#233;cran o&#249; afficher les messages
	 * @param gameCore la partie principale du jeu, contient la grille
	 *
	 */
	public SaveAsListener(Window screen,GameCore gameCore){
		this.screen = screen;
		this.gameCore = gameCore;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String commande = event.getActionCommand();

		//On vide l'étiquette

		JTextField champ;
		try {
			champ = (JTextField) event.getSource();
		} catch (ClassCastException e) {
			throw new IllegalStateException("Ce n'est pas un JTextField!");
		}
		champ.setText("");

		String message = "Sauvegardé : "+globalPath+commande+".lab";

		//garde 0 : partie avant saut de ligne
		String withOutLineSeparator = commande.split(System.lineSeparator())[0];

		//Si on a pas non match avec caractère alphanumériques alors il y a eu erreur
		if(commande.length() == 0 && !withOutLineSeparator.matches("\\p{Alnum}")){
			message = "Le nom est invalide, non sauvegardé!";
		} else {

			//On garde la sauvegarde
			this.gameCore.setSavePath(globalPath + commande + ".lab");

			try {
				WriteLoader.writeSave(this.gameCore.getGrille(), this.gameCore.getSavePath());
			} catch (IOException e) {
				ErrorPopup error = new ErrorPopup(this.screen, e.getMessage());
			}
		}

		MessagePopup popup = new MessagePopup(this.screen,message);
	}

	/**
	 *
	 * Renvoi le chemin o#249; sont sauvegard&#234;e les sauvegardes
	 *
	 * @return le chemin o#249; sont sauvegard&#234;e les sauvegardes
	 *
	 */
	public static String getGlobalPath() {
		return globalPath;
	}

	/**
	 *
	 * Change le chemin o#249; sont sauvegard&#234;e les sauvegardes
	 *
	 * @param globalPath le chemin o#249; sont sauvegard&#234;e les sauvegardes
	 *
	 */
	public static void setGlobalPath(String globalPath) {
		SaveAsListener.globalPath = globalPath;
	}
}

