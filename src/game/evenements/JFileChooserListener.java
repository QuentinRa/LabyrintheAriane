package game.evenements;

import engine.window.Window;
import game.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * Observateur des boutons du JFileChooser du menu
 *
 * @version 1.0 4 mai 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class JFileChooserListener implements ActionListener {
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
	 * Cr&#233;e un observateur des boutons du JFileChooser
	 *
	 * @param screen l'&#233;cran associ&#233; au menu
	 * @param menu le menu
	 *
	 */
	public JFileChooserListener(Window screen, Menu menu){
		this.screen = screen; //ce sur quoi on affiche
		this.menu = menu; //le menu du jeu
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

		// Annulation : on revient en arrière
		if(commande.equals("CancelSelection")){
			this.screen.removeAll();
			this.screen.repaint();
			//On revient en arrière si l'utilisateur fait "annuler"
			this.menu.choix();
		// Validation : on charge le fichier.
		} else if(commande.equals("ApproveSelection")){
			this.screen.removeAll();
			this.screen.repaint();
			//Récupération du gestionnaire de fichiers
			JFileChooser gestionnaire = (JFileChooser) event.getSource();
			//Récupération du fichier sélectionné
			File file = gestionnaire.getSelectedFile();
			//lance le jeu avec sauvegarde
			this.menu.gameStart(file.getPath(),false);
		}
	}
}
