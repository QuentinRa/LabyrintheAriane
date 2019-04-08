package source.tools.events;

import source.game.GameCore;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardListener implements KeyListener{

	private GameCore game;

	public KeyboardListener(GameCore game){
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e){
		this.game.nextMove();
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	}
	
	@Override
	public void keyTyped(KeyEvent e){
	}

}