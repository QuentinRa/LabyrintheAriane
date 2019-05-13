package source.game.interfaces;

/**
*
* Format type d'un jeu
*
* @version 1.0 1 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

public interface IGame{

	/** pr&#233;paration des ressources du jeu */
	void init();
	
	/** Lancer le jeu */
	void start();

	/** boucle du jeu */
	void gameLoop();

	/** lib&#233;ration des ressources du jeu */
	void dispose();
}