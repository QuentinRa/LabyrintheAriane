package source.game;

import source.game.Grille;
import source.game.Cases;
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

import java.util.Queue;
import java.util.LinkedList;

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
			this.grille = SaveLoader.getSave(this.filePath,this.ecran);
		} else {
			this.grille = new Grille();
		}

		// Affiche le vrai jeu
		DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran);
		this.ecran.add(drawGrille);
		drawGrille.setOpaque(false);
		this.ecran.revalidate();

		Cases[][] cases = this.grille.getCasesArray();

		int i = this.grille.getXPlayer();
		int j = this.grille.getYPlayer();
		int butx = this.grille.getXExit();
		int buty = this.grille.getYExit();
		int size = this.grille.getSize();

		int a = 0;

		Queue<Cases> file = new LinkedList<>();
		file.add(cases[i][j]);
		
		while(file.isEmpty() == false){
			Cases first = file.element(); //Récupère le 1er élément
			first.setEmpille(true);
			i = first.getXPos();
			j = first.getYPos();
			System.out.println(i+" "+j);

			if(i == butx && j == buty){
				//On a trouvé un chemin
				System.out.println("victory");
				file.clear();
			}else{
				//chemin gauche ok
				if(j-1>=0 && !cases[i][j-1].getValue() && !cases[i][j-1].isEmpille())
					file.add(cases[i][j-1]);
				//chemin droite
				if(j+1<size && !cases[i][j+1].getValue() && !cases[i][j+1].isEmpille())
					file.add(cases[i][j+1]);
				//chemin haut
				if(i-1>=0 && !cases[i-1][j].getValue() && !cases[i-1][j].isEmpille())
					file.add(cases[i-1][j]);
				//chemin bas
				if(i+1<size && !cases[i+1][j].getValue() && !cases[i+1][j].isEmpille())
					file.add(cases[i+1][j]);

				file.remove(); //supprime le 1er élément
			}
		}

		//boolean[][] matrice = new boolean[size][size];

		for(i=0; i < size; i++){
			for(j=0; j < size; j++){
				System.out.print(cases[i][j].getValue()==true?0:1);
			}
			System.out.println("");
		}
	}
}