package source.tools;

import source.tools.Grille;
import source.tools.Cases;
import source.tools.Window;
import source.tools.events.CasesListener;
import source.tools.exceptions.ErrorPopup;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
//https://www.logicbig.com/tutorials/java-swing/layered-pane-example.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html

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
	private CasesListener casesListener;

	//taille d'une case
	private static final int caseSize = 48;

	/**
	*
	* 
	*
	*/
	public DrawGrille(Grille grille, Window ecran, boolean withListener){
		super();
		this.grille = grille;
		this.ecran = ecran;
		if(withListener)
			this.casesListener = new CasesListener(this.grille);
		else
			this.casesListener = null;
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

		int widthC = grille.getSize()*this.caseSize+100;
		int heightC = grille.getSize()*this.caseSize+100;
	
		//Si elle sont plus grandes que la fenêtre mais moins que l'écran
		if(widthC > this.ecran.getWidth() || heightC>this.ecran.getHeight()){
			this.ecran.setSize(widthC,heightC); //augmente sa taille
			this.ecran.setLocation(Window.CENTER); //centre par rapport à l'écran
		}

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0, 0));//#transparent :)
		//#setOpaque fait tout disparaitre #bidouillage #echec

		panel.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();

		int width = grille.getSize();
		int height = grille.getSize();

		Cases[][] cases = this.grille.getCasesArray();

		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				bag.gridx = j; //colonnes
				bag.gridy = i; //lignes
				boolean value = cases[i][j].getValue();
				cases[i][j].setIcon(value == true?Cases.WALL:null);
				if(xPlayer == j && yPlayer == i)
					cases[i][j].setIcon(Cases.PLAYER);
				else if(xExit == j && yExit == i)
					cases[i][j].setIcon(Cases.EXIT);
				if(this.casesListener!=null)
					cases[i][j].addActionListener(this.casesListener);
				panel.add(cases[i][j], bag);
			}
		}

		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.revalidate();
	}
}