package source.tools.utils;

import java.io.IOException;
import java.io.DataInputStream;

/**
*
* Lit un fichier de sauvegarde
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

		boolean[] arrayBuffer = new boolean[size];

		for(int i = 0; i< size; i+=8){
			byte tmp = flux.readByte();
			for(int j=7; j >= 0; j--){
				arrayBuffer[i+7-j] = ((tmp >> j & 00000001) == 1)?true:false;
			}
		}

		//Conversion
		for(int i=0; i < sizeY; i++){
			for(int j=0; j < sizeX; j++){
				array[i][j] = arrayBuffer[i*sizeX + j];
			}
		}

		return array;
	}
}