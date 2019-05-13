package source.tools;

/**
*
* 
*
* @version 1.0 7 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Matrices{

	public static int[][] mul(int[][] matrice1, int[][] matrice2){
		int[][] matrice = new int[matrice1.length][matrice2[0].length];

		//vérification propriété multiplication des matrices
		if(matrice1.length != matrice2[0].length){
			//throw new InvalidMatrix()
			String message = "Le nombre de lignes de la 1ere matrice"+
			"doit être égal au nombre de colonnes de la 2eme matrice!";
			throw new IllegalStateException(message);
		}

		//calcul

		int i,j,ligne,colonne;

		//on cherche a mettre dans toutes les cases de matrice
		//le somme des produit des lignes de m1 avec colonnes de m2

		for(i=0; i< matrice1.length; i++){
			for(j=0; j< matrice2[0].length; j++){
				int valeur = 0;
				for(colonne=0; colonne < matrice2[0].length; colonne++){
					//on fixe la ligne avec celle de la case a remplir de matrice1
					//et on fixe la colonne de matrice2 avec celle de la case à remplir
					valeur += matrice1[i][colonne] * matrice2[colonne][j];
				}
				matrice[i][j] = valeur;
			}
		}

		return matrice;
	}
}