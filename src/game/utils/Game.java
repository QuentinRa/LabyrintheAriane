package game.utils;

import engine.exceptions.WindowException;

/**
 *
 * Format type d'un jeu
 *
 * @version 1.0 4 mai 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public abstract class Game{

	/**
	 *
	 * Lancer le jeu
	 *
	 */
	public void start(){
		try {
			this.init();
			this.gameLoop();
		} catch (WindowException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * Pr&#233;paration des ressources du jeu
	 *
	 */
	protected abstract void init();

	/**
	 *  boucle du jeu
	 */
	protected abstract void gameLoop();

	/**
	 *  lib&#233;ration des ressources du jeu
	 */
	protected abstract void dispose();
}