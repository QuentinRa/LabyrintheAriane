package engine.graph;

//import engine.graph.Grille;
//import engine.graph.Case;
//import engine.window.Window;

//import engine.graph.Case;
//import engine.graph.Grille;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;

/**
 *
 * Affiche une grille &#224; l'&#233;cran
 *
 * @version 1.0 5 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class DrawGrille extends JPanel {

	/**
	 *
	 * La grille &#224; afficher
	 *
	 */
	private Grille grille;

	/**
	 *
	 * Cr&#233;e un panneau dans lequel la grille sera dessin&#233;e
	 *
	 * @param grille la grille &#224; dessiner
	 *
	 */
	public DrawGrille(Grille grille){
		this.grille = grille;
	}

	/**
	 *
	 * M&#233;thode appel&#233; lors du dessin d'un composant
	 *
	 * @param pinceau le pinceau qui sert &#224; dessiner le composant
	 *
	 */
	@Override
	public void paintComponent(Graphics pinceau) {
		// on crée un nouveau pinceau pour pouvoir le modifier plus tard
		Graphics secondPinceau = pinceau.create();
		// si le composant n'est pas censé être transparent
		if (this.isOpaque()) {
			// on repeint toute la surface avec la couleur de fond
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		this.removeAll();

		this.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();

		int width = grille.getSize();
		int height = grille.getSize();

		Case[][] cases = this.grille.getCasesArray();

		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();

		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				bag.gridx = j; //colonnes
				bag.gridy = i; //lignes
				//Récupération de la valeur
				boolean value = cases[j][i].getValue();
				//transformation en mur (selon valeur)
				cases[j][i].setIcon(value?Case.WALL:null);
				//ou en joueur
				if(xPlayer == j && yPlayer == i) {
					cases[j][i].setIcon(Case.PLAYER);
				} //ou en sortie
				else if(xExit == j && yExit == i) {
					cases[j][i].setIcon(Case.EXIT);
				}
				//ajout
				this.add(cases[j][i],bag);
			}
		}

		this.revalidate();

	}
}
