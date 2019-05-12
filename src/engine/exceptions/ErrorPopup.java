package engine.exceptions;

import engine.window.Window;

import javax.swing.JOptionPane;

/**
 *
 * L&#232;ve un popup d'erreur et quitte le programme
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class ErrorPopup extends JOptionPane{

	/**
	 *
	 * Vide l'&#233;cran et affiche un popup avec le message d'erreur
	 * blocant puis quitte le programme (+ferme fen&#234;tre)
	 *
	 * @param screen l'&#233;cran sur lequel afficher le popup
	 * @param message le message &#224; afficher
	 *
	 */
	public ErrorPopup(Window screen, String message){
		super();
		//vide l'écran
		screen.removeAll();
		this.setOpaque(false);
		//Affiche popup
		JOptionPane.showMessageDialog(screen,message,"Erreur",JOptionPane.ERROR_MESSAGE);
		screen.revalidate();
		//quitte
		screen.dispose(); //Fermeture
		System.exit(0);
	}
}