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
public class Case implements ActionListener{

	/**
	*
	* ...
	*
	*/
	public Case(){
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

		System.out.println("1");
	}
}