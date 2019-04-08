package source.tools.events;

import source.tools.Window;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
*
* 
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class WinPopup extends JOptionPane{

	/**
	*
	* 
	*
	* @param ecran l'&#233;cran sur lequel afficher le popup
	* @param message le message &#224; afficher
	*
	*/

	public WinPopup(Window ecran, String message){
		super();
		JOptionPane.showMessageDialog(ecran,message,"Jeu terminé",
		JOptionPane.INFORMATION_MESSAGE,new ImageIcon("ressources/player.png"));
		
		ecran.revalidate();
		System.exit(0);
	}
}