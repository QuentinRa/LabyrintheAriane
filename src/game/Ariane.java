package game;

import engine.window.Window;
import engine.exceptions.WindowException;
import game.utils.Game;

/**
*
* Le jeu Ariane, on (joueur) se trouve &#224; un certain endroit dans un espace (grille)
* remplie d'obstacles (murs) et on doit arriver &#224; la sortie (coffre).
*
* @version 1.0 1 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Ariane extends Game {

	/**
	 *
	 * Fen&#234;tre associ&#233;e au jeu
	 *
	 */
	private Window window;

	/**
	 *
	 * Cr&#233;e le jeu Ariane
	 *
	 * @param width largeur de la fen&#234;tre du jeu
	 * @param height hauteur de la fen&#234;tre du jeu
	 * @param title titre de la fen&#234;tre du jeu
	 *
	 * @throws WindowException si la cr&#233;ation de la fen&#234;tre
	 *
	 */
	public Ariane(int width, int height, String title){
		this.window = new Window(width,height,title);
	}


	/**
	 *
	 * Pr&#233;paration des ressources du jeu
	 *
	 * @throws WindowException si le chargement du fond d'&#233;cran &#233;choue
	 *
	 */
	@Override
	protected void init() {
		this.window.setLocation(Window.ALIGN_CENTER);
		this.window.setBackground("../ressources/bg.jpg");
		//Affiche la fenêtre
		this.window.setVisible(true);
	}

	@Override
	protected void gameLoop() {
		//Lance le jeu (menu)
		Menu menu = new Menu(this.window);
		menu.setSavePath("../ressources/sav/");
		menu.run();
	}

	@Override
	protected void dispose() {
		//libération de la fenêtre
		//this.window.dispose();
	}

	/**
	 *
	 * retourne la fen&#234;tre associ&#233;e au jeu
	 *
	 * @return fen&#234;tre associ&#233;e au jeu
	 *
	 */
	public Window getWindow(){
		return this.window;
	}
}