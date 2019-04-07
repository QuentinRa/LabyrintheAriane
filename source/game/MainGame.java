package source.game;

import source.game.Grille;
import source.game.Cases;
import source.game.interfaces.IGameComponent;
import source.tools.Window;
import source.tools.DrawGrille;
import source.tools.Graphes;
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
			this.grille.setSize(5);
		}

		// Affiche le vrai jeu
		DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran);
		this.ecran.add(drawGrille);
		drawGrille.setOpaque(false);
		this.ecran.revalidate();

		//Crée un graphe avec la grille
		Graphes graph = new Graphes(this.grille);

		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();

		//Trouve un chemin dans le graph entre joueur et sortie
		boolean b = graph.findPath(xPlayer,yPlayer,xExit,yExit);

		//Affiche résultat
		if(b){
			System.out.println("Il existe un chemin !");
		}
		else{
			System.out.println("Il n'existe pas de chemin");
			System.exit(0);
		}

		//Renvoi le plus court chemin.
		double plusCourtChemin = graph.getSorthestPath(xPlayer,yPlayer,xExit,yExit);
		System.out.println("Le plus court chemin fait :"+plusCourtChemin);
	}
}