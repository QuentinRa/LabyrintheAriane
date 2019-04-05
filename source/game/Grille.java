package source.game;

/**
*
* 
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Grille{

	private int xPlayer; //Position colonne x du joueur
	private int yPlayer; //Position ligne x du joueur
	private int xOut; //Position colonne x de la sortie
	private int yOut; //Position ligne y de la sortie
	private int size; //dimension de la grille = size * size

	private boolean[][] cases;

	public Grille(){
		this.xPlayer = 0;
		this.yPlayer = 0;
		this.xOut = 0;
		this.yOut = 0;
		this.size = 0;
		this.cases = null;
	}

	public int getXPlayer(){
		return this.xPlayer;
	}

	public void setXPlayer(int value){
		this.xPlayer = value;
	}

	public int getYPlayer(){
		return this.yPlayer;
	}

	public void setYPlayer(int value){
		this.yPlayer = value;
	}
	
	public int getXOut(){
		return this.xOut;
	}

	public void setXOut(int value){
		this.xOut = value;
	}

	public int getYOut(){
		return this.yOut;
	}
	
	public void setYOut(int value){
		this.yOut = value;
	}
	
	public int getSize(){
		return this.size;
	}

	public void setSize(int value){
		this.size = value;
		this.cases = new boolean[this.size][this.size];
	}

	public boolean[][] getCasesArray(){
		return this.cases;
	}

	public void setCasesArray(boolean[][] cases){
		this.cases = cases;
	}
}