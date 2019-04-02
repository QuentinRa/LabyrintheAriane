/**
*
* Le jeu Ariane, on se trouve à un certain endroit dans un espace remplit
* d'obstacle et on doit arriver à la sortie.
*
* @version 1.0 13 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.game;

import source.game.IGame;
import source.game.Menu;
import source.tools.Window;
import source.tools.Background;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.BorderLayout;

public class Ariane implements IGame{

	/** la fen&#234;tre associ&#233;e &#224; notre jeu */
	private Window window;
	/** panneau qui repr&#233;sente la fen&#234;tre */
	private Background ecran;

	/** construit un jeu "Ariane"
	* Dimensions du jeu = 600x600 (largeur*hauteur)
	*/
	public Ariane(){
		this.window = new Window();
		this.ecran = new Background("ressources/rem.png");
	}

	/** construit un jeu "Ariane"
	* Dimensions du jeu = width*height (largeur*hauteur)
	*
	*@param width largeur de la fen&#234;tre (strictement sup&#233;rieure &#224;
	* 0 ainsi que strictement sup&#233;rieure &#224; 1 si height=1)
	*@param height hauteur de la fen&#234;tre (strictement sup&#233;rieure &#224;
	* 0 ainsi que strictement sup&#233;rieure &#224; 1 si width=1)
	*/
	public Ariane(int width, int height){
		this.window = new Window(width,height,"Ariane Game");
		this.ecran = new Background("ressources/rem.png");
	}

	/** m&#233;thode appel&#234;e pour lancer le jeu */
	@Override
	public void start(){	
		this.init();
		this.gameLoop();
		this.dispose();
	}

	/** préparation des ressources du jeu */
	@Override
	public void init(){
		this.window.add(this.ecran, BorderLayout.CENTER);//ajoute le panneau à la fenêtre
		this.window.setVisible(true); //affiche fenêtre
	}

	/** boucle du jeu */
	@Override
	public void gameLoop(){
		Menu menu = new Menu(this.ecran);
	}

	/** libération des ressources du jeu */
	@Override
	public void dispose(){
	}
}