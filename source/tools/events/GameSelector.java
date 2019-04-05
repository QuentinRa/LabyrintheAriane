package source.tools.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameSelector implements ActionListener{

	/**
	*
	* ...
	*
	*/
	public GameSelector(){
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
		//String commande = evenement.getActionCommand();

		System.out.println(evenement.getSource());
	}
}