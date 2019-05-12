package game.evenements;

import game.GameCore;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * Observateur des touches du clavier
 *
 * @version 1.0 1 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class KeyboardListener implements KeyListener{

	/**
	 *
	 * Jeu associ&#233; &#224; l'observateur
	 *
	 */
	private GameCore game;

	/**
	 *
	 * Cr&#233;e un observateur du clavier de la partie principale du jeu
	 *
	 * @param game la partie principale du jeu
	 *
	 */
	public KeyboardListener(GameCore game){
		this.game = game;
	}

	/**
	 *
	 * Appel&#233; automatique lorsqu'une touche est appuy&#233;e
	 *
	 * @param event des informations sur la touche
	 *
	 */
	@Override
	public void keyPressed(KeyEvent event){
		this.game.nextMove(); //prochain mouvement
	}

	/**
	 *
	 * Appel&#233; automatique lorsqu'une touche est relach&#233;e
	 *
	 * @param event des informations sur la touche
	 *
	 */
	@Override
	public void keyReleased(KeyEvent event){
	}

	/**
	 *
	 * Appel&#233; automatique lorsqu'une touche est typed
	 *
	 * @param event des informations sur la touche
	 *
	 */
	@Override
	public void keyTyped(KeyEvent event){
	}

}