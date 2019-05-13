package source;

import source.game.Ariane; //Jeu
import source.game.interfaces.IGame; //Interface jeu type

/**
*
* Lancement du jeu
* 
* make : compiler le programme
* make run : lancer le programme
* make javadoc : cr&#233;e la documentation du projet
* make doc : lancer la javadoc (firefox)
* make clean : supprimer javadoc+programme
*
* @version 1.0 1 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

public class Main{

	/**
	*
	* m&#233;thode qui lance le jeu Ariane
	*
	* @param args arguments saisis la ligne de commande, non utilis&#233;es ici
	*
	* @see Ariane
	*
	*/
	public static void main(String[] args){
		try{
			//crée un jeu dans une fenêtre 600x600
			IGame ariane = new Ariane(600,600);
			ariane.start(); //démarrage du jeu
		}catch(IllegalStateException e){
			//On est obligé d'afficher les exception datant d'
			//avant la création de la fenêtre/initialisation du jeu
			//sur le terminal car pas de fenêtre...

			//j'ai classé ces exceptions dans IllegalStateException
			//il s'agit donc exceptions "controlés" mais incontrolables
			System.err.println(e.getMessage());
		}

	}
}