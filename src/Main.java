import game.Ariane; //Jeu
import game.utils.Game; //jeu type

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
		//crée un jeu dans une fenêtre 600x600
		Game ariane = new Ariane(600, 600, "Ariane");
		ariane.start(); //démarrage du jeu
	}
}