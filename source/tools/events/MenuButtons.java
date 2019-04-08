package source.tools.events;

import source.tools.Window;
import source.game.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
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

	/** La fen&#234;tre associ&#233;e au menu */
	private Window ecran;
	/** le menu */
	private Menu menu;

	/**
	*
	* Cr&#233;e un observateur des boutons du menu
	*
	* @param ecran l'&#233;cran associ&#233; au menu
	* @param menu le menu
	*
	*/
	public MenuButtons(Window ecran, Menu menu){
		this.ecran = ecran; //ce sur quoi on affiche
		this.menu = menu; //le menu du jeu
	}

	/**
	*
	* Invoqu&#233; lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	* associ&#233;. Remplie automatiquement.
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

		//Supression des composants = reset écran
		this.ecran.removeAll();
		this.ecran.repaint();

		if(commande.equals("Charger")){
			//Charge le menu "Charger"
			this.menu.load();
		}else if(commande.equals("Nouveau")){
			//Charge le menu le jeu sans sauvegarde
			this.ecran.removeAll();
			this.ecran.repaint();
			this.ecran.setLayout(new GridBagLayout());
			GridBagConstraints bag = new GridBagConstraints();
			
			//affiche boutons aléatoire et déterministe
			JPanel choix = new JPanel();
			JButton random = new JButton("Aléatoire");
			JButton vide = new JButton("Vide");
			choix.setLayout(new GridLayout(2,1));
			choix.add(random);
			choix.add(vide);
			random.addActionListener(this); //observateur
			vide.addActionListener(this);
			this.ecran.add(choix,bag);
			this.ecran.revalidate();

		}else if(commande.equals("Aléatoire") || commande.equals("Vide")){
			//Charge le menu le jeu sans sauvegarde
			boolean value = false;
			if(commande.equals("Aléatoire")) value = true;
			this.menu.play("",value);

		// Cancel/Approve selection ne sont valables que dans l'interface
		// du JFileChooser (cancer = annuler, approve = double clic/ouvrir)

		} else if(commande.equals("CancelSelection")){
			//On revient en arrière si l'utilisateur fait "annuler"
			this.menu.run();
		} else if(commande.equals("ApproveSelection")){
			//Récupération du gestionnaire de fichiers
			JFileChooser gestionnaire = (JFileChooser) evenement.getSource();
			//Récupération du fichier sélectionné
			File file = gestionnaire.getSelectedFile();
			//Donne le path au menu
			this.menu.play(file.getPath(),false);//lance le jeu avec sauvegarde
		}
	}
}