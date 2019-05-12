package game.evenements;

import engine.graph.Case;
import engine.graph.Grille;
import game.utils.CreateGame;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Change l'&#233;tat des cases en fonction du bouton de remplissage
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 * @see CreateGame#getSelectedIcon()
 *
 */
public class CreateGameButtonsListener implements ActionListener {

	/**
	 *
	 * Cr&#233;ateur de grille
	 *
	 */
	private CreateGame createGame;
	/**
	 *
	 * Grille de jeu
	 *
	 */
	private Grille grille;

	/**
	 *
	 * Cr&#233;e un observateur qui change l'&#233;tat des cases en fonction du bouton de remplissage
	 *
	 * @param createGame le cr&#233;ateur du jeu
	 * @param grille la grille de jeu
	 *
	 *
	 */
	public CreateGameButtonsListener(CreateGame createGame, Grille grille){
		this.createGame = createGame;
		this.grille = grille;
	}

	/**
	 *
	 * Invoqu&#233; lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	 * associ&#233;. Remplie automatiquement.
	 *
	 * @param event l'object de l'&#233;v&#233;nement sous la forme d'un
	 * ActionEvent
	 *
	 * @see ActionEvent
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Case cases = (Case) event.getSource();
		ImageIcon icon = this.createGame.getSelectedIcon(); //Récupère icône pour changement.
		//remplace l'icône
		cases.setIcon(icon);

		//Récupération des valeurs.
		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();

		//Si on a mis une icône (supprimer = pas d'icône), on met à jour les données
		if(icon != null) {
			String path = icon.getDescription(); //chemin de l'icone
			//Si c'est le même que celui du joueur
			if(path.equals(Case.PLAYER.getDescription())){
				//Si le player est dans la grille, on commence par le supprimer
				if(xPlayer != -1 && yPlayer != -1){
					Case oldPlayer = this.grille.getCase(xPlayer,yPlayer);
					oldPlayer.setIcon(null);
				}
				//On affiche le nouveau joueur
				this.grille.setYPlayer(cases.getXPos());
				this.grille.setXPlayer(cases.getYPos());
				cases.setValue(false);//la case est libre (joueur...)
			}//Si c'est le même que celui de la sortie
			else if(path.equals(Case.EXIT.getDescription())) {
				//Si la sortie est dans la grille, on commence par le supprimer
				if (xExit != -1 && yExit != -1) {
					Case oldExit = this.grille.getCase(xExit, yExit);
					oldExit.setIcon(null);
				}
				//On affiche la nouvelle sortie
				this.grille.setYExit(cases.getXPos());
				this.grille.setXExit(cases.getYPos());
				cases.setValue(false);//la case est libre
			}//Sinon c'était le chemin du mur
			else {
				//case bloquée
				cases.setValue(true);
			}
		} else {
			//si a retiré l'icone mais que c'était la case du joueur, on le sort de la grille
			if(cases.getXPos() == yPlayer && cases.getYPos() == xPlayer){
				this.grille.setXPlayer(-1);
				this.grille.setYPlayer(-1);
				//si l'icone est a null et c'était la sortie
			}//si a retiré l'icone mais que c'était la case de la sortie, on la sort de la grille
			else if(cases.getXPos() == yExit && cases.getYPos() == xExit){
				this.grille.setXExit(-1);
				this.grille.setYExit(-1);
			}
			//La case est vide.
			cases.setValue(false);
		}
	}
}

