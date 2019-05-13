package source.tools.utils;

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
	public ErrorPopup(Window ecran, String message){
		super();
		ecran.removeAll();
		JOptionPane.showMessageDialog(ecran,
		message,"Erreur",JOptionPane.ERROR_MESSAGE);
		ecran.revalidate();
		System.exit(0);
	}
}