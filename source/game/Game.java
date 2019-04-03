/**
*
* Partie principale du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.game;

import source.tools.Background;
import source.tools.Read;

public class Game{

	/** ecran sur lequel on affiche */
	private Background ecran;
	/** chemin d'un &#233;ventuel fichier &#224; charger */
	private String filepath;

	private int xPlayer;
	private int yPlayer;
	private int xOut;
	private int yOut;

	private int size;

	public Game(Background ecran, String path){
		this.ecran = ecran;
		this.filepath = path;
		this.xPlayer = 0;
		this.yPlayer = 0;
		this.xOut = 0;
		this.yOut = 0;
		this.size = 0;
	}

	public void run(){
		if(this.filepath.length() != 0){
			Read.update(this);
		}
		System.out.println("taille : "+this.size+"*"+this.size);
		System.out.println(this.xPlayer+","+this.yPlayer);
		System.out.println(this.xOut+","+this.yOut);
	}

	public void setXPlayer(int value){
		this.xPlayer = value;
	}

	public void setYPlayer(int value){
		this.yPlayer = value;
	}

	public void setXOut(int value){
		this.xOut = value;
	}

	public void setYOut(int value){
		this.yOut = value;
	}

	public void setSize(int value){
		this.size = value;
	}

	public int getSize(){
		return this.size;
	}

	public String getFilePath(){
		return this.filepath;
	}
}