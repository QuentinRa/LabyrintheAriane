package source.tools;

import source.game.Grille;
import source.game.Cases;
import source.tools.exceptions.InvalidDataException;

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

	private int tours;

	private Random rand;

	public Graphes(Grille grille){
		this.grille = grille;
		this.tours = 0;
		this.rand = new Random();
	}

	public boolean findPath(int x,int y, int xx, int yy){

		Cases[][] cases = this.grille.getCasesArray();

		LinkedList<Cases> file = new LinkedList<>();
		LinkedList<Cases> tour = new LinkedList<>();
		file.add(cases[x][y]);

		int i,j;
		int size = this.grille.getSize();

		while(file.isEmpty() == false){
			Cases first = file.element(); //Récupère le 1er élément
			tour.add(first);
			first.setEmpille(true);
			i = first.getXPos();
			j = first.getYPos();

			int a = 0;

			if(i == xx && j == yy){
				//On a trouvé un chemin
				file.clear(); //vide pile
				this.tours = tour.size();
				tour.clear();
				return true;
			}else{
				int passed = 0;
				int[] ar = new int[]{-1,-1,-1,-1};
				while(passed < 4){
					int num = 0;

					boolean deja = true;
					while(deja){
						num = rand.nextInt(4);
						if(ar[num] == -1){
							deja = false;
							passed ++;
							ar[num] = 0;
						}
					}

					//chemin gauche ok
					if(j-1>=0 && !cases[i][j-1].getValue()
						&& !cases[i][j-1].isEmpille() && num == 0){
						
						file.add(cases[i][j-1]);
						a++;
					}
					//chemin droite
					if(j+1<size && !cases[i][j+1].getValue()
						&& !cases[i][j+1].isEmpille() && num == 1){
						
						file.add(cases[i][j+1]);
						a++;
					}
					//chemin haut
					if(i-1>=0 && !cases[i-1][j].getValue()
						&& !cases[i-1][j].isEmpille() && num == 2){
						
						file.add(cases[i-1][j]);
						a++;
					}
					//chemin bas
					if(i+1<size && !cases[i+1][j].getValue()
						&& !cases[i+1][j].isEmpille() && num == 3){

						file.add(cases[i+1][j]);
						a++;
					}
				}

				if(a == 0)
					tour.removeLast();

				file.remove(); //supprime le 1er élément
			}
		}

		return false;
	}

	public double getSorthestPath(int x,int y, int xx, int yy){

		double somme = 0;
		int i = 0;

		while(i < 100){
			somme += (double) this.tours;
			this.findPath(x,y,xx,yy);
			i++;
		}

		return somme/100 - 1.0;
	}

}