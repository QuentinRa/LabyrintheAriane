package source.game;

import source.game.interfaces.IGameComponent;
import source.game.GameCore;
import source.tools.Window;
import source.tools.events.MenuButtons;
import source.tools.exceptions.ErrorPopup;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

/**
*
* Le menu du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Menu implements IGameComponent{
	
	/** &#233;cran sur lequel on affiche */
	private Window ecran;
	/** observateur du menu */
	private MenuButtons menuListener;
	/** chemin des sauvegardes de base */
	private String savPath = "./ressources/sav/";

	/**
	*
	* Cr&#233;e et affiche le menu &#224; l'&#233;cran
	*
	* @param ecran l'&#233;cran sur lequel afficher le menu
	*
	*/
	public Menu(Window ecran){
		this.ecran = ecran;
		this.menuListener = new MenuButtons(this.ecran,this);
	}

	/**
	*
	* Lance le menu
	*
	*/
	@Override
	public void run(){
		// On cherche a créer deux panneaux et que au milieu de  chaque
		// panneau (redimensionnement inclus) les boutons se trouvent en
		// leur centre (vertical et horizontal)
		this.ecran.setLayout(new GridLayout(2,1));
		GridBagConstraints bag = new GridBagConstraints();

		// Panneau pour créer une nouvelle grille
		JPanel pNewGame = new JPanel();
		pNewGame.setLayout(new GridBagLayout());
		JButton newGame = new JButton("Nouveau");
		newGame.addActionListener(this.menuListener);
		pNewGame.add(newGame,bag);
		pNewGame.setOpaque(false);

		// Panneau pour charger une grille existante
		JPanel pCharger = new JPanel();
		pCharger.setLayout(new GridBagLayout());
		JButton charger = new JButton("Charger");
		charger.addActionListener(this.menuListener);
		pCharger.add(charger,bag);
		pCharger.setOpaque(false);

		//ajoute
		this.ecran.add(pNewGame);
		this.ecran.add(pCharger);

		//met à jour l'écran (fait automatiquement la 1ere fois)
		this.ecran.revalidate();
	}

	/**
	*
	* Lance le jeu
	*
	* @see GameCore
	*
	*/
	public void play(String path, boolean remplissage){
		IGameComponent game = new GameCore(this.ecran,path,remplissage);
		game.run();
	}


	/**
	*
	* Charge le gestionnaire de fichier si on veut charger une sauvegarde
	*
	*/
	public void load(){
		try{
			//Change pour layout qui prends tout l'écran
			this.ecran.setLayout(new BorderLayout());
			//Charge le gestionnaire de fichiers, dossier courant
			JFileChooser gestionnaireFichiers = new JFileChooser(this.savPath);
			this.ecran.add(gestionnaireFichiers, BorderLayout.CENTER);
			gestionnaireFichiers.addActionListener(this.menuListener);
			//met à jour l'écran
			this.ecran.revalidate();
		}catch(Exception e){
			//Affiche un panneau avec l'erreur, puis quittera
			String message = "Ouverture du gestionnaire de fichier à échoué";
			ErrorPopup popup = new ErrorPopup(this.ecran,message);
		}
	}

	/**
	*
	* Change le chemin o&#254; chercher les sauvegardes
	* 
	* @param path le nouveau chemin
	*
	*/
	public void setSavePath(String path){
		if(path.length() == 0)
			path = ".";
		this.savPath = path;
	}

	/**
	*
	* Renvoi le chemin o&#254; chercher les sauvegardes
	* 
	* @return le nouveau chemin o&#254; chercher les sauvegardes
	*
	*/
	public String getSavePath(){
		return this.savPath;
	}
}