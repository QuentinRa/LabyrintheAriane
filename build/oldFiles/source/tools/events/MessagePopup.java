package source.tools.events;

import source.tools.Window;

import javax.swing.JOptionPane;

/**
*
* Lève un popup qui affiche un message
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class MessagePopup extends JOptionPane{

	/**
	*
	* Affiche un message
	*
	* @param ecran l'&#233;cran sur lequel afficher le popup
	* @param message le message &#224; afficher
	*
	*/

	public MessagePopup(Window ecran, String message){
		super();
		JOptionPane.showMessageDialog(ecran,
		message,"Notification",JOptionPane.INFORMATION_MESSAGE);
		ecran.revalidate();
	}
}