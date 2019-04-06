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

		System.out.println(i+" "+j);

		if(j-1>=0 && cases[i][j-1].isEmpile() == false
				&& cases[i][j-1].getValue() == false){
			System.out.println("gauche ok");
			cases[i][j].setEmpile(true);
			j--;
		}
		if(j+1<this.grille.getSize() && cases[i][j+1].isEmpile() == false
				&& cases[i][j+1].getValue() == false){
			System.out.println("droite ok");
			cases[i][j].setEmpile(true);
			j++;

		}

		if(i-1>=0 && cases[i-1][i].isEmpile() == false
			&& cases[i-1][j].getValue() == false){
			System.out.println("haut ok");
			cases[i][j].setEmpile(true);
			i--;
		}

		if(i+1<this.grille.getSize() && cases[i+1][j].isEmpile() == false
			&& cases[i+1][j].getValue() == false){
			cases[i][j].setEmpile(true);
			i++;
			System.out.println("bas ok");
		}
	}
}