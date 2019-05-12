package source.tools.exceptions;

import source.tools.Window;

import javax.swing.JOptionPane;

/**
*
* Lève un popup d'erreur et quitte le programme
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class ErrorPopup extends JOptionPane{

	/**
	*
	* Vide l'&#233;cran et affiche un popup avec le message d'erreur
	* blocant puis quitte le programme
	*
	* @param ecran l'&#233;cran sur lequel afficher le popup
	* @param message le message &#224; afficher
	*
	*/

	public ErrorPopup(Window ecran, String message){
		super();
		ecran.removeAll();
		this.setOpaque(false);
		JOptionPane.showMessageDialog(ecran,
		message,"Erreur",JOptionPane.ERROR_MESSAGE);
		ecran.revalidate();
		System.exit(0);
	}
}