/**
*
* Le menu du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.game;

import source.tools.Window;
import source.tools.Write;
import source.tools.Background;
import source.tools.MenuButtonsListener;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

public class Menu{

	/** ecran sur lequel on affiche */
	private Background ecran;
	/** observateur du menu */
	private MenuButtonsListener menuListener;
	/** chemin d'un &#233;ventuel fichier &#224; charger */
	private String filePath;

	/**
	*
	* Créer et affiche le menu &#224; l'&#233;cran
	*
	* @param ecran l'&#233;cran sur lequel afficher le menu
	*
	*/
	public Menu(Background ecran){
		this.ecran = ecran;
		this.menuListener = new MenuButtonsListener(this.ecran,this);
	}

	/**
	*
	* Lance le menu
	*
	*/
	public void run(){
		this.ecran.setLayout(new GridLayout(2,1));
		GridBagConstraints bag = new GridBagConstraints();

		JPanel pNewGame = new JPanel();
		pNewGame.setLayout(new GridBagLayout());
		JButton newGame = new JButton("Nouveau");
		newGame.addActionListener(this.menuListener);
		pNewGame.add(newGame,bag);
		pNewGame.setOpaque(false);

		JPanel pCharger = new JPanel();
		pCharger.setLayout(new GridBagLayout());
		JButton charger = new JButton("Charger");
		charger.addActionListener(this.menuListener);
		pCharger.add(charger,bag);
		pCharger.setOpaque(false);

		this.ecran.add(pNewGame);
		this.ecran.add(pCharger);

		this.ecran.revalidate();//met à jour l'écran
	}

	/**
	*
	* Cr&#233;e un nouveau jeu
	*
	*/
	public void newGame(){
		System.out.println("Création du jeu");
		this.filePath = Write.createGame();
	}

	/**
	*
	* Charge le gestionnaire de fichier si on veut charger une sauvegarde
	* 
	* @see #getFilePath pour r&#233;cup&#233;rer le chemin d'un &#233;ventuel fichier s&#233;lectionn&#233;
	*
	*/
	public void load(){
		//Change pour layout qui prends tout l'écran
		this.ecran.setLayout(new BorderLayout());
		//Charge le gestionnaire de fichiers
		JFileChooser gestionnaireFichiers = new JFileChooser();
		this.ecran.add(gestionnaireFichiers, BorderLayout.CENTER);
		gestionnaireFichiers.addActionListener(this.menuListener);
		//met à jour l'écran
		this.ecran.revalidate();
	}

	/**
	*
	* Lance le jeu
	*
	* @see Game
	*
	*/
	public void play(){
		Game game = new Game(this.ecran, this.filePath);
		game.run();
	}

	/**
	*
	* setter du chemin du fichier s&#233;lectionn&#233; remplit par l'observateur
	*
	* @param path le nouveau chemin du fichier s&#233;lectionn&#233;
	*
	* @see MenuButtonsListener
	*/
	public void setFilePath(String path){
		this.filePath = path;
	}

	/**
	*
	* Renvoi le chemin du fichier s&#233;lectionn&#233; par le gestionnaire de fichiers
	*
	* @return le chemin du fichier s&#233;lectionn&#233;
	*
	*/
	public String getFilePath(){
		return this.filePath;
	}
}