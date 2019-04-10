package source.tools.events;

import source.game.GameCore;
import source.tools.Grille;
import source.tools.Window;
import source.tools.exceptions.InvalidDataException;
import source.tools.events.MessagePopup;
import source.tools.utils.WriteLoader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.regex.Pattern;

/**
*
* Observateur des boutons fonctionnels du jeu : créations
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameCreate implements ActionListener{

	/** Le jeu */
	private GameCore game;
	/** La grille */
	private Grille grille;

	/**
	*
	* Cr&#233;e un observateur  des boutons fonctionnels du jeu
	*
	* @param game le jeu
	* @param grille la grille du jeu
	*
	*/
	public GameCreate(Grille grille, GameCore game){
		this.game = game; //ce sur quoi on affiche
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
		String commande = evenement.getActionCommand();

		//Si la taille n'est pas initilialisé, alors c'est forcément la taille
		//qui a étée entrée
		if(this.grille.getSize() == 0){
			try{
				this.grille.setSize(Integer.parseInt(commande));
				//Supression des composants = reset
				this.game.getWindow().removeAll();
				this.game.getWindow().repaint();
				//Charge la méthode pour créer la map
				this.game.newGame();
			}catch(NumberFormatException e){
				//On vide l'étiquette @recommencez !
				JTextField champ = (JTextField) evenement.getSource();
				champ.setText("");
				champ.setBackground(Color.RED);
			}catch(InvalidDataException e){
				//On vide l'étiquette @recommencez !
				JTextField champ = (JTextField) evenement.getSource();
				champ.setText("");
				MessagePopup popup = new MessagePopup(this.game.getWindow(),e.getMessage());
			}
		//Sinon il y a jouer, deterministe, manuel, automatique, random
		} else if(commande.equals("Jouer")){
			if(this.grille.check()){
				this.game.setGame();
			} else {
				MessagePopup popup;
				String message = "La grille n'est pas correcte";
				popup = new MessagePopup(this.game.getWindow(),message);
			}
		}else if(commande.equals("Re-générer")){
			this.game.newGame();
		//ou la sauvegarde
		} else if(commande.equals("Deterministe")||commande.equals("Aléatoire")){
			if(commande.equals("Deterministe"))
				this.game.setType(true);
			//clean
			this.game.getWindow().removeAll();
			this.game.getWindow().repaint();
			this.game.getWindow().setLayout(new GridBagLayout());
			GridBagConstraints bag = new GridBagConstraints();
			
			//affiche boutons aléatoire et déterministe
			JPanel choix = new JPanel();
			JButton manuel = new JButton("Manuel");
			JButton automatique = new JButton("Automatique");
			choix.setLayout(new GridLayout(2,1));
			choix.add(manuel);
			choix.add(automatique);
			manuel.addActionListener(this); //observateur
			automatique.addActionListener(this);
			this.game.getWindow().add(choix,bag);
			this.game.getWindow().revalidate();

		} else if(commande.equals("Manuel") || commande.equals("Automatique")){
			//Active automatique
			if(commande.equals("Automatique"))
				this.game.setMode(true);
			//Lance le jeu
			this.game.getWindow().removeAll();
			this.game.getWindow().repaint();
			this.game.gameStart();
			
		}//ou la sauvegarde
		else {
			this.game.setFilePath(commande);
			//On vide l'étiquette @recommencez !
			JTextField champ = (JTextField) evenement.getSource();
			champ.setText("");
			String message = "";
			if(this.grille.check()){
				message = "Sauvegardé : ressources/sav/"+commande+".lab";
				this.game.setFilePath("ressources/sav/"+commande+".lab");
				WriteLoader.writeSave(this.grille,this.game.getFilePath(),
					this.game.getWindow());
				//Supression des composants = reset
				this.game.getWindow().removeAll();
				this.game.getWindow().repaint();
				this.game.run(); //relance le jeu avec sauvegarde
			} else {
				message = "Grille incorrecte. Non Sauvegardé";
			}
			MessagePopup popup = new MessagePopup(this.game.getWindow(),message);
		}
	}
}