package source.tools.events;

import source.tools.Grille;
import source.tools.Cases;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

/**
*
* Observateur des bouton de selection du motif de remplissage
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class CasesListener implements ActionListener{

	/** La grille */
	private Grille grille;

	/**
	*
	*
	*
	* @param grille la grille du jeu
	*
	*/
	public CasesListener(Grille grille){
		this.grille = grille; //le menu du jeu
	}

	/**
	*
	* Invoqu&#233; lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	* associ&#233;. Remplie automatiquement.
	*
	* @param evenement l'object de l'&#233;v&#233;nement sous la forme d'un
	* ActionEvent
	*
	* @see ActionEvent
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){
		Cases cases = (Cases) evenement.getSource();
		ImageIcon icone = this.grille.getImageIcon();
		//cliquer sur l'icone supprime/change son icone
		cases.setIcon(icone);

		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();

		if(icone != null){
			String path = icone.getDescription();
			if(path.equals(Cases.PLAYER.getDescription())){
				//Si le player est dans la grille, on le déplace
				if(xPlayer != -1 && yPlayer != -1){
					Cases oldPlayer = this.grille.getCase(yPlayer,xPlayer);
					oldPlayer.setIcon(null);
				}
				this.grille.setXPlayer(cases.getXPos());
				this.grille.setYPlayer(cases.getYPos());
				cases.setValue(false);
			} else
			if(path.equals(Cases.EXIT.getDescription())){
				//Si la sortie est dans la grille, on la déplace
				if(xExit != -1 && yExit != -1){
					Cases oldExit = this.grille.getCase(yExit,xExit);
					oldExit.setIcon(null);
				}
				this.grille.setXExit(cases.getXPos());
				this.grille.setYExit(cases.getYPos());
				cases.setValue(false);
			} else {
				//alors on dessine un mur
				cases.setValue(true);
			}
		} else{
			//si l'icone est a null et c'était la case du joueur
			if(cases.getXPos() == xPlayer && cases.getYPos() == yPlayer){
				//si on
				this.grille.setXPlayer(-1);
				this.grille.setYPlayer(-1);
			//si l'icone est a null et c'était la sortie
			} else if(cases.getXPos() == xExit && cases.getYPos() == yExit){
				this.grille.setXExit(-1);
				this.grille.setYExit(-1);
			}
			cases.setValue(false);
		}
	}
}