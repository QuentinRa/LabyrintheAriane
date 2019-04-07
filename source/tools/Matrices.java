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
		int[][] matrice = new int[matrice1.length][matrice1.length];

		if(matrice1.length != matrice2[0].length){
			//throw new InvalidMatrix()
			String message = "Le nombre de lignes de la 1ere matrice"+
			"doit être égal au nombre de colonnes de la 2eme matrice!";
			throw new IllegalStateException(message);
		}

		int i,j,number =0;
		int mi = 0, mj = 0;

		for(i=0; i<matrice1.length; i++){
			for(j=0; j<matrice2[0].length; j++){
				number += matrice1[i][j]*matrice2[j][i];
			}
			//matrice[mi][mj] = number;
			System.out.print(number);
			number = 0;
		}

		for(i=0; i < matrice.length ; i++){
			for(j=0; j<matrice[0].length; j++){
				System.out.print(matrice[i][j]);
			}
			System.out.println("");
		}

		/*	System.out.print(matrice1[i][0]*matrice2[0][i]);
			System.out.print(matrice1[i][1]*matrice2[1][i]);
			System.out.print(matrice1[i][2]*matrice2[2][i]);

			System.out.print(matrice1[i][3]*matrice2[3][i]);
			System.out.print(matrice1[i][4]*matrice2[4][i]);
			System.out.print(matrice1[i][5]*matrice2[5][i]);

			System.out.print(matrice1[i][6]*matrice2[6][i]);
			System.out.print(matrice1[i][7]*matrice2[7][i]);
			System.out.print(matrice1[i][8]*matrice2[8][i]);

		System.out.println("");*/


		System.exit(0);

		return null;
	}
}

/*System.out.print(matrice1[0][0]);
		System.out.print(matrice1[0][1]);
		System.out.print(matrice1[0][2]);
		System.out.print(matrice1[0][3]);
		System.out.print(matrice1[0][4]);
		System.out.print(matrice1[0][5]);
		System.out.print(matrice1[0][6]);
		System.out.print(matrice1[0][7]);
		System.out.print(matrice1[0][8]);

		System.out.println("");

		System.out.print(matrice2[0][0]);
		System.out.print(matrice2[1][0]);
		System.out.print(matrice2[2][0]);
		System.out.print(matrice2[3][0]);
		System.out.print(matrice2[4][0]);
		System.out.print(matrice2[5][0]);
		System.out.print(matrice2[6][0]);
		System.out.print(matrice2[7][0]);
		System.out.print(matrice2[8][0]);

		System.out.println("");

		//for(mi=0; mi < matrice1.length ; mi++ ){
		//	for(mj=0; mj < matrice2[0].length ; mj++ ){
				for(i=0; i<matrice1.length; i++){
					for(j=0, number = 0; j<matrice2[0].length; j++){
						number += matrice1[i][j]*matrice2[j][i];
					}
				}
				matrice[mi][mj] = number;
		//	}
		//}*/
