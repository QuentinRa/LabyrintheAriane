package source.tools;

import source.tools.events.Case;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

/**
*
* 
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class AreaGame extends JPanel{

	private int size;
	private final int xOffset = 100;
	private final int yOffset = 100;

	private Case caseJeu;

	public AreaGame(int size){
		super();
		this.size = size;
		this.caseJeu = new Case();
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

		secondPinceau.setColor(Color.BLACK);
		secondPinceau.drawRect(xOffset/2, yOffset/2,
				this.getWidth()-xOffset, this.getHeight()-yOffset);

		this.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				bag.gridx = j; //colonnes
				bag.gridy = i; //lignes
				JButton carre = new JButton();
				carre.setPreferredSize(new Dimension(48,48));
				if(j == 0 && i == 0)
					carre.setIcon(new ImageIcon("ressources/main.png"));
				if(j == 3 && i == 0)
					carre.setIcon(new ImageIcon("ressources/chest.png"));
				carre.addActionListener(this.caseJeu); //ajoute un observateur
				carre.setFocusable(false); //images ne sont par resurlignés après clic
				carre.setRolloverEnabled(false);
				carre.setFocusPainted(false);
				this.add(carre, bag);
			}
		}

		this.revalidate();
	}
}