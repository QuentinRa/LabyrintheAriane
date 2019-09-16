package game;

import engine.window.Window;
import engine.exceptions.ErrorPopup;
import engine.utils.CenteredPanel;
import game.evenements.JFileChooserListener;
import game.evenements.MenuButtonsListener;
import game.utils.GameComponent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

/**
 *
 * Le menu du jeu
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class Menu implements GameComponent {

	/**
	 *
	 * &#233;cran associ&#233; au menu
	 *
	 */
	private Window screen;
	/**
	 *
	 * Observateur associ&#233; aux boutons du menu
	 *
	 */
	private MenuButtonsListener menuButtonsListener;
	/**
	 *
	 *  chemin des sauvegardes de base
	 *
	 */
	private String savPath;

	/**
	 *
	 * Cr&#233;e un menu
	 *
	 * @param window la fen&#234;tre dans laquelle cr&#233;er le menu
	 *
	 */
	public Menu(Window window){
		this.screen = window;
		this.savPath = ".";
		this.menuButtonsListener = new MenuButtonsListener(this.screen,this);
	}

	@Override
	public void run(){
		//Boutons du menu
		JPanel panneauSupInfos = new JPanel();
		panneauSupInfos.setLayout(new GridLayout(2,1));
		panneauSupInfos.setOpaque(false);
		JLabel title = new JLabel("Labyrinthe d'ariane");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 28));
		title.setForeground(Color.BLACK);
		title.setBackground(new Color(255, 255, 255, 200) );
		title.setOpaque(true);
		title.setHorizontalAlignment(JLabel.CENTER);
		JTextArea explication = new JTextArea("Le but de ce programme est de"
			+" vous présenter un algorithme de guidage"
			+" d'un objet mobile jusqu'à une destination dans un labyrinthe.");
		explication.setBackground(new Color(255, 255, 255, 225));
		explication.setForeground(new Color(66, 66, 66, 175));
		explication.setFont(new Font(explication.getFont().getName(), Font.PLAIN, 15));
		explication.setLineWrap(true);
		explication.setWrapStyleWord(true);
		explication.setEditable(false);
		panneauSupInfos.add(title);
		panneauSupInfos.add(explication);
		JButton jouer = new JButton("Jouer");
		
		//Observateurs
		jouer.addActionListener(this.menuButtonsListener);

		//Panneaux avec éléments centrées
		CenteredPanel panneauSup = new CenteredPanel();
		CenteredPanel panneauInf = new CenteredPanel();
		panneauSup.add(panneauSupInfos);
		panneauInf.add(jouer);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new GridLayout(2,1));
		this.screen.add(panneauSup);
		this.screen.add(panneauInf);
		this.screen.revalidate();
	}

	public void choix(){
		//Boutons du menu
		JButton newGame = new JButton("Nouveau labyrinthe");
		JButton charger = new JButton("Charger un labyrinthe");
		//Observateurs
		newGame.addActionListener(this.menuButtonsListener);
		charger.addActionListener(this.menuButtonsListener);

		//Panneaux avec éléments centrées
		CenteredPanel panneauSup = new CenteredPanel();
		CenteredPanel panneauInf = new CenteredPanel();
		panneauSup.add(newGame);
		panneauInf.add(charger);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new GridLayout(2,1));
		this.screen.add(panneauSup);
		this.screen.add(panneauInf);
		this.screen.revalidate();
	}


	/**
	 *
	 * Charge une sauvegarde
	 *
	 */
	public void charger(){
		try{
			//Change pour layout qui prends tout l'écran
			this.screen.setLayout(new BorderLayout());
			//Charge le gestionnaire de fichiers, dossier courant
			JFileChooser gestionnaireFichiers = new JFileChooser(this.savPath);
			this.screen.add(gestionnaireFichiers, BorderLayout.CENTER);
			gestionnaireFichiers.addActionListener(new JFileChooserListener(this.screen,this));
			//met à jour l'écran
			this.screen.revalidate();
		}catch(Exception e){
			//Affiche un panneau avec l'erreur, puis quittera
			String message = "Ouverture du gestionnaire de fichier à échoué";
			ErrorPopup popup = new ErrorPopup(this.screen,message);
		}
	}

	/**
	 *
	 * Cr&#233;e un nouveau jeu
	 *
	 */
	public void nouveau(){
		//Boutons du menu
		JButton random = new JButton("Pré-remplir la grille");
		JButton vide = new JButton("Grille vide");
		//Observateurs
		random.addActionListener(this.menuButtonsListener);
		vide.addActionListener(this.menuButtonsListener);

		//Panneaux avec éléments centrées
		CenteredPanel panneauSup = new CenteredPanel();
		CenteredPanel panneauInf = new CenteredPanel();
		panneauSup.add(random);
		panneauInf.add(vide);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new GridLayout(2,1));
		this.screen.add(panneauSup);
		this.screen.add(panneauInf);
		this.screen.revalidate();
	}

	/**
	 *
	 * Lance le jeu
	 *
	 * @param savePath chemin d'une sauvegarde/null ou cha&#238;ne vide pour aucune
	 * @param randomlyFilled true si on doit remplir al&#233;atoirement la grille (seulement si pas de sauvegarde)
	 *
	 */
	public void gameStart(String savePath, boolean randomlyFilled){
		//Lancement du jeu
		GameCore game = new GameCore(this.screen,savePath,randomlyFilled);
		game.run();
	}

	/**
	 *
	 * Change le chemin o&#249; chercher les sauvegardes
	 *
	 * @param path le nouveau chemin, null ou cha&#238;ne vide pour aucun (=dossier courant)
	 *
	 */
	public void setSavePath(String path){
		if(path == null || path.length() == 0)
			path = ".";
		this.savPath = path;
	}
}
