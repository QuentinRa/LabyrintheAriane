package source.tools;

import source.tools.exceptions.InvalidDataException;

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

	/** Les icones associées aux nom */

	/** icone du joueur */
	public static final ImageIcon PLAYER = new ImageIcon("ressources/player.png");
	/** icone de la sortie */
	public static final ImageIcon EXIT = new ImageIcon("ressources/exit.png");
	/** icone d'un mur */
	public static final ImageIcon WALL = new ImageIcon("ressources/wall.png");
	/** taille d'une case */
	public static int caseSize = 48;

	/** La valeur d'une case : 0 = vide, 1 = remplie */
	private boolean value;
	/** Si on a empillé la case, cf graphes - dfs */
	private boolean empille;
	/** si on a parcouru la case **/
	private boolean parcourue;

	/** la position x de la case */
	private int x;
	/** la position x de la case */
	private int y;

	/**
	*
	* Crée un case de taille size, qui s'identifie comme étant à la position
	* x,y (colonne,ligne)
	*
	* @param size la taille d'une case, si valeur <=0 alors sera assignée à la
	* valeur de base (48*48)
	* @param x la colonne o&#254; se trouve la case
	* @param y la ligne o&#254; se trouve la case
	*
	*/
	public Cases(int size, int x, int y){
		super();
		this.value = false;
		this.empille = false;
		this.x = x;
		this.y = y;

		if(size<=0) size = 48; 

		Dimension dim = new Dimension(size,size);

		this.setPreferredSize(dim);//la taille d'une case (qui lui sera donnée)
		this.setFocusable(false); //images ne sont par resurlignés après clic
		this.setRolloverEnabled(false); //pas de hover
	}

	/**
	*
	* Change la valeur de la case qui dit si elle est vide ou pleine
	*
	* @param value true si cette case est pleine sinon false donc vide
	*
	* @see #getValue
	*
	*/
	public void setValue(boolean value){
		this.value = value;
	}

	/**
	*
	* Renvoi la valeur d'une case
	*
	* @return true si la case est pleine sinon false
	*
	* @see #isPlayer
	*
	*/
	public boolean getValue(){
		return this.value;
	}

	/**
	*
	* Permet de définir si une case &#224; &#224;t&#224;e empillée
	*
	* @param value true si la case &#224; &#233;t&#233;e empill&#233;e sinon false
	*
	*/
	public void setEmpille(boolean value){
		this.empille = value;
	}

	/**
	*
	* Permet de savoir si une case &#224; &#233;t&#233;e empill&#233;e
	*
	* @return true si la case est empill&#233;e sinon false
	*
	*/
	public boolean isEmpille(){
		return this.empille;
	}

	/**
	*
	* Renvoi la position x de la case
	*
	* @return la position x de la case
	*
	*/
	public int getXPos(){ return this.x; }

	/**
	*
	* Renvoi la position y de la case
	*
	* @return la position y de la case
	*
	*/
	public int getYPos(){ return this.y; }

	/**
	*
	* Change la position x de la case
	*
	* @param x la nouvelle position x de la case
	*
	* @throws InvalidDataException si la valeur est incorrecte
	*
	*/
	public void setXPos(int x){
		if(x<0){
			String message = "La valeur x pour la case est incorrecte";
			throw new InvalidDataException(message);
		}
		this.x = x;
	}

	/**
	*
	* Change la position y de la case
	*
	* @param y la nouvelle position y de la case
	*
	* @throws InvalidDataException si la valeur est incorrecte
	*
	*/
	public void setYPos(int y){
		if(y<0){
			String message = "La valeur y pour la case est incorrecte";
			throw new InvalidDataException(message);
		}
		this.y = y;
	}

	public void setParcourue(boolean value){
		this.parcourue = value;
	}
	public boolean isParcourue(){
		return this.parcourue;
	}
}