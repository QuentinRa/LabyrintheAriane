package source.tools.events;

import source.tools.events.GameSelector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
*
* Observateur des boutons du menu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Case implements ActionListener{

	private GameSelector selector;
	private JButton player;

	/**
	*
	* ...
	*
	*/
	public Case(GameSelector selector){
		this.selector = selector;
		this.player = null;
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
		//récupère le bouton
		JButton boutonAppuye = (JButton) evenement.getSource();

		String path = selector.getSelectedFile();
		if(path.equals(""))
			boutonAppuye.setIcon(null); //retire l'icone
		else{
			if(path.contains("player")){
				//Si le joueur a déja été placé
				if(this.player != null){
					//on le supprime
					this.player.setIcon(null);
				}
				//Conserve le nouvel emplacement
				this.player = boutonAppuye;
			}
			if(path.contains("chest")){
				//Si le joueur a déja été placé
				if(this.chest != null){
					//on le supprime
					this.chest.setIcon(null);
				}
				//Conserve le nouvel emplacement
				this.chest = boutonAppuye;
			}
			//On place l'image
			boutonAppuye.setIcon(new ImageIcon(path));
		}
	}
}