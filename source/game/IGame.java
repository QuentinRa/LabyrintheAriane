/**
*
* Format type d'un jeu
*
* @version 1.0 13 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.game;

public interface IGame{

	/** préparation des ressources du jeu */
	void init();
	
	/** Lancer le jeu */
	void start();

	/** boucle du jeu */
	void gameLoop();

	/** libération des ressources du jeu */
	void dispose();
}