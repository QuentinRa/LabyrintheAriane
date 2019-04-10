package source.tools.graph;

import source.tools.Grille;
import source.tools.Cases;
import source.tools.exceptions.InvalidDataException;
import source.tools.graph.Matrices;

import java.util.Queue;
import java.util.LinkedList;

import java.util.Random;

/**
*
* 
*
* @version 1.0 7 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Graphes{

	private Grille grille;
	private LinkedList<Cases> noMap;
	private boolean fileInitialised;
	private Cases[][] cases;
	private int size;
	private int count;

	private Random rand;

	public Graphes(Grille grille){
		this.grille = grille;
		this.noMap = new LinkedList<>();
		this.fileInitialised = false;
		this.cases = this.grille.getCasesArray();
		this.size = this.grille.getSize();
		this.count = 0;
		this.rand = new Random();
	}

	public boolean findPathWithMap(int x,int y, int xx, int yy){
		Queue<Cases> file = new LinkedList<>();
		file.add(cases[x][y]);

		int i=0,j=0;
		int size = this.grille.getSize();

		//Dépille tout
		for(i=0; i<size; i++){
			for(j=0; j <size; j++){
				cases[i][j].setEmpille(false);
			}
		}

		while(file.isEmpty() == false){
			Cases first = file.element(); //Récupère le 1er élément
			first.setEmpille(true);
			i = first.getXPos();
			j = first.getYPos();

			if(j == xx && i == yy){
				//On a trouvé un chemin
				file.clear(); //vide pile
				return true;
			}else{
				//chemin gauche
				if(this.cheminGauche(cases,i,j)){
					file.add(cases[i][j-1]);
				}
				//chemin droite
				if(this.cheminDroite(cases,i,j,size)){
					file.add(cases[i][j+1]);
				}
				//chemin haut
				if(this.cheminHaut(cases,i,j)){
					file.add(cases[i-1][j]);
				}
				//chemin bas
				if(this.cheminBas(cases,i,j,size)){
					file.add(cases[i+1][j]);
				}

				file.remove(); //supprime le 1er élément
			}
		}

		return false;
	}

	public int findPathWithoutMap(){
		this.count++;

		if(!this.fileInitialised){
			int x = this.grille.getXPlayer();
			int y= this.grille.getYPlayer();
			noMap.add(this.grille.getCase(x,y));
			this.fileInitialised = true;

		for(int i=0; i<size; i++){
				for(int j=0; j <size; j++){
					cases[i][j].setEmpille(false);
					cases[i][j].setParcourue(false);
				}
			}
		}

		int x = this.grille.getXPlayer();
		int y = this.grille.getYPlayer();
		boolean moved = false;
		boolean retour = false;

		if(cases[x][y].isParcourue()){
			//on a fait du surPlace donc la dernière case visitée est
			//blocante : on l'empille
			//System.out.println("entre");
			Cases dernier = noMap.getLast();
			dernier.setEmpille(true);
			noMap.removeLast();
			Cases nouveauDernier = noMap.getLast();
			nouveauDernier.setParcourue(false);
		}

		cases[x][y].setParcourue(true);

			if(x-1<size && x-1>=0 && !this.cases[x-1][y].isParcourue()
				&& !cases[x-1][y].isEmpille()){
				retour = this.grille.movePlayer(-1,0);
				noMap.add(this.grille.getCase(x-1,y));
			} else if(x+1<size && x+1>=0 && !this.cases[x+1][y].isParcourue()
						&& !cases[x+1][y].isEmpille()){
				retour = this.grille.movePlayer(1,0);
				noMap.add(this.grille.getCase(x+1,y));
			} else if(y-1<size && y-1>=0 && !this.cases[x][y-1].isParcourue() 
						&& !cases[x][y-1].isEmpille()){
				retour = this.grille.movePlayer(0,-1);
				noMap.add(this.grille.getCase(x,y-1));
			} else if(y+1<size && y+1>=0 && !this.cases[x][y+1].isParcourue()
					&&	!cases[x][y+1].isEmpille()){
				retour = this.grille.movePlayer(0,1);
				noMap.add(this.grille.getCase(x,y+1));
			}

			if(retour){
				noMap.clear();
				return this.count;
			}
			if(noMap.size() == 1){
				return -1;
			}

		return 0;
	}

	public int randomPathWithoutMap(){
		this.count++;

		boolean axis = rand.nextBoolean();	//true = y false = x
		boolean retour = false;
		int value = rand.nextBoolean()==true?1:-1;

		if(axis){
			retour = this.grille.movePlayer(value,0);
		} else {
			retour = this.grille.movePlayer(0,value);
		}

		if(retour){ return count; }

		return 0;
	}

	public void reset(){
		this.count = 0;
		this.noMap.clear();
		this.fileInitialised = false;
	}

	public double getSorthestPathWithMap(int x,int y, int xx, int yy){

		int i=0,j=0;
		int size = this.grille.getSize();

		//On créer un tableau ou si on regarde la ligne puis la colonne
		//on peut savoir le nombre de chemin (matrice puissance 1) en
		//un coup allant de la ligne a la colonne

		//On multiplie la matrice jusqu'à ce qu'une valeur apparaisse
		//à l'intersection et donc qu'il existe au moins un chemin
		// en (puissance) coups.

		int[][] matriceAdjacente = new int[size*size][size*size];

		int l,c;
		//je regarde pour toutes les cases
		for(l=0; l<size; l++){
			for(c=0; c<size; c++){
				//Si c'est une case vide alors je regarde ses voisins
				//cad les cases qu'elle peut atteindre en un coup
				// je met 1 dans sa ligne dans les colonne ou c le cas
				//notez que la grille a étée déroulée
				// 0 = cases[0][0] et size*size = case[size][size]
				if(!this.grille.getCasesArray(l,c)){
					for(i=0; i < size; i++){
						for(j=0; j<size; j++){
							//je regarde ses voisins
							int valeur = voisinWithMap(l,c,i,j) == true?1:0;
							//je met toutes les valeurs DANS LA LIGNE
							//dont tous les voisins de cases[0][0] sont
							//sur la ligne 0 et dans des colonnes
							//qui vont de 0 à size*size
							matriceAdjacente[l*size+c][j+i*size] = valeur;
						}
					}
				}
			}
		}

		int longueur = 1; //le minimum c'est en un coup
		int[][] matrice = matriceAdjacente;

		/*for(i=0; i < size*size; i++){
			for(j=0; j<size*size; j++){
				System.out.print(matriceAdjacente[j][i]);
			}
			System.out.println("");
		}*/

		while(matrice[x+y*size][xx+yy*size] == 0){
			longueur++;
			matrice = Matrices.mul(matriceAdjacente,matrice);
		}
		
		return longueur;
	}

	public boolean voisinWithMap(int x, int y, int xx, int yy){
		//Aucune coordonnée en commun
		if(x!= xx && y!= yy){
			return false;
		}
		//Si il est voisin en colonne
		else if(xx-1 == x || xx+1 == x)
			return !this.grille.getCasesArray(xx,y);

		//Si il est voisin en ligne
		else if(yy-1 == y || yy+1 == y)
			return !this.grille.getCasesArray(x,yy);

		return false;			
	}

	private boolean cheminGauche(Cases[][] cases, int i, int j){
		if(j-1>=0 && !cases[i][j-1].getValue()
					&& !cases[i][j-1].isEmpille()){
			return true;
		}
		return false;
	}

	private boolean cheminDroite(Cases[][] cases, int i, int j, int size){
		if(j+1<size && !cases[i][j+1].getValue()
				&& !cases[i][j+1].isEmpille()){
			return true;
		}
		return false;
	}

	private boolean cheminHaut(Cases[][] cases, int i, int j){
		if(i-1>=0 && !cases[i-1][j].getValue()
						&& !cases[i-1][j].isEmpille()){
			return true;
		}
		return false;
	}

	private boolean cheminBas(Cases[][] cases, int i, int j, int size){
		if(i+1<size && !cases[i+1][j].getValue()
					&& !cases[i+1][j].isEmpille()){
			return true;
		}
		return false;
	}
}