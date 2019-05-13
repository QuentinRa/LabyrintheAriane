package source.tools.events;

import source.tools.Grille;
import source.tools.Cases;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
*
* Observateur des bouton de selection du motif de remplissage
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameSet implements ActionListener{

	/** La grille */
	private Grille grille;

	/**
	*
	*
	*
	* @param grille la grille du jeu
	*
	*/
	public GameSet(Grille grille){
		this.grille = grille; //le menu du jeu
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
		JButton boutonAppuye = (JButton) evenement.getSource();

		try{
			//récupère le chemin
			this.grille.setImageIcon(boutonAppuye.getIcon().toString());
		}catch(NullPointerException e){
			//Si c'était null
			this.grille.setImageIcon(null);
		}
	}
}