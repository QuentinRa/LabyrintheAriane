package source.game;

import source.game.Menu;
import source.game.interfaces.IGame;
import source.game.interfaces.IGameComponent;
import source.tools.Window;

/**
*
* Le jeu Ariane, on se trouve &#224; un certain endroit dans un espace (grille)
* remplie d'obstacles et on doit arriver &#224; la sortie.
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
	/** chemin du fond d'écran de base */
	private static final String background = "ressources/rem.png";

	/**
	*
	* construit un jeu "Ariane"
	* Dimensions du jeu = 600x600 (largeur*hauteur)
	*
	* @throws IllegalStateException si le chargement du fond d'&#233;cran
	* &#233;choue, ou la cr&#233;ation de la fen&#234;tre
	*
	*/
	public Ariane(){
		this.window = new Window(); //fenêtre
		this.window.setBackground(this.background); //fond
		this.menu = new Menu(this.window); //menu
	}

	/**
	*
	* construit un jeu "Ariane"
	* Dimensions du jeu = width*height
	*
	* @param width largeur de la fen&#234;tre (strictement sup&#233;rieure
	* &#224; 0 ainsi que strictement sup&#233;rieure &#224; 1 si height=1)
	* @param height hauteur de la fen&#234;tre (strictement sup&#233;rieure
	* &#224; 0 ainsi que strictement sup&#233;rieure &#224; 1 si width=1)
	*
	* @throws IllegalStateException si le chargement du fond d'&#233;cran
	* &#233;choue ou la cr&#233;ation de la fen&#234;tre
	*
	*/
	public Ariane(int width, int height){
		this.window = new Window(width,height,"Ariane");
		this.window.setBackground(this.background);
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
	* pr&#233;paration des ressources du jeu
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
	* lib&#233;ration des ressources du jeu
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