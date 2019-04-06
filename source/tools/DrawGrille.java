package source.tools;

import source.game.Grille;
import source.tools.Window;
import source.tools.exceptions.InvalidDataException;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Dimension;
import javax.swing.JButton;

import java.awt.*;
import javax.swing.*;

/**
*
* Classe qui g&#232;re les opération li&#233;s à la fen&#234;tre
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class DrawGrille extends JPanel{

	private Grille grille;
	private Window ecran;

	//taille d'une case
	private static final int caseSize = 48;

	/**
	*
	* 
	*
	*/
	public DrawGrille(Grille grille, Window ecran){
		super();
		this.grille = grille;
		this.ecran = ecran;
	}

	@Override
	public void paintComponent(Graphics pinceau){
		// on crée un nouveau pinceau pour pouvoir le modifier plus tard
		Graphics secondPinceau = pinceau.create();
		// si le composant n'est pas censé être transparent
		if (this.isOpaque()){
			// on repeint toute la surface avec la couleur de fond
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		int width = grille.getSize()*this.caseSize;
		int height = grille.getSize()*this.caseSize;
	
		//Si les dimensions de notre jeu sont plus grande que l'écran
		if(this.ecran.getMonitorWidth() < width ||
		   this.ecran.getMonitorHeight() < height ){
			String message = "La fenetre ne permet pas un jeu de cette taille";
			throw new InvalidDataException(message);
		}
		//Si elle sont plus grandes que la fenêtre mais moins que l'écran
		else if(width > this.ecran.getWidth() || height>this.ecran.getHeight()){
			this.ecran.setSize(width,height); //augmente sa taille
			this.ecran.setLocation(Window.CENTER); //centre par rapport à l'écran
		}

		this.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();

		Dimension dim = new Dimension(this.caseSize,this.caseSize);

		width/=this.caseSize;
		height/=this.caseSize;
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				bag.gridx = j; //colonnes
				bag.gridy = i; //lignes
				JButton carre = new JButton(""+i);
				carre.setPreferredSize(dim);
				carre.setMinimumSize(dim);
				//images ne sont par resurlignés après clic
				carre.setFocusable(false);
				carre.setRolloverEnabled(false);
				carre.setFocusPainted(false);
				this.add(carre, bag);
			}
		}

		this.revalidate();
	}
}