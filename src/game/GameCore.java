package game;

import engine.algorithms.Search;
import engine.graph.Case;
import engine.graph.DrawGrille;
import engine.graph.Grille;
import engine.window.Window;
import engine.exceptions.ErrorPopup;
import engine.exceptions.WinPopup;
import engine.exceptions.MessagePopup;
import engine.utils.CenteredPanel;
import game.evenements.CreateGameListener;
import game.utils.CreateGame;
import game.utils.SaveLoader;
import game.evenements.GameCoreButtonsListener;
import game.evenements.KeyboardListener;
import game.utils.GameComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


/**
 *
 * Partie principale (coeur) du jeu.
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class GameCore implements GameComponent {

	/**
	 *
	 *  &#233;cran sur lequel on affiche
	 *
	 */
	private Window screen;
	/**
	 *
	 *  chemin d'un &#233;ventuel fichier de sauvegarde &#224; charger
	 *
	 */
	private String savePath;
	/**
	 *
	 *  remplir la grille al&#233;atoirement. true = oui, false = non.
	 *
	 */
	private boolean randomlyFilled;
	/**
	 *
	 * Grille du jeu
	 *
	 */
	private Grille grille;
	/**
	 *
	 * Search associ&#233; au jeu
	 *
	 */
	private Search search;
	/**
	 *
	 * Mode de jeu (automatique : true, manuel : false)
	 *
	 */
	private boolean automaticMode;
	/**
	 *
	 * Type de jeu (al&#233;atoire : true, d&#233;terministe : false)
	 *
	 */
	private boolean randomType;
	/**
	 *
	 * Observateur des boutons de s&#233;lection
	 *
	 */
	private GameCoreButtonsListener gameCoreButtonsListener;
	/**
	 *
	 * Prochaine direction
	 *
	 */
	private JLabel direction;

	/**
	 *
	 * Indique si on à déja crée un jeu
	 *
	 */
	private boolean createdOnce;

	/**
	 *
	 * Construit le coeur du jeu (partie qui fait tourner les algorithme).
	 *
	 * @param screen &#233;cran sur lequel le jeu tourne
	 * @param savePath chemin d'une &#233;ventuelle sauvegarde
	 * @param randomlyFilled si on doit remplir al&#233;atoirement la grille.
	 *
	 */
	public GameCore(Window screen, String savePath, boolean randomlyFilled){
		this.screen = screen; //écran
		this.savePath = savePath; //Sauvegarde
		this.randomlyFilled = randomlyFilled; //remplissage
		this.grille = null; //grille de jeu
		this.search = null; // search -> algorithmes de parcours
		this.direction = null; // prochaine direction, manuel seulement
		this.createdOnce = false;
		//Observateur des boutons
		this.gameCoreButtonsListener = new GameCoreButtonsListener(this.screen,this);
	}

	@Override
	public void run(){
		// 2 possibilités :
		//		1 - je charge un jeu existant
		//		2 - je crée un nouveau jeu

		//si on m'a donné un chemin, je charge
		if(this.savePath != null && this.savePath.length() != 0){
			try {
				// lis le fichier de sauvegarde
				this.grille = SaveLoader.getSave(this.savePath);
			} catch (IOException e) {
				//Affiche si erreur
				ErrorPopup error = new ErrorPopup(screen,e.getMessage());
			}
			this.setType();
		} //sinon on crée un nouveau jeu
		  else {
		  	//Création de la grille
			this.grille = new Grille();

			//Elements
			JPanel menu = new JPanel();
			JLabel tailleGrille = new JLabel("Taille de la grille");
			JTextField answer = new JTextField();
			//Observateurs
			answer.addActionListener(new CreateGameListener(this.screen,this.grille,this));

			//Panneau
			menu.setLayout(new GridLayout(2,1));
			menu.add(tailleGrille);
			menu.add(answer);

			//Ajoute et on re-valide (=on re-dessine l'écran)
			this.screen.setLayout(new GridBagLayout());
			this.screen.add(menu);
			this.screen.revalidate();
		}
	}

	/**
	 *
	 * Cr&#233;e un nouveau jeu
	 *
	 */
	public void createNewGame(){
		CreateGame newGame = new CreateGame(this.screen, this.grille, this);
		if(!this.createdOnce) {
			newGame.render(this.randomlyFilled);
			createdOnce = true;
		} else {
			newGame.repaint();
		}
	}

	/**
	 *
	 * <p>D&#233;finit le type de l'algorithme</p>
	 *  <ul>
	 *      <li>D&#233;terministe : trouver la sortie en un minimum de d&#233;placements.</li>
	 *      <li>Al&#233;atoire : trouver la sortie al&#233;atoirement s'il y en a une.</li>
	 *  </ul>
	 *
	 * @see GameCore#setType()
	 *
	 */
	public void setType(){
		//Boutons de choix du mode
		JPanel deterP = new JPanel();
		deterP.setLayout(new GridLayout(2,1));
		JButton deterministe = new JButton("Parcours déterministe");
		JTextArea deterInfo = new JTextArea("Thésée cartographie le labyrinthe"
			+" pour sortir rapidement");
		deterInfo.setBackground(new Color(255, 255, 255, 225));
		deterInfo.setForeground(new Color(66, 66, 66, 175));
		deterInfo.setFont(new Font(deterInfo.getFont().getName(), Font.PLAIN, 15));
		deterInfo.setLineWrap(true);
		deterInfo.setEditable(false);
		deterInfo.setWrapStyleWord(true);
		deterP.add(deterministe);
		deterP.add(deterInfo);
		
		JPanel randomP = new JPanel();
		randomP.setLayout(new GridLayout(2,1));
		JButton random = new JButton("Parcours aléatoire");
		JTextArea randomInfo = new JTextArea("Thésée se balade en choissisant"
			+" une direction au hasard.");
		randomInfo.setBackground(new Color(255, 255, 255, 225));
		randomInfo.setForeground(new Color(66, 66, 66, 175));
		randomInfo.setFont(new Font(randomInfo.getFont().getName(), Font.PLAIN, 15));
		randomInfo.setLineWrap(true);
		randomInfo.setEditable(false);
		randomInfo.setWrapStyleWord(true);
		randomP.add(random);
		randomP.add(randomInfo);
		
		//Observateurs
		deterministe.addActionListener(this.gameCoreButtonsListener);
		random.addActionListener(this.gameCoreButtonsListener);

		//Panneaux avec éléments centrées
		CenteredPanel panneauSup = new CenteredPanel();
		CenteredPanel panneauInf = new CenteredPanel();
		panneauSup.add(deterP);
		panneauInf.add(randomP);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new GridLayout(2,1));
		this.screen.add(panneauSup);
		this.screen.add(panneauInf);
		this.screen.revalidate();
	}

	/**
	 *
	 * <p>D&#233;finit le mode de jeu.</p>
	 *  <ul>
	 *      <li>Manuel : l'utilisateur fait d&#233;filer les &#233;tapes, une par une.</li>
	 *      <li>Automatique : le jeu renvoi le nombre de d&#233;placements.
	 *      Dans la cas de l'algorithme al&#233;atoire, il s'ag&#238;t d'une moyenne sur 100 tests.</li>
	 *  </ul>
	 *
	 * @see GameCore#setMode()
	 * @see GameCore#nextMove()
	 *
	 */
	public void setMode(){
		//Boutons de choix du mode
		JPanel manuelP = new JPanel();
		manuelP.setLayout(new GridLayout(2,1));
		JButton manuel = new JButton("Parcours en mode manuel");
		JTextArea manuelInfo = new JTextArea("Vous visionnez chaque étape du parcours.");
		manuelInfo.setBackground(new Color(255, 255, 255, 225));
		manuelInfo.setForeground(new Color(66, 66, 66, 175));
		manuelInfo.setFont(new Font(manuelInfo.getFont().getName(), Font.PLAIN, 15));
		manuelInfo.setLineWrap(true);
		manuelInfo.setEditable(false);
		manuelInfo.setWrapStyleWord(true);
		manuelP.add(manuel);
		manuelP.add(manuelInfo);

		//Boutons de choix du mode
		JPanel automatiqueP = new JPanel();
		automatiqueP.setLayout(new GridLayout(2,1));
		JButton automatique = new JButton("Parcours en mode automatique");
		JTextArea automatiqueInfo;
		if(this.randomType)
			automatiqueInfo = new JTextArea("Vous visionnez un moyenne sur 100 parcours.");
		else
			automatiqueInfo = new JTextArea("Vous visionnez seulement le résultat.");
		automatiqueInfo.setBackground(new Color(255, 255, 255, 225));
		automatiqueInfo.setForeground(new Color(66, 66, 66, 175));
		automatiqueInfo.setFont(new Font(automatiqueInfo.getFont().getName(), Font.PLAIN, 15));
		automatiqueInfo.setLineWrap(true);
		automatiqueInfo.setEditable(false);
		automatiqueInfo.setWrapStyleWord(true);
		automatiqueP.add(automatique);
		automatiqueP.add(automatiqueInfo);
		
		//Observateurs
		manuel.addActionListener(this.gameCoreButtonsListener);
		automatique.addActionListener(this.gameCoreButtonsListener);

		//Panneaux avec éléments centrées
		CenteredPanel panneauSup = new CenteredPanel();
		CenteredPanel panneauInf = new CenteredPanel();
		panneauSup.add(manuelP);
		panneauInf.add(automatiqueP);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new GridLayout(2,1));
		this.screen.add(panneauSup);
		this.screen.add(panneauInf);
		this.screen.revalidate();

	}

	/**
	 *
	 * Lancement de l'algorithme selon le mode.
	 * Affiche un message de victoire et quitte si la sortie est trouv&#233;e/erreur.
	 *
	 */
	public void gameStart(){
		this.search = new Search(this.grille);

		//Si on est en manuel
		if(!this.automaticMode){
			//Label qui affiche le prochain mouvement
			direction = new JLabel();
			direction.setHorizontalAlignment(JLabel.CENTER);
			direction.setOpaque(true);

			this.grille.removeAllListeners(); //suppression des observateurs de la grille
			//Dessine la grille
			DrawGrille drawGrille = new DrawGrille(this.grille);
			drawGrille.setOpaque(false);

			//Ajuste la fenêtre si elle est trop petite et que l'écran le permet
			int size = this.grille.getSize()* Case.getCaseSize()+100; // 100 = marge bordures
			if(size > this.screen.getWidth() || size>this.screen.getHeight()){
				if(size<this.screen.getMonitorWidth() && size<this.screen.getMonitorHeight()) {
					this.screen.setSize(size, size); //augmente sa taille
					this.screen.setLocation(Window.ALIGN_CENTER); //centre par rapport à l'écran
				} else {
					String message = "La taille de l'écran ne permet pas le jeu sur une grille de cette taille";
					ErrorPopup errorPopup = new ErrorPopup(this.screen,message);
				}
			}

			//Affiche le nouvel écran
			this.screen.setLayout(new BorderLayout());
			this.screen.add(this.direction,BorderLayout.NORTH);
			this.screen.add(drawGrille,BorderLayout.CENTER);
			this.screen.setFocusable(true);
			this.screen.requestFocusInWindow();
			//Observateur pour faire avancer le parcours
			this.screen.addKeyListener(new KeyboardListener(this));
			this.screen.revalidate();

			String messagePopup = "Appuyez sur une touche pour vous déplacer.";
			MessagePopup popup = new MessagePopup(this.screen,messagePopup);
		} else {
			double count = 0d;

			if(this.randomType){
				double i = 0d;
				double somme = 0d;
				//Tourner 100 fois pour une moyenne
				while(i<100d){
					double tmp = 0d;
					//Fais tourner tant que l'on a pas trouvé la sortie
					while(tmp == 0) {
						tmp = (double) search.randomPathWithoutMap();
					}
					if(tmp != -1) {
						somme += tmp;
						i++;
					} else {
						//on quitte car il n'y a pas de sortie
						somme = -100d;
						i = 100d;
					}
				}
				count = somme/i;
			} else {
				while(count == 0){
					count = this.search.findPathWithoutMap();
				}
			}

			//Message de fin
			String message = "La grille a été complétée en "+count+" étapes.";
			if(count == -1){
				message = "Il n'existe pas de chemin allant à la sortie";
			}

			//Affiche le popup de victoire
			WinPopup victoire = new WinPopup(this.screen,message,Case.PLAYER);
			this.screen.dispose(); //Fermeture
		}
	}

	/**
	 *
	 * Permet de d&#233;finir le mode.
	 *
	 * @param automaticMode true pour automatique, false pour manuel
	 *
	 * @see GameCore#setMode()
	 *
	 */
	public void setAutomaticMode(boolean automaticMode) {
		this.automaticMode = automaticMode;
	}

	/**
	 *
	 * Permet de d&#233;finir le type d'algorithme.
	 *
	 * @param randomType true pour al&#233;atoire, false pour d&#233;terministe
	 *
	 * @see GameCore#setType()
	 *
	 */
	public void setRandomType(boolean randomType) {
		this.randomType = randomType;
	}

	/**
	 *
	 * M&#233;thode qui appelle l'algorithme d&#233;terministe/al&#233;atoire pour un d&#233;placement.
	 * Affiche un message de victoire et quitte si la sortie est trouv&#233;e/erreur.
	 *
	 */
	public void nextMove() {
		int retour;
		if(this.randomType){
			retour = this.search.randomPathWithoutMap(this.direction);
		} else {
			retour = this.search.findPathWithoutMap(this.direction);
		}
		if(retour != 0){
			String message;
			if(retour != -1)
				message = "La grille a été complétée en "+retour+" étapes.";
			else {
				message = "Il n'existe pas de chemin allant à la sortie...";
			}

			WinPopup victoire = new WinPopup(this.screen,message,Case.PLAYER);
			this.screen.dispose(); //Fermeture
		}
	}

	/**
	 *
	 * Change le chemin des sauvegardes
	 *
	 * @param savePath le nouveau chemin
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 *
	 * Renvoi le chemin des sauvegardes
	 *
	 * @return le chemin des sauvegardes
	 *
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 *
	 * Renvoi la grille de jeu
	 *
	 * @return la grille de jeu
	 *
	 */
	public Grille getGrille() {
		return this.grille;
	}
}
