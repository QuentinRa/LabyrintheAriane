/**
*
* Lancement du jeu
*
* @version 1.0 13 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source;

import source.game.IGame;
import source.game.Ariane;

public class Main{
	/**
	* méthode qui lance le jeu
	*
	*@param args arguments saisis la ligne de commande
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