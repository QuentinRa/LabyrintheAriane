package source.tools.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameSelector implements ActionListener{

	private String currentPath;

	/**
	*
	* ...
	*
	*/
	public GameSelector(){
		this.currentPath = "";
	}

	/**
	*
	* ...
	*
	* @param evenement
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){

		//L'objectif est de récupérer le chemin de l'icone cliquée

		//Récupère le bouton
		JButton boutonAppuye = (JButton) evenement.getSource();
		//Si un bouton n'a pas d'icone alors getIcon renvoi null
		// (donc toString rate)
		
		try{
			//récupère le chemin
			this.currentPath = boutonAppuye.getIcon().toString();
		}catch(NullPointerException e){
			this.currentPath = "";
		}
	}

	public String getSelectedFile(){
		return this.currentPath;
	}
}