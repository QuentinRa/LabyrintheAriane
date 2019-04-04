package source.game;

import source.tools.events.GameButtonsListener;
import source.tools.Background;
import source.tools.AreaGame;
import source.tools.utils.Read;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
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
public class Game{

	/** ecran sur lequel on affiche */
	private Background ecran;
	/** chemin d'un &#233;ventuel fichier &#224; charger */
	private String filepath;
	/** observateur des boutons du menu */
	private GameButtonsListener gameListener;

	private int xPlayer;
	private int yPlayer;
	private int xOut;
	private int yOut;

	private int size;

	public Game(Background ecran, String path){
		this.ecran = ecran;
		this.filepath = path;
		this.gameListener = new GameButtonsListener(ecran,this);
		this.xPlayer = 0;
		this.yPlayer = 0;
		this.xOut = 0;
		this.yOut = 0;
		this.size = 0;
	}

	public void run(){
		if(this.filepath.length() != 0){
			Read.update(this);

			System.out.println("taille : "+this.size+"*"+this.size);
			System.out.println(this.xPlayer+","+this.yPlayer);
			System.out.println(this.xOut+","+this.yOut);
		} else {
			this.ecran.setLayout(new GridBagLayout());
			GridBagConstraints bag = new GridBagConstraints();

			JPanel menu = new JPanel();
			JTextField tailleGrille = new JTextField();
			tailleGrille.addActionListener(this.gameListener);
			menu.setLayout(new GridLayout(2,1));
			menu.add(new JLabel("Taille de la grille"));
			menu.add(tailleGrille);
			this.ecran.add(menu,bag);
			this.ecran.revalidate();
		}
	}

	/**
	*
	* Cr&#233;e un nouveau jeu si l'utilisateur &#224; choisi "Nouveau"
	*
	*/
	public void newGame(){
		System.out.println("taille : "+this.size+"*"+this.size);
		System.out.println(this.xPlayer+","+this.yPlayer);
		System.out.println(this.xOut+","+this.yOut);

		// On veut créer au centre la zone, en bas un bouton sauvegarder
		// en haut bouton jouer
		this.ecran.setLayout(new BorderLayout());

		//Bouton jouer
		JPanel playP = new JPanel();
		JButton play = new JButton("Jouer");
		play.addActionListener(this.gameListener);
		playP.add(play);
		playP.setOpaque(false);
		
		//Panneau avec options sauvegarde
		JPanel panneauSauvegarde = new JPanel();
		panneauSauvegarde.setLayout(new GridLayout(1,2));
		JLabel sav = new JLabel("Sauvegarder sous : ");
		sav.setHorizontalAlignment(JLabel.CENTER);
		panneauSauvegarde.add(sav);
		JTextField nomSav = new JTextField();
		nomSav.addActionListener(this.gameListener);
		panneauSauvegarde.add(nomSav);
		
		//Ajouts à la fenêtre
		this.ecran.add(playP, BorderLayout.NORTH);
		this.ecran.add(panneauSauvegarde, BorderLayout.SOUTH);

		AreaGame area = new AreaGame(5);
		area.setOpaque(false);
		this.ecran.add(area, BorderLayout.CENTER);
		
		//met à jour l'écran
		this.ecran.revalidate();
	}

	public void start(){
		System.out.println("Demarrage du Jeu");
	}

	public void setXPlayer(int value){ this.xPlayer = value; }

	public void setYPlayer(int value){ this.yPlayer = value; }

	public void setXOut(int value){ this.xOut = value; }

	public void setYOut(int value){ this.yOut = value; }

	public void setSize(int value){	this.size = value; }

	public int getSize(){ return this.size;	}

	public String getFilePath(){ return this.filepath; }
}