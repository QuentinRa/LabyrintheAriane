package source.tools.events;

import source.tools.Background;
import source.game.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameButtonsListener implements ActionListener{

	/** */
	private Background ecran;
	/** */
	private Game game;


	/**
	*
	* ...
	*
	* @param ecran
	* @param game
	*
	*/
	public GameButtonsListener(Background ecran, Game game){
		this.ecran = ecran; //Ce sur quoi on affiche
		this.game = game; //le jeu
	}

	/**
	*
	* ...
	*
	* @param evenement
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){
		String commande = evenement.getActionCommand();

		//forcément si la taille est à zéro alors le premier
		//action listener est celui de la taille
		if(this.game.getSize() == 0){
			try{
				this.game.setSize(Integer.parseInt(commande));
				if(this.game.getSize()>0){
					//Supression des composants = reset
					this.ecran.removeAll();
					this.ecran.repaint();
					//Charge la méthode pour créer la map
					this.game.newGame();
				} else {
					//Simule l'erreur (pour éviter dupliquer code)
					throw new NumberFormatException();
				}
			}catch(NumberFormatException e){
				//On vide l'étiquette @recommencez !
				JTextField champ = (JTextField) evenement.getSource();
				champ.setText("");
			}
		} else if(commande.equals("Jouer")){
			//Supression des composants = reset
			this.ecran.removeAll();
			this.ecran.repaint();

			this.game.start();
		}
	}
}