package game.evenements;

import game.utils.CreateGame;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Observateur des boutons de s&#233;lection
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class CreateGameFillButtonsListener implements ActionListener {


	/**
	 *
	 * Cr&#233;ateur du jeu
	 *
	 */
	private CreateGame createGame;
	/**
	*
	* Se souvient du dernier bouton cliqué
	*
	*/
	private JButton last;

	/**
	 *
	 * Cr&#233;e un observateur des boutons de s&#233;lection
	 *
	 * @param createGame gestionnaire de cr&#233;ation du jeu
	 *
	 */
	public CreateGameFillButtonsListener(CreateGame createGame){
		this.createGame = createGame;
		this.last = null;
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
		JButton fillButton = (JButton)event.getSource();
		if(fillButton.getIcon() != null) {
			//Récupération de l'icone
			this.createGame.setSelectedIcon(new ImageIcon(fillButton.getIcon().toString()));
		} else {
			//Si c'est effacer
			this.createGame.setSelectedIcon(null);
		}
		
		if(fillButton.getBackground().equals(Color.RED)){
			//reset bg
			fillButton.setBackground(new JButton().getBackground());
		} else {
			fillButton.setBackground(new Color(255,0,0,125));
			/* on reset bg ancien */
			if(last != null) this.last.setBackground(new JButton().getBackground());
			this.last = fillButton;
		}
	}
}

