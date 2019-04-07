package source.tools;

import source.tools.Cases;
import source.tools.exceptions.InvalidDataException;

/**
*
* Grille du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Grille{

	/** Position colonne x du joueur */
	private int xPlayer;
	/** Position ligne x du joueur */
	private int yPlayer;
	/** Position colonne x de la sortie */
	private int xExit;
	/** Position ligne y de la sortie */
	private int yExit;
	/** dimension de la grille = size * size */
	private int size;
	/** nos cases */
	private Cases[][] cases;

	public Grille(){
		this.xPlayer = -1;
		this.yPlayer = -1;
		this.xExit = -1;
		this.yExit = -1;
		this.size = 0;
		this.cases = null;
	}

	/**
	*
	* Renvoi la position x (colonne) du joueur
	*
	* @return colonne o&#249; se trouve le joueur.
	* Compteur commence &#224; z&#233;ro
	*
	*/
	public int getXPlayer(){
		return this.xPlayer;
	}
	
	/**
	*
	* Change la position x (colonne) du joueur
	*
	* @param value nouvelle colonne o&#249; se trouve le joueur.
	* Compteur commence &#224; z&#233;ro.
	* Donner -1 pour retirer le joueur de la grille.
	*
	* @throws InvalidDataException si value n'a pas une valeur correcte
	*
	*/
	public void setXPlayer(int value){
		if(value<-1 || value >= this.size ){
			String message = "La valeur x (joueur) n'est pas dans la grille";
			throw new InvalidDataException(message);
		} else if(value == -1){
			this.yPlayer = -1;
			this.xPlayer = -1;
		} else {
			this.xPlayer = value;
		}
	}

	/**
	*
	* Renvoi la position y (ligne) du joueur
	*
	* @return ligne o&#249; se trouve le joueur.
	* Compteur commence &#224; z&#233;ro
	*
	*/
	public int getYPlayer(){
		return this.yPlayer;
	}

	/**
	*
	* Change la position y (ligne) du joueur
	*
	* @param value nouvelle ligne o&#249; se trouve le joueur.
	* Compteur commence &#224; z&#233;ro.
	* Donner -1 pour retirer le joueur de la grille.
	*
	* @throws InvalidDataException si value n'a pas une valeur correcte
	*
	*/
	public void setYPlayer(int value){
		if(value<-1 || value >= this.size ){
			String message = "La valeur y (joueur) n'est pas dans la grille";
			throw new InvalidDataException(message);
		} else if(value == -1){
			this.yPlayer = -1;
			this.xPlayer = -1;
		} else {
			this.yPlayer = value;
		}
	}
	
	/**
	*
	* Renvoi la position x (colonne) de la sortie
	*
	* @return colonne o&#249; se trouve la sortie.
	* Compteur commence &#224; z&#233;ro
	*
	*/
	public int getXExit(){
		return this.xExit;
	}

	/**
	*
	* Change la position x (colonne) de la sortie
	*
	* @return colonne o&#249; se trouve la sortie.
	* Compteur commence &#224; z&#233;ro
	* Donner -1 pour retirer la sortie de la grille.
	*
	* @throws InvalidDataException si value n'a pas une valeur correcte
	*
	*/
	public void setXExit(int value){
		if(value<-1 || value >= this.size ){
			String message = "La valeur x (sortie) n'est pas dans la grille";
			throw new InvalidDataException(message);
		} else if(value == -1){
			this.yExit = -1;
			this.xExit = -1;
		} else {
			this.xExit = value;
		}
	}

	/**
	*
	* Renvoi la position y (ligne) de la sortie
	*
	* @return ligne o&#249; se trouve la sortie.
	* Compteur commence &#224; z&#233;ro
	*
	*/
	public int getYExit(){
		return this.yExit;
	}
	
	/**
	*
	* Change la position y (ligne) de la sortie
	*
	* @return ligne o&#249; se trouve la sortie.
	* Compteur commence &#224; z&#233;ro
	* Donner -1 pour retirer la sortie de la grille.
	*
	* @throws InvalidDataException si value n'a pas une valeur correcte
	*
	*/
	public void setYExit(int value){
		if(value<0 || value >= this.size ){
			String message = "La valeur x (sortie) n'est pas dans la grille";
			throw new InvalidDataException(message);
		}else if(value == -1){
			this.yExit = -1;
			this.xExit = -1;
		} else {
			this.yExit = value;
		}
	}
	
	/**
	*
	* Renvoi la largeur/hauteur de la grille (elle est carr&#233;e).
	* Dimension = size * size;
	*
	* @return Renvoi la largeur/hauteur de la grille
	*
	*/
	public int getSize(){
		return this.size;
	}

	/**
	*
	* Change la largeur/hauteur de la grille (elle est carr&#233;e).
	* Dimension = size * size;
	* Crée automatiquement un tableau de cases. {@link #getCasesArray}
	*
	* @param size la largeur/hauteur de la grille
	* Elle est strictement sup&#233;rieure &#224; 1
	*
	* @throws InvalidDataException si size n'a pas une valeur correcte
	*
	*/
	public void setSize(int size){
		if(size <= 1){
			String message = "size strictement supérieure à 1!";
			throw new InvalidDataException(message);
		}
		this.size = size;
		this.cases = new Cases[this.size][this.size];
		for(int i=0; i< this.size; i++){
			for(int j=0; j< this.size; j++){
				this.cases[i][j] = new Cases(48,j,i);
			}
		}
	}

	/**
	*
	* Renvoi le tableau des valeurs des cases.
	*
	* @return null si {@link #setSize} n'a pas &#233;t&#233;e appel&#233;e,
	* sinon un tableau de Cases initialisés à false de taille [size][size]
	*
	* @see #setSize
	* @see #setCasesArray
	*/
	public Cases[][] getCasesArray(){
		return this.cases;
	}

	/**
	*
	* Change le tableau des valeurs des cases.
	*
	* @param cases le nouveau tableau des valeurs des cases
	*
	*/
	public void setCasesArray(Cases[][] cases){
		this.cases = cases;
	}

	/**
	*
	* Change la valeur à la position x,y de cases avec valeur.
	*
	* @param x colonne de la valeur à changer
	* @param y ligne de la valeur à changer
	* @param value la valeur avec laquelle changer
	*
	* @throws InvalidDataException si la position n'est pas valide ou le
	* tableau n'existe pas.
	* 
	*/
	public void setCasesArray(int x, int y, boolean value){
		try{
			this.cases[x][y].setValue(value);
		}catch(IndexOutOfBoundsException e){
			String message = "La position x,y n'est pas valide dans le tableau.";
			throw new InvalidDataException(message);
		}catch(NullPointerException e){
			String message = "La tableau n'existe pas.";
			throw new InvalidDataException(message);
		}
	}

	/**
	*
	* Renvoi la valeur &#224; la position x,y de cases avec valeur.
	*
	* @param x colonne de la valeur &#224; renvoyer
	* @param y ligne de la valeur &#224; renvoyer
	*
	* @return la valeur de la case x,y. true pour pleine, false pour vide.
	*
	* @throws InvalidDataException si la position n'est pas valide ou le
	* tableau n'existe pas.
	* 
	*/
	public boolean getCasesArray(int x, int y){
		try{
			return this.cases[x][y].getValue();
		}catch(IndexOutOfBoundsException e){
			String message = "La position x,y n'est pas valide dans le tableau.";
			throw new InvalidDataException(message);
		}catch(NullPointerException e){
			String message = "La tableau n'existe pas.";
			throw new InvalidDataException(message);
		}
	}

	public boolean check(){
		return false;
	}
}