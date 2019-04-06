package source.game;

import source.game.Grille;
import source.game.interfaces.IGameComponent;
import source.tools.Window;
import source.tools.DrawGrille;
import source.tools.utils.SaveLoader;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
*
* Partie principale du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class MainGame implements IGameComponent{

	/** ecran sur lequel on affiche */
	private Window ecran;
	/** chemin d'un &#233;ventuel fichier &#224; charger */
	private String filePath;
	/** la grille de jeu */
	private Grille grille;

	public MainGame(Window ecran, String filePath){
		this.ecran = ecran;
		this.filePath = filePath;
		this.grille = null;
	}

	/**
	*
	* Lance le jeu
	*
	*/
	@Override
	public void run(){
		// 2 possibilités : 
		//		1 - je charge un jeu existant
		//		2 - je crée un nouveau jeu

		//si on m'a donné un chemin
		if(this.filePath.length() != 0){
			this.grille = SaveLoader.getSave(this.filePath);
		} else {
			this.grille = new Grille();
		}

		//Affiche à des fins de débogage

		System.out.println("taille : "+this.grille.getSize()+"*"+
			this.grille.getSize());
		System.out.println("j: "+this.grille.getXPlayer()+","+
			this.grille.getYPlayer());
		System.out.println("o: "+this.grille.getXOut()+","+this.grille.getYOut());

		int size = this.grille.getSize();
		boolean[][] array = this.grille.getCasesArray();

		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				System.out.print(array[i][j] == true?1:0);
			}
			System.out.println();
		}

		DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran);
		this.ecran.add(drawGrille);
		drawGrille.setOpaque(false);
		this.ecran.revalidate();
	}
}