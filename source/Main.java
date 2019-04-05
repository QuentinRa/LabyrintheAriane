package source;

import source.game.Ariane;
import source.game.interfaces.IGame;

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
			IGame ariane = new Ariane(600,600); //dimensions fenêtre
			ariane.start(); //démarrage du jeu
		}catch(Exception e){
			//S'il y a une exception, on l'affiche simplement sur error
			System.err.println(e.getMessage());
		}
	}
}