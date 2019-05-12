package engine.graph;

import engine.exceptions.InvalidDataException;
//import engine.graph.Case;

import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * Grille du jeu
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class Grille {

	/**
	 *
	 * Position colonne x du joueur
	 *
	 */
	private int xPlayer;
	/**
	 *
	 * Position ligne x du joueur
	 *
	 */
	private int yPlayer;
	/**
	 *
	 * Position colonne x de la sortie
	 *
	 */
	private int xExit;
	/**
	 *
	 * Position ligne y de la sortie
	 *
	 */
	private int yExit;
	/**
	 *
	 * dimension de la grille = size * size
	 *
	 */
	private int size;
	/**
	 *
	 * Les cases de la grille
	 *
	 */
	private Case[][] cases;

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
	 *
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
	 * @param value nouvelle colonne o&#249; se trouve la sortie.
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
	 * @param value nouvelle ligne o&#249; se trouve la sortie.
	 * Compteur commence &#224; z&#233;ro
	 * Donner -1 pour retirer la sortie de la grille.
	 *
	 * @throws InvalidDataException si value n'a pas une valeur correcte
	 *
	 */
	public void setYExit(int value){
		if(value<-1 || value >= this.size ){
			String message = "La valeur y (sortie) n'est pas dans la grille";
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
	 *
	 * @param size la largeur/hauteur de la grille.
	 * Elle est strictement sup&#233;rieure &#224; 1.
	 *
	 * @throws InvalidDataException si size n'a pas une valeur correcte
	 *
	 */
	public void setSize(int size){
		if(size <= 1){
			String message = "taille doit être strictement supérieure à 1!";
			throw new InvalidDataException(message);
		}
		if(size > 100){
			String message = "taille max 100*100";
			throw new InvalidDataException(message);
		}
		this.size = size;
	}

	/**
	 *
	 * Renvoi le tableau des valeurs des cases.
	 *
	 * @return null si aucun tableau, sinon le tableau des cases de la grille.
	 *
	 * @see #setSize
	 * @see #setCasesArray
	 */
	public Case[][] getCasesArray(){
		return this.cases;
	}

	/**
	 *
	 * Assigne un nouveau tableau de cases.
	 *
	 * @param cases le nouveau tableau des valeurs des cases
	 *
	 */
	public void setCasesArray(Case[][] cases){
		this.cases = cases;
	}

	/**
	 *
	 * Change la valeur &#224; la position x,y de la grille
	 *
	 * @param x colonne de la valeur &#224; changer
	 * @param y ligne de la valeur &#224; changer
	 * @param value la valeur avec laquelle changer
	 *
	 * @throws InvalidDataException si la position n'est pas valide
	 * @throws InvalidDataException si le tableau n'existe pas.
	 *
	 */
	public void setCaseValue(int x, int y, boolean value){
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
	 * Renvoi la valeur &#224; la position x,y de la grille
	 *
	 * @param x colonne de la valeur &#224; renvoyer
	 * @param y ligne de la valeur &#224; renvoyer
	 *
	 * @return la valeur de la case x,y. true pour pleine, false pour vide.
	 *
	 * @throws InvalidDataException si la position n'est pas valide
	 * @throws InvalidDataException si le tableau n'existe pas.
	 *
	 */
	public boolean getCaseValue(int x, int y) {
		try {
			return this.cases[x][y].getValue();
		} catch (IndexOutOfBoundsException e) {
			String message = "La position x,y n'est pas valide dans le tableau.";
			throw new InvalidDataException(message);
		} catch (NullPointerException e) {
			String message = "La tableau n'existe pas.";
			throw new InvalidDataException(message);
		}
	}

	/**
	 *
	 * Renvoi la case &#224; la position x,y de la grille.
	 *
	 * @param x colonne de la valeur &#224; renvoyer
	 * @param y ligne de la valeur &#224; renvoyer
	 *
	 * @return la valeur de la case x,y. true pour pleine, false pour vide.
	 *
	 * @throws InvalidDataException si la position n'est pas valide
	 * @throws InvalidDataException si le tableau n'existe pas.
	 *
	 */
	public Case getCase(int x, int y){
		try{
			return this.cases[x][y];
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
	 * M&#233;thode qui d&#233;place le joueur.
	 *
	 * @param xPos d&#233;placement horizontal
	 * @param yPos d&#233;placement vertical
	 *
	 * @return true si le joueur est arriv&#233; &#224;  la sortie apr&#232;s d&#233;placement
	 *
	 */
	public boolean movePlayer(int xPos, int yPos){
		//dans la grille ?
		int x = this.xPlayer+xPos;
		int y = this.yPlayer+yPos;
		if(x==xExit && y==yExit){
			//bouge pas mais préviens que c'est la sortie
			return true;
		}
		//Si on est dans la map
		if(x>=0 && y>=0 && x<size && y<size){
			//si elle est vide
			if(!cases[x][y].getValue()){
				//vide l'ancienne position
				cases[this.xPlayer][this.yPlayer].setIcon(null);
				cases[this.xPlayer][this.yPlayer].revalidate();
				cases[this.xPlayer][this.yPlayer].repaint();
				//remplir la nouvelle
				cases[x][y].setIcon(Case.PLAYER);
				cases[x][y].revalidate();
				cases[x][y].repaint();
				this.xPlayer = x;
				this.yPlayer = y;
			}
			//Sinon on bouge pas
		}
		//Sinon on est pas dans la map, on bouge pas

		//retourne que c'est pas la sortie
		return false;
	}

	/**
	 *
	 * Cr&#233;e la grille de cases
	 *
	 */
	public void createCasesArray(){
		this.cases = new Case[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				Case element = new Case();
				element.setPosition(j, i);
				this.cases[i][j] = element;
			}
		}
	}

	/**
	 *
	 * G&#233;n&#232;re une map remplie al&#233;atoirement
	 *
	 */
	public Case[][] generate(){
		//Création d'une map s'il n'en existe pas.
		if(this.cases == null) {
			this.createCasesArray();
		}

		//Génération du joueur et de la sortie
		Random rand = new Random();
		this.xPlayer = rand.nextInt(this.size);
		this.yPlayer = rand.nextInt(this.size);
		//tant que le joueur et la sortie sont confondus
		do{
			this.xExit = rand.nextInt(this.size);
			this.yExit = rand.nextInt(this.size);
		}while(this.xPlayer == this.xExit && this.yPlayer == this.yExit);

		//Génération de la map
		int min = rand.nextInt(this.size*this.size*10); //un nombre de générations minimum, variable
		if(min<this.size) min = this.size; //pas de nombre trop bas
		int indice = 0; //le nombre actuel de générations

		while(indice<min){
			int x,y;
			//Génère une case différente du joueur et de la sortie
			do{
				x = rand.nextInt(this.size);
				y = rand.nextInt(this.size);
			}while((x==this.xPlayer && y==this.yPlayer) || (x==this.xExit && y==this.yExit));
			this.cases[x][y].setValue(rand.nextBoolean());
			indice++;
		}

		return this.cases;
	}

	/**
	 *
	 * Vérifie que la grille est correcte
	 *
	 */
	public boolean check(){
		return this.xPlayer != -1 && this.yPlayer != -1 && this.xExit != -1 && this.yExit != -1;
	}

	/**
	 *
	 * Ajoute un action listener &#224; toutes les cases.
	 *
	 * @param actionListener un observateur des actions pour chaque case.
	 *
	 */
	public void allAddListener(ActionListener actionListener){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				cases[i][j].addActionListener(actionListener);
			}
		}
	}

	/**
	 *
	 * Supprime tous les listeners de la grille
	 *
	 */
	public void removeAllListeners(){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				cases[i][j].removeActionListener(null);
			}
		}
	}
}
