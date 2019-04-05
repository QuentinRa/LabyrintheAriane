package source.tools.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
*
* Observateur qui conserve le chemin des icones du dernier &#233;lement ayant
* subit un &#233;v&#233;nement.
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class IconListener implements ActionListener{

	/** le chemin contenu dans la dernière icone des &#233;léments*/
	private String currentPath;

	/**
	*
	* Cr&#233;e l'observateur
	*
	*/
	public IconListener(){
		this.currentPath = "";
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

		//L'objectif est de récupérer le chemin de l'icone cliquée

		//Récupère le bouton
		JButton boutonAppuye = (JButton) evenement.getSource();
		//Si un bouton n'a pas d'icone alors getIcon renvoi null
		// (donc toString rate)
		
		try{
			//récupère le chemin
			this.currentPath = boutonAppuye.getIcon().toString();
		}catch(NullPointerException e){
			//Si c'était null
			this.currentPath = "";
		}
	}

	/**
	*
	* Retourne le chemin de l'icone associ&#233;e au dernier bouton cliqu&#233;
	*
	* @return la chemin de l'icone r&#233;cup&#233;r&#233;e
	*
	*/
	public String getIconPath(){
		return this.currentPath;
	}
}