package source.tools.events;

import source.tools.Background;
import source.game.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
*
* Observateur des boutons jeu (jouer, sauvegarder, tailleGrille)
*
* @version 1.0 4 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameButtonsListener implements ActionListener{

	/** ecran sur lequel les boutons sont affich&#233;s */
	private Background ecran;
	/** le jeu */
	private Game game;


	/**
	*
	* Cr&#233;e un observateur des boutons du jeu
	*
	* @param ecran ecran sur lequel les boutons sont affich&#233;s
	* @param game le jeu ({@link Game#getSize}{@link Game#setSize})
	*
	* @see Game
	*
	*/
	public GameButtonsListener(Background ecran, Game game){
		this.ecran = ecran;
		this.game = game;
	}

	/**
	*
	* Invoque lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	* associ&#233;. Rempli automatiquement.
	*
	* @param evenement l'object de l'&#233;v&#233;nement sous la forme d'un
	* ActionEvent
	*
	* @see ActionEvent
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){
		String commande = evenement.getActionCommand();

		//forcément si la taille est à zéro alors le premier
		//action listener est celui de la taille car elle est
		//nécessaire pour créer et donc utiliser le reste du menu
		if(this.game.getSize() == 0){
			try{
				this.game.setSize(Integer.parseInt(commande));
				if(this.game.getSize()>1){ //grille 1x1 : entités confondus
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
				this.game.setSize(0); //au cas ou l'entier était bon pour parseInt
				//Vide la champ de texte
				JTextField champ = (JTextField) evenement.getSource();
				champ.setText("");
			}
		} else if(commande.equals("Jouer")){
			//Supression des composants = reset
			this.ecran.removeAll();
			this.ecran.repaint();
			//Lance le jeu
			this.game.start();
		} else {
			//Vide la champ de texte
			JTextField champ = (JTextField) evenement.getSource();
			champ.setText("");
			//Sinon c'est que l'on a fait sauvegarder
			String message = "Fichier sauvegardé à : ressources/sav/"+commande;
			JOptionPane.showMessageDialog(this.ecran,message);
			//Ici on sauvegarde
			//SaveGame save = new SaveGame(this.game);
			//save.write();
		}
	}
}