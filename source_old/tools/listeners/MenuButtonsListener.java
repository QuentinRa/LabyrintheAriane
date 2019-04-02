/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools.listeners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import source.tools.streams.DrawImage;

public class MenuButtonsListener implements ActionListener{

	private JPanel ecran;

	public MenuButtonsListener(JPanel ecran){
		this.ecran = ecran;
	}

	@Override
	public void actionPerformed(ActionEvent evenement){
		String bouton = evenement.getActionCommand();

		if(bouton.equals("Charger")){
			DrawImage fond = new DrawImage("ressources/rem.png");
			this.ecran.add(new JLabel("aa"));
			this.ecran.repaint();
		}
	}
}