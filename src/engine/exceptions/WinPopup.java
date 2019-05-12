package engine.exceptions;

import engine.window.Window;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * Popup de message de victoire
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class WinPopup extends JOptionPane{

	/**
	 *
	 * Cr&#233;e un popup levant un message de victoire, qui bloque le programme.
	 *
	 * @param screen l'&#233;cran sur lequel afficher le popup
	 * @param message le message &#224; afficher
	 *
	 */

	public WinPopup(Window screen, String message, ImageIcon icon){
		super();
		JOptionPane.showMessageDialog(screen,message,"Jeu terminé",
				JOptionPane.INFORMATION_MESSAGE,icon);
		screen.revalidate();
	}
}