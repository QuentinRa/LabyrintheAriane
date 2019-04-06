package source.game;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
*
* Une case de notre jeu
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Cases extends JButton{

	public static final ImageIcon PLAYER = new ImageIcon("ressources/player.png");
	public static final ImageIcon EXIT = new ImageIcon("ressources/exit.png");
	public static final ImageIcon WALL = new ImageIcon("ressources/wall.png");

	private boolean value;

	private boolean player;
	private boolean exit;

	private boolean empille;

	private int x;
	private int y;

	public Cases(int size, int x, int y){
		super();
		this.value = false;
		this.player = false;
		this.exit = false;
		this.empille = false;
		this.x = x;
		this.y = y;

		if(size<=0) size = 48; 

		Dimension dim = new Dimension(size,size);

		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		//images ne sont par resurlignés après clic
		this.setFocusable(false);
		this.setRolloverEnabled(false);
		this.setFocusPainted(false);
	}

	public void setValue(boolean value){
		this.value = value;
	}

	public boolean getValue(){
		return this.value;
	}

	public boolean isPlayer(){
		return this.player;
	}

	public boolean isExit(){
		return this.exit;
	}

	public void setPlayer(boolean value){
		this.player = value;
	}

	public void setExit(boolean value){
		this.exit = value;
	}

	public void setEmpille(boolean value){
		this.empille = value;
	}

	public boolean isEmpille(){
		return this.empille;
	}

	public int getXPos(){ return this.x; }
	public int getYPos(){ return this.y; }
	public void setXPos(int x){ this.x = x; }
	public void setYPos(int y){ this.y = y; }
}