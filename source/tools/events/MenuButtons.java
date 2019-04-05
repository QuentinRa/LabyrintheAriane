package source.tools.events;

import source.tools.Window;
import source.game.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;

/**
*
* Observateur des boutons du menu
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class MenuButtons implements ActionListener{

	/** */
	private Window ecran;
	/** */
	private Menu menu;

	/**
	*
	* Cr&#233;e un observateur des boutons du menu
	*
	* @param ecran
	* @param menu
	*
	*/
	public MenuButtons(Window ecran, Menu menu){
		this.ecran = ecran; //Ce sur quoi on affiche
		this.menu = menu; //le menu du jeu
	}

	/**
	*
	* Invoque lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	* associ&#233;. Rempli automatiquement.
	*
	* @param evenement l'object de l'&#233;v&#233;nement sous la forme d'un
	* ActionEvent
	*
	* @see ActionEvent
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){
		String commande = evenement.getActionCommand();

		//Supression des composants = reset
		this.ecran.removeAll();
		this.ecran.repaint();

		if(commande.equals("Charger")){
			//Charge le menu "Charger"
			this.menu.load();
		}else if(commande.equals("Nouveau")){
			//Charge le menu "Nouveau"
			this.menu.play("");
		} else if(commande.equals("CancelSelection")){
			//On revient en arrière si l'utilisateur fait "annuler"
			this.menu.run();
		} else if(commande.equals("ApproveSelection")){
			//Récupération du gestionnaire de fichiers
			JFileChooser gestionnaire = (JFileChooser) evenement.getSource();
			//Récupération du fichier sélectionné
			File file = gestionnaire.getSelectedFile();
			//Donne le path au menu
			this.menu.play(file.getPath());
		}
	}
}