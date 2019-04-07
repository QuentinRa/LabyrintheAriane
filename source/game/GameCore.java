package source.game;

import source.game.interfaces.IGameComponent;
import source.tools.Grille;
import source.tools.Cases;
import source.tools.Window;
import source.tools.DrawGrille;
import source.tools.events.GameCreate;
//import source.tools.Graphes;
//import source.tools.utils.SaveLoader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
*
* Partie principale du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class GameCore implements IGameComponent{

	/** ecran sur lequel on affiche */
	private Window ecran;
	/** chemin d'un &#233;ventuel fichier &#224; charger */
	private String filePath;
	/** la grille de jeu */
	private Grille grille;

	/**
	*
	* Cr&#233;e la c&#339;ur du jeu (algorithmes...)
	*
	* @param ecran ou afficher le jeu
	* @param filePath chemin d'une éventuelle sauvegarde
	*
	*/
	public GameCore(Window ecran, String filePath){
		this.ecran = ecran;
		this.filePath = filePath;
		this.grille = null;
	}

	/**
	*
	* Lance le jeu
	*
	*/
	@Override
	public void run(){
		// 2 possibilités : 
		//		1 - je charge un jeu existant
		//		2 - je crée un nouveau jeu

		//si on m'a donné un chemin
		if(this.filePath.length() != 0){
			//this.grille = SaveLoader.getSave(this.filePath,this.ecran);
		} else {
			this.grille = new Grille();
			this.ecran.setLayout(new GridBagLayout());
			GridBagConstraints bag = new GridBagConstraints();

			GameCreate gameCreateListener = new GameCreate(this.grille,this);

			JPanel menu = new JPanel();
			JTextField tailleGrille = new JTextField();
			tailleGrille.addActionListener(gameCreateListener);
			menu.setLayout(new GridLayout(2,1));
			menu.add(new JLabel("Taille de la grille"));
			menu.add(tailleGrille);
			this.ecran.add(menu,bag);
			this.ecran.revalidate();
		}
	}

	public void newGame(){
		this.ecran.setLayout(new BorderLayout());

		GameCreate gameCreateListener = new GameCreate(this.grille,this);
		//IconListener selectorListener = new IconListener();

		//Bouton jouer
		JPanel play = new JPanel();
		JButton boutonPlay = new JButton("Jouer");
		boutonPlay.addActionListener(gameCreateListener);
		play.add(boutonPlay);
		play.setOpaque(false);
		
		//Panneau avec options sauvegarde
		JPanel espaceSauvegarde = new JPanel();
		espaceSauvegarde.setLayout(new GridLayout(1,2));
		//Phrase sauvegarde
		JLabel sav = new JLabel("Sauvegarder sous : ");
		sav.setHorizontalAlignment(JLabel.CENTER);
		//Champ de texte de l'emplacement
		JTextField nomSav = new JTextField();
		nomSav.addActionListener(gameCreateListener);
		espaceSauvegarde.add(sav);
		espaceSauvegarde.add(nomSav);

		//Panneau avec boutons pour remplir la grille
		JPanel boutonsChoix = new JPanel();
		boutonsChoix.setLayout(new GridLayout(4,1));
		//1- Boutons
		JButton wall = new JButton(Cases.WALL);
		JButton clear = new JButton("effacer");
		JButton player = new JButton(Cases.PLAYER);
		JButton exit = new JButton(Cases.EXIT);
		//2- Leurs observateurs
		//
		//3-Ajout à la fenêtre
		boutonsChoix.add(wall);
		boutonsChoix.add(player);
		boutonsChoix.add(exit);
		boutonsChoix.add(clear);


		//Ajouts à la fenêtre des 4 composantes
		this.ecran.add(play, BorderLayout.NORTH);
		this.ecran.add(espaceSauvegarde, BorderLayout.SOUTH);
		this.ecran.add(boutonsChoix, BorderLayout.WEST);
		DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran);
		this.ecran.add(drawGrille,BorderLayout.CENTER);

		//met à jour l'écran
		this.ecran.revalidate();
	}

	public void gameStart(){

	}

	public Window getWindow(){
		return this.ecran;
	}

	public void setFilePath(String path){
		this.filePath = path;
	}

	public String getFilePath(){
		return this.filePath;
	}
}