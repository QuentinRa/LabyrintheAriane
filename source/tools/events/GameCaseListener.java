package source.tools.events;

import source.tools.events.IconListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
*
* Observateur des cases du jeu
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameCaseListener implements ActionListener{

	/** l'observateur qui nous donne l'icone avec laquelle remplir
	la case */
	private IconListener icone;
	/** le bouton associ&#233; actuellement au joueur */
	private JButton player;
	/** le bouton associ&#233; actuellement &#224; la sortie */
	private JButton exit;

	/**
	*
	* Cr&#233;e un observateur des cases du jeu
	*
	* @param icone un observateur qui renvoi
	* ({@link IconListener#getIconPath}) le lien vers une icone
	*
	*/
	public GameCaseListener(IconListener icone){
		this.icone = icone;
		this.player = null;
		this.exit = null;
	}

	/**
	*
	* Invoque lorsque une action est effectu&#233;e sur un &#233;l&#233;ment
	* associ&#233;. Rempli automatiquement.
	*
	* @param evenement l'object de l'&#233;v&#233;nement sous la forme d'un
	* ActionEvent
	*
	* @see ActionEvent
	*
	*/
	@Override
	public void actionPerformed(ActionEvent evenement){
		//récupère le bouton
		JButton boutonAppuye = (JButton) evenement.getSource();
		//Récupère le chemin de l'icone du JButton
		String path = icone.getIconPath();
		
		//Si pas de chemin
		if(path.equals(""))
			boutonAppuye.setIcon(null); //retire l'icone
		else{
			//Regarde si on veut placer/déplacer le joueur
			//donc on le supprime de son ancien emplacement
			//ou si le button clique est celui du joueur (ancien du coup), alors
			//on supprime l'association du joueur avec ce bouton
			if(path.contains("player") || boutonAppuye.equals(this.player)){
				if(this.player != null){
					this.player.setIcon(null); //on supprime joueur
				}
				this.player = boutonAppuye; //conserve le bouton joueur courant
				//Si on remplace le bouton joueur pas autre chose que le joueur
				if(!path.contains("player")){
					this.player = null;
				}
			}
			//Meme chose
			if(path.contains("exit") || boutonAppuye.equals(this.exit)){
				if(this.exit != null){
					this.exit.setIcon(null); //on supprime joueur
				}
				this.exit = boutonAppuye; //conserve le bouton joueur courant
				//Si on remplace le bouton chest pas autre chose que le chest
				if(!path.contains("exit")){
					this.exit = null;
				}
			}
			//On place l'image
			boutonAppuye.setIcon(new ImageIcon(path));
		}
	}
}