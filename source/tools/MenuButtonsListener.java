/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools;

import source.tools.Background;
import source.game.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;

public class MenuButtonsListener implements ActionListener{

	private Background ecran;
	private Menu menu;

	public MenuButtonsListener(Background ecran, Menu menu){
		this.ecran = ecran; //Ce sur quoi on affiche
		this.menu = menu; //le menu du jeu
	}

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
			this.menu.play();
		} else if(commande.equals("CancelSelection")){
			//On revient en arrière si l'utilisateur fait "annuler"
			this.menu.run();
		} else if(commande.equals("ApproveSelection")){
			//Récupération du gestionnaire de fichiers
			JFileChooser gestionnaire = (JFileChooser) evenement.getSource();
			//Récupération du fichier sélectionné
			File file = gestionnaire.getSelectedFile();
			//Donne le path au menu
			this.menu.setFilePath(file.getPath());
			this.menu.play();
		}
	}
}