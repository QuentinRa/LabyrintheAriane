package source.tools.utils;

import source.game.Grille;
import source.tools.utils.ReaderBitByBit;
import source.tools.exceptions.InvalidDataException;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
*
* Lit un fichier de sauvegarde
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class SaveLoader{
	/**
	*
	* Lit un fichier de sauvegarde et le renvoi sous forme de Grille
	*
	* @param path chemin du fichier de sauvegarde
	*
	* @return Grille remplie avec le fichier de sauvegarde
	*
	* @throws IOException si la fermeture du fichier &#233;choue, ou si la lecture
	* &#233;choue
	* @throws FileNotFoundException si l'ouverture du fichier à &#233;chou&#233;e
	* @throws InvalidDataException si les données n'ont pas le bon format
	*
	* @see Grille
	*
	*/
	public static Grille getSave(String path){
		Grille grille = new Grille();
		try{
			//Ouverture du fichier
			FileInputStream flux = new FileInputStream(path);
			DataInputStream file = new DataInputStream(flux);

			try{
				grille.setSize(file.read());
				grille.setYPlayer(file.read());
				grille.setXPlayer(file.read());
				grille.setYOut(file.read());
				grille.setXOut(file.read());
				int size = grille.getSize();
				boolean[][] buffer = ReaderBitByBit.read(file,size,size);
				//Inverse le tableau car la lecture est en ligne mais
				//on doit l'interpréter en colonnes
				boolean[][] cases = new boolean[size][size];
				for(int i=0; i<size; i++){
					for(int j=0; j<size; j++){
						cases[j][i] = buffer[i][j];
					}
				}
				grille.setCasesArray(cases);
			}catch(IOException e){
				String message = "Erreur de la lecture de la sauvegarde";
				throw new IllegalStateException(message);
			}catch(InvalidDataException e){
				String message = "Les données sont au mauvais format/invalides";
				throw new InvalidDataException(message);
			}
		
			try{
				file.close();
			}catch(IOException e){
				String message = "Impossible de fermer la sauvegarde";
				throw new IllegalStateException(message);
			}
		}catch(FileNotFoundException e){
			String message = "Impossible de charger la sauvegarde";
			throw new IllegalStateException(message);
		}

		return grille;
	}
}