package source.tools.events;

import source.game.GameCore;

import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener{

	public KeyboardListener(){

	}

	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("suivant");
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	}
	
	@Override
	public void keyTyped(KeyEvent e){
	}

}