package source.game;

import source.game.interfaces.IGameComponent;
import source.tools.Grille;
import source.tools.Cases;
import source.tools.Window;
import source.tools.DrawGrille;
import source.tools.graph.Graphes;
import source.tools.events.GameSet;
import source.tools.events.GameCreate;
import source.tools.events.WinPopup;
import source.tools.utils.SaveLoader;

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

	/** remplir la grille */
	private boolean remplissage;

	/** le mode de jeu */
	private boolean mode; //true = auto, false = manuel
	private boolean type; //true = deter, false = aléatoire

	/**
	*
	* Cr&#233;e la c&#339;ur du jeu (algorithmes...)
	*
	* @param ecran ou afficher le jeu
	* @param filePath chemin d'une éventuelle sauvegarde
	*
	*/
	public GameCore(Window ecran, String filePath, boolean remplissage){
		this.ecran = ecran;
		this.filePath = filePath;
		this.grille = null;
		this.mode = false;
		this.type = false;
		this.remplissage = remplissage;
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
			this.grille = SaveLoader.getSave(this.filePath,this.ecran);
			this.setGame();
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
		this.grille.generate(remplissage);
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
		GameSet setter = new GameSet(this.grille);
		wall.addActionListener(setter);
		clear.addActionListener(setter);
		player.addActionListener(setter);
		exit.addActionListener(setter);
		//3-Ajout à la fenêtre
		boutonsChoix.add(wall);
		boutonsChoix.add(player);
		boutonsChoix.add(exit);
		boutonsChoix.add(clear);

		//Ajouts à la fenêtre des 4 composantes
		this.ecran.add(play, BorderLayout.NORTH);
		this.ecran.add(espaceSauvegarde, BorderLayout.SOUTH);
		this.ecran.add(boutonsChoix, BorderLayout.WEST);
		DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran,true);
		drawGrille.setOpaque(false);
		this.ecran.add(drawGrille,BorderLayout.CENTER);

		//met à jour l'écran
		this.ecran.revalidate();
	}

	public void setGame(){
		GameCreate gameCreateListener = new GameCreate(this.grille,this);

		//clean
		this.ecran.removeAll();
		this.ecran.repaint();
		this.ecran.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
			
		//affiche boutons aléatoire et déterministe
		JPanel choix = new JPanel();
		JButton deterministe = new JButton("Deterministe");
		JButton aleatoire = new JButton("Aléatoire");
		choix.setLayout(new GridLayout(2,1));
		choix.add(deterministe);
		choix.add(aleatoire);
		deterministe.addActionListener(gameCreateListener); //observateur
		aleatoire.addActionListener(gameCreateListener);
		this.ecran.add(choix,bag);
		this.ecran.revalidate();
	}

	public void gameStart(){
		//System.out.println("Le mode est :"
		//	+(this.mode?"automatique":"manuel")+" "+
		//	(this.type?"deterministe":"aléatoire"));

		Graphes graphe = new Graphes(this.grille);
		int xPlayer = this.grille.getXPlayer();
		int yPlayer = this.grille.getYPlayer();
		int xExit = this.grille.getXExit();
		int yExit = this.grille.getYExit();
		
		if(mode == false){
			this.ecran.setLayout(new BorderLayout());
			this.grille.removeAllListeners();
			DrawGrille drawGrille = new DrawGrille(this.grille,this.ecran,false);
			drawGrille.setOpaque(false);
			this.ecran.add(drawGrille,BorderLayout.CENTER);
			this.ecran.revalidate();
		} else {
			//sinon on est en automatique
			//deterministe
			double etapes = 0d;
			if(type){
				etapes = graphe
							.getSorthestPathWithMap(xPlayer,yPlayer,xExit,yExit);
			}

			WinPopup victoire = new WinPopup(this.ecran,"La grille a été "+
				"complétée en "+etapes+" étapes.");

		}
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

	public void setMode(boolean value){
		this.mode = value;
	}

	public void setType(boolean value){
		this.type = value;
	}
}