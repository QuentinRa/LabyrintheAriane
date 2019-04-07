package source.tools;

import source.game.Grille;
import source.game.Cases;
import source.tools.exceptions.InvalidDataException;
import source.tools.Matrices;

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

	public Graphes(Grille grille){
		this.grille = grille;
	}

	public boolean findPath(int x,int y, int xx, int yy){

		Cases[][] cases = this.grille.getCasesArray();

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

			if(i == xx && j == yy){
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

	public double getSorthestPath(int x,int y, int xx, int yy){

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
							int valeur = voisin(l,c,i,j) == true?1:0;
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

		int longueur = 0;

		while(matriceAdjacente[x+size*y][xx+size*yy] == 0){
			longueur++;
			Matrices.mul(matriceAdjacente,matriceAdjacente);
		}


		System.out.print("      ");
		for(i=0; i<size*size; i++)
			System.out.print(i+((i<10)?"    ":(i<100?"   ":"  ")));
		System.out.println("");

		for(i=0; i < size*size; i++){
			System.out.print(i+((i<10)?"    :":(i<100?"   :":"  :")));
			for(j=0; j<size*size; j++){
				System.out.print(matriceAdjacente[i][j]+"    ");
			}
			System.out.println("");
		}

		return longueur;
	}

	public boolean voisin(int x, int y, int xx, int yy){
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