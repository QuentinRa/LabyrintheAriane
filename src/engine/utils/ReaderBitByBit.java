package engine.utils;

import java.io.IOException;
import java.io.DataInputStream;

/**
 *
 * Lit un flux bit par bit et le renvoi sous la forme d'un tableau de bool&#233;ens
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class ReaderBitByBit{
	/**
	 *
	 * Lit des bits dans un flux et renvoi les renvoi sous la forme d'un tableau de
	 * booleans
	 *
	 * @param flux dans lequel lire
	 * @param sizeX taille d'une ligne (largeur)
	 * @param sizeY taille d'une colonne (hauteur)
	 *
	 * @return tableau de boolean avec 0 = false et 1 = true
	 *
	 * @throws IOException si la lecture &#233;choue
	 *
	 */
	public static boolean[][] read(DataInputStream flux, int sizeX, int sizeY)
			throws IOException {
		boolean[][] array = new boolean[sizeX][sizeY];
		int size = sizeX * sizeY;

		boolean[] arrayBuffer = ReaderBitByBit.read(flux,size);

		//Conversion en [][]
		for(int i=0; i < sizeY; i++){
			for(int j=0; j < sizeX; j++){
				array[i][j] = arrayBuffer[i*sizeX + j];
			}
		}

		return array;
	}

	/**
	 *
	 * Lit des bits dans un flux et renvoi les renvoi sous la forme d'un tableau de
	 * booleans
	 *
	 * @param flux dans lequel lire
	 * @param size la taille du tableau
	 *
	 * @return tableau de boolean avec 0 = false et 1 = true
	 *
	 * @throws IOException si la lecture &#233;choue
	 *
	 */
	public static boolean[] read(DataInputStream flux, int size)
			throws IOException {
		boolean[] array = new boolean[size];

		//On va lire directement un octet donc 8 bits
		//on va ensuite regarde chaque bit en faisant des rotations
		//sur l'octect et un & bit a bit sur le dernier bit.

		// Soit un octet : 0000 0110
		// 0000 0110 >> 7 donne  0000 0000 & 1 = 0 et le 1er bit = 0 ok
		// 0000 0110 >> 6 donne  0000 0000 & 1 = 0 et le 2e bit = 0 ok
		// 0000 0110 >> 5 donne  0000 0000 & 1 = 0 et le 3e bit = 0 ok
		// 0000 0110 >> 4 donne  0000 0000 & 1 = 0 et le 4e bit = 0 ok
		// 0000 0110 >> 3 donne  0000 0000 & 1 = 0 et le 5e bit = 0 ok
		// 0000 0110 >> 2 donne  0000 0001 & 1 = 1 et le 6e bit = 1 ok
		// 0000 0110 >> 1 donne  0000 0001 & 1 = 1 et le 7e bit = 1 ok
		// 0000 0110 >> 0 donne  0000 0000 & 1 = 0 et le 8e bit = 0 ok

		int i,j;

		for(i = 0; i< size; i+=8){
			byte tmp = flux.readByte();
			for(j=7; j >= 0 && (i+7-j) < size; j--){
				//on considère des valeurs de 0 à 7 donc 8 = octet
				//la première valeur lue soit celle déplacement de 7
				//est dans la case 0 puis celle de 6 dans case 1 ...
				//et i est la position la ou mettre le bit
				array[i+7-j] = (tmp >> j & 1) == 1;
			}
		}

		return array;
	}
}
