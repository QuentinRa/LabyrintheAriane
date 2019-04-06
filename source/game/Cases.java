package source.tools;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;

/**
*
* Une case de notre jeu
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Case extends JButton{

	private int x;
	private int y;
	private int width;
	private int height;
	private String icone;

	private boolean available;

	private boolean player;
	private boolean exit;

	public Case(int x, int y){