package engine.exceptions;

import engine.window.Window;

import javax.swing.JOptionPane;

/**
 *
 * L&#232;ve un popup qui affiche un message
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
	 * @param screen l'&#233;cran sur lequel afficher le popup
	 * @param message le message &#224; afficher
	 *
	 */

	public MessagePopup(Window screen, String message){
		super();
		JOptionPane.showMessageDialog(screen,
				message,"Notification",JOptionPane.INFORMATION_MESSAGE);
		screen.revalidate();
	}
}