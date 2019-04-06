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

	private String icone;
	private boolean value;

	private boolean player;
	private boolean exit;

	private boolean empille;

	public Cases(int size){
		super();
		this.value = false;
		this.icone = "";
		this.player = false;
		this.exit = false;
		this.empille = false;

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

	public void setEmpile(boolean value){
		this.empille = value;
	}

	public boolean isEmpile(){
		return this.empille;
	}

}