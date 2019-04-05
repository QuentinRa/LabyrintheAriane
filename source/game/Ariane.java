package source.game;

import source.game.Menu;
import source.game.interfaces.IGame;
import source.game.interfaces.IGameComponent;
import source.tools.Window;

/**
*
* Le jeu Ariane, on se trouve à un certain endroit dans un espace (grille) remplie
* d'obstacles et on doit arriver à la sortie.
*
* @version 1.0 1 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Ariane implements IGame{

	/** la fen&#234;tre associ&#233;e &#224; notre jeu */
	private Window window;
	/** le menu du jeu */
	private IGameComponent menu;

	/**
	*
	* construit un jeu "Ariane"
	* Dimensions du jeu = 600x600 (largeur*hauteur)
	*
	* @throws IllegalStateException si le chargement du fond d'&#233;cran
	* &#233;choue
	*
	*/
	public Ariane(){
		this.window = new Window();
		this.window.setBackground("ressources/rem.png");
		this.menu = new Menu(this.window);
		this.window.setVisible(true); //affiche fenêtre
	}

	/**
	*
	* construit un jeu "Ariane"
	* Dimensions du jeu = width*height
	*
	* @param width largeur de la fen&#234;tre (strictement sup&#233;rieure &#224;
	*  0 ainsi que strictement sup&#233;rieure &#224; 1 si height=1)
	* @param height hauteur de la fen&#234;tre (strictement sup&#233;rieure &#224;
	*  0 ainsi que strictement sup&#233;rieure &#224; 1 si width=1)
	*
	* @throws IllegalStateException si le chargement du fond d'&#233;cran
	* &#233;choue
	*
	*/
	public Ariane(int width, int height){
		this.window = new Window(width,height,"Ariane Game");
		this.window.setBackground("ressources/rem.png");
		this.menu = new Menu(this.window);
	}

	/**
	*
	* m&#233;thode appel&#234;e pour lancer le jeu
	*
	*/
	@Override
	public void start(){
		this.init();
		this.gameLoop();
		this.dispose();
	}

	/**
	*
	* préparation des ressources du jeu
	*
	*/
	@Override
	public void init(){
		this.window.setVisible(true); //affiche fenêtre
	}

	/**
	*
	* boucle du jeu
	*
	*/
	@Override
	public void gameLoop(){
		this.menu.run(); //lance le menu
	}

	/**
	*
	* libération des ressources du jeu
	*
	*/
	@Override
	public void dispose(){
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