package engine.algorithms;

import engine.graph.Grille;
import engine.graph.Case;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;

/**
 *
 * Algorithmes de parcours
 *
 * @version 1.0 7 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class Search {

	/**
	 *
	 * Grille du jeu, permet de d&#233;placer le joueur
	 *
	 */
	private Grille grille;
	/**
	 *
	 * Les cases de la grille
	 *
	 */
	private Case[][] cases;
	/**
	 *
	 * Compteur du nombre de d&#233;placements
	 *
	 */
	private int moveCount;
	/**
	 *
	 * Indique si on a fait les initialisations
	 *
	 */
	private boolean initialised;
	/**
	 *
	 * File servant &#224; mapper la grille
	 *
	 */
	private LinkedList<Case> map;
	/**
	 *
	 * Gestionnaire de l'al&#233;atoire
	 *
	 */
	private static final Random rand = new Random();

	/**
	 *
	 * Position x du joueur
	 *
	 */
	private int xPlayer;
	/**
	 *
	 * Position y du joueur
	 *
	 */
	private int yPlayer;

	/**
	 *
	 * Construit un gestionnaire d'algorithmes de parcours de graphe
	 *
	 * @param grille la grille qui nous sert de graphe
	 *
	 */
	public Search(Grille grille){
		this.grille = grille;
		this.cases = grille.getCasesArray();
		this.moveCount = 0; //nombre de déplacement
		this.map = new LinkedList<>(); //map crée en mappant la map
		this.initialised = false;

		//Sauvegarde la position du joueur
		this.xPlayer = this.grille.getXPlayer();
		this.yPlayer = this.grille.getYPlayer();
	}

	/**
	 *
	 * Algorithme d&#233;terministe de recherche du plus court chemin sans conna&#238;tre la map
	 *
	 * @return
	 * <ul>
	 *     <li>int : 0 si aucun chemin n'a &#233;t&#233; trouv&#233; mais on n'est pas sur qu'il n'en existe pas.</li>
	 *     <li>int : -1 s'il n'existe aucun chemin</li>
	 *     <li>int : valeur (positive) qui correspond au nombre de d&#233;placements si un chemin est trouv&#233;</li>
	 * </ul>
	 *
	 */
	public int findPathWithoutMap(){
		return this.findPathWithoutMap(null);
	}

	/**
	 *
	 * Algorithme d&#233;terministe de recherche du plus court chemin sans conna&#238;tre la map
	 *
	 * @param label etiquette dans laquelle la direction choisie par l'algorithme serait &#233;crite,
	 *
	 * @return
	 * <ul>
	 *     <li>int : 0 si aucun chemin n'a &#233;t&#233; trouv&#233; mais on n'est pas sur qu'il n'en existe pas.</li>
	 *     <li>int : -1 s'il n'existe aucun chemin</li>
	 *     <li>int : valeur (positive) qui correspond au nombre de d&#233;placements si un chemin est trouv&#233;</li>
	 * </ul>
	 *
	 */
	public int findPathWithoutMap(JLabel label){
		if(!this.initialised){
			//Ajoute le joueur à la file
			int x = this.grille.getXPlayer();
			int y = this.grille.getYPlayer();
			map.add(this.grille.getCase(x,y));
			this.initialised = true;

			//Reset des cases parcourues et empilés
			for(int i=0; i< this.grille.getSize(); i++){
				for(int j=0; j < this.grille.getSize(); j++){
					cases[i][j].setEmpile(false);
					cases[i][j].setParcourue(false);
				}
			}
		}
		//Incrémente le nombre de déplacements
		this.moveCount++;

		//Récupère position du joueur
		int x = this.grille.getXPlayer();
		int y = this.grille.getYPlayer();

		//On va tenter de déplacer le joueur
		//on a fait du surPlace donc la dernière case visitée est bloquante : on l'empille = mur
		if(cases[x][y].isParcourue()){
			Case dernier = map.getLast();
			dernier.setEmpile(true);
			map.removeLast(); //Retire de la pile
			//Reviens en arrière
			Case nouveauDernier = map.getLast();
			nouveauDernier.setParcourue(false);
		}

		//On a parcouru la case ou on est.
		cases[x][y].setParcourue(true);

		boolean sortie = false; //sortie trouvée = true
		int size = grille.getSize();
		String direction = "";

		if(x-1>=0 && !this.cases[x-1][y].isParcourue() && cases[x-1][y].isNotEmpile()){
			//on regarde pas x-1 < size car x<size
			sortie = this.grille.movePlayer(-1, 0);
			map.add(this.grille.getCase(x - 1, y)); //ajoute la case
			direction = "gauche";
		}
		else if(x+1<size && !this.cases[x+1][y].isParcourue() && cases[x+1][y].isNotEmpile()){
			//on regarde pas x+1>=0 car x>=0
			sortie = this.grille.movePlayer(1,0);
			map.add(this.grille.getCase(x+1,y));
			direction = "droite";
		}
		else if(y-1>=0 && !this.cases[x][y-1].isParcourue() && cases[x][y-1].isNotEmpile()){
			//on regarde pas y-1 < size car y<size
			sortie = this.grille.movePlayer(0,-1);
			map.add(this.grille.getCase(x,y-1));
			direction = "haut";
		}
		else if(y+1<size && !this.cases[x][y+1].isParcourue() && cases[x][y+1].isNotEmpile()){
			//on regarde pas y+1>=0 car y>=0
			sortie = this.grille.movePlayer(0,1);
			map.add(this.grille.getCase(x,y+1));
			direction = "bas";
		}

		//Affiche dans le label
		if (label != null) {
			label.setText("Direction : " + direction);
		}

		//Si la fonction renvoi true, alors on a trouvé la sortie
		if (sortie) {
			map.clear(); //vide la map
			int moveCount = this.moveCount;
			this.reset();//Reset de l'algorithme
			return moveCount;
		}
		if (map.size() == 1) {
			// S'il ne reste que le joueur dans la map, alors on a tout parcouru
			// et il n'y a donc pas de sortie
			return -1;
		}

		return 0;
	}

	/**
	 *
	 * Algorithme al&#233;atoire de recherche d'une sortie.
	 *
	 * @return
	 * <ul>
	 *     <li>int : 0 si aucun chemin n'a &#233;t&#233; trouv&#233; mais on n'est pas sur qu'il n'en existe pas.</li>
	 *     <li>int : -1 s'il n'existe aucun chemin</li>
	 *     <li>int : -2 si trouver le chemin est trop long (plus de 100 000 d&#233;placements)</li>
	 *     <li>int : valeur (positive) qui correspond au nombre de d&#233;placements si un chemin est trouv&#233;</li>
	 * </ul>
	 *
	 * <p><b>Attention </b> : Le nombre maximum de d&#233;placements est 100 000 </p>
	 *
	 */
	public int randomPathWithoutMap(){
		return this.randomPathWithoutMap(null);
	}

	/**
	 *
	 * Algorithme al&#233;atoire de recherche d'une sortie.
	 *
	 * @param label etiquette dans laquelle la direction choisie par l'algorithme serait &#233;crite
	 *
	 * @return
	 * <ul>
	 *     <li>int : 0 si aucun chemin n'a &#233;t&#233; trouv&#233; mais on n'est pas sur qu'il n'en existe pas.</li>
	 *     <li>int : -1 s'il n'existe aucun chemin</li>
	 *     <li>int : -2 si trouver le chemin est trop long (plus de 100 000 d&#233;placements)</li>
	 *     <li>int : valeur (positive) qui correspond au nombre de d&#233;placements si un chemin est trouv&#233;</li>
	 * </ul>
	 *
	 * <p><b>Attention </b> : Le nombre maximum de d&#233;placements est 100 000 </p>
	 *
	 */
	public int randomPathWithoutMap(JLabel label) {
		if(!initialised){ //Si jamais lancé la méthode
			//Test s'il existe une sortie, sinon boucle inf
			int retour = 0;
			while(retour == 0){
				retour = this.findPathWithoutMap();
			}
			if(retour == -1){
				return -1;
			}
			this.reset(); //reset
			this.initialised = true;
		}
		this.moveCount++;
		//CAP de déplacements
		if(moveCount > 100000){
			this.reset();//Reset de l'algorithme
			return -2;
		}

		boolean axis = rand.nextBoolean();	//true = x false = y
		int value = rand.nextBoolean()?1:-1; //augmente/réduire valeur de axis
		boolean retour;

		//Si c'est x
		if(axis){
			//on va gauche/droite
			retour = this.grille.movePlayer(value,0);
			//si etiquette, affiche
			if(label != null) {
				if (value == -1) {
					label.setText("Direction : gauche");
				} else {
					label.setText("Direction : droite");
				}
			}
		} else {
			retour = this.grille.movePlayer(0,value);
			if(label != null) {
				if (value == -1) {
					label.setText("Direction : haut");
				} else {
					label.setText("Direction : bas");
				}
			}
		}

		//Renvoi les déplacements si c'est la sortie
		if(retour){
			int moveCount = this.moveCount;
			this.reset();//Reset de l'algorithme
			return moveCount;
		}

		return 0;
	}

	/**
	 *
	 * Reset les valeurs apr&#232;s utilisation d'un algorithme
	 *
	 */
	private void reset(){
		this.moveCount = 0;
		if(this.map != null)
			this.map.clear();
		this.initialised = false;

		//Reset de la position du joueur
		this.grille.getCase(this.grille.getXPlayer(),this.grille.getYPlayer()).setIcon(null);
		this.grille.setXPlayer(this.xPlayer);
		this.grille.setYPlayer(this.yPlayer);
	}
}

