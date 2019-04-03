package source;

import source.game.IGame;
import source.game.Ariane;

/**
*
* Lancement du jeu
*
* @version 1.0 1 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

public class Main{
	/**
	* méthode qui lance le jeu
	*
	*@param args arguments saisis la ligne de commande, non utilis&#233;es ici
	*
	*/
	public static void main(String[] args){
		try{
			IGame ariane = new Ariane(600,600); //dimensions fenêtre
			ariane.start(); //démarrage du jeu
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}