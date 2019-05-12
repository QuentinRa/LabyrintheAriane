package engine.graph;

import engine.exceptions.InvalidDataException;

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
public class Case extends JButton {

	/* Les icônes associées aux noms des cases */

	/**
	 *
	 * ic&#244;ne du joueur
	 *
	 */
	public static final ImageIcon PLAYER = new ImageIcon("ressources/player.png");
	/**
	 *
	 * ic&#244;ne de la sortie
	 *
	 */
	public static final ImageIcon EXIT = new ImageIcon("ressources/exit.png");
	/**
	 *
	 *  ic&#244;ne d'un mur
	 *
	 */
	public static final ImageIcon WALL = new ImageIcon("ressources/wall.png");

	/**
	 *
	 *  taille d'une case
	 *
	 */
	private static int caseSize = 48;


	/**
	 *
	 * La valeur d'une case : 0 = vide, 1 = remplie
	 *
	 */
	private boolean value;
	/**
	 *
	 * Si on a empil&#233; la case, cf graphes - dfs
	 *
	 */
	private boolean empile;
	/**
	 *
	 * si on a parcouru la case
	 *
	 */
	private boolean parcourue;
	/**
	 *
	 * la position x de la case
	 *
	 */
	private int x;
	/**
	 *
	 * la position y de la case
	 *
	 */
	private int y;


	/**
	 *
	 * Cr&#233;e une case
	 *
	 */
	public Case(){
		super();
		this.value = false;
		this.empile = false;
		this.x = 0;
		this.y = 0;

		Dimension dim = new Dimension(Case.caseSize,Case.caseSize);

		this.setPreferredSize(dim);//la taille d'une case (qui lui sera donnée)
		this.setFocusable(false); //images ne sont par re-surlignés après clic
		this.setRolloverEnabled(false); //pas de hover
	}

	/**
	 *
	 * D&#233;finir la position de la case
	 *
	 * @param x colonne de la case
	 * @param y ligne de la case
	 *
	 * @throws InvalidDataException si l'une des positions est incorrecte
	 *
	 */
	public void setPosition(int x, int y){
		if(x < 0 || y < 0){
			String message = "Coordonnées de la case invalide.";
			throw new InvalidDataException(message);
		}
		this.x = x;
		this.y = y;
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
	 */
	public boolean getValue(){
		return this.value;
	}

	/**
	 *
	 * Permet de d&#233;finir si une case &#224; &#233;t&#233;e empil&#233;e
	 *
	 * @param value true si la case &#224; &#233;t&#233;e empil&#233;e sinon false
	 *
	 */
	public void setEmpile(boolean value){
		this.empile = value;
	}

	/**
	 *
	 * Permet de savoir si une case &#224; &#233;t&#233;e empil&#233;e
	 *
	 * @return true si la case n'est empil&#233;e sinon false
	 *
	 */
	public boolean isNotEmpile(){
		return !this.empile;
	}

	/**
	 *
	 * Permet de d&#233;finir si une case &#224; &#233;t&#233;e parcourue
	 *
	 * @param value true si la case &#224; &#233;t&#233;e parcourue sinon false
	 *
	 */
	public void setParcourue(boolean value){
		this.parcourue = value;
	}

	/**
	 *
	 * Permet de savoir si une case &#224; &#233;t&#233;e parcourue
	 *
	 * @return true si la case est parcourue sinon false
	 *
	 */
	public boolean isParcourue(){
		return this.parcourue;
	}

	/**
	 *
	 * Renvoi la taille d'une case
	 *
	 * @return la taille d'une case
	 *
	 */
	public static int getCaseSize() {
		return Case.caseSize;
	}

	/**
	 *
	 * Change la taille d'une case
	 *
	 * @param caseSize la nouvelle taille (&#155;0)
	 *
	 */
	public static void setCaseSize(int caseSize) {
		if(caseSize < 0) caseSize = 48;
		Case.caseSize = caseSize;
	}
}