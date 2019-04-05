package source.tools.utils;

import source.game.Grille;
import source.tools.utils.ReaderBitByBit;

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
	* @throws IOException si la fermeture du fichier &#233;choue, ou si la lecture &#233;choue
	* @throws FileNotFoundException si l'ouverture du fichier à &#233;chou&#233;e
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
				//Catcher les exceptions invalidFormat
				grille.setSize(file.read());
				grille.setYPlayer(file.read());
				grille.setXPlayer(file.read());
				grille.setYOut(file.read());
				grille.setXOut(file.read());
				int size = grille.getSize();
				grille.setCasesArray(ReaderBitByBit.read(file,size,size));
			}catch(IOException e){
				System.out.println("Erreur de la lecture de la sauvegarde");
			}
		
			try{
				file.close();
			}catch(IOException e){
				System.out.println("Impossible de fermer la sauvegarde");
			}
		}catch(FileNotFoundException e){
			throw new IllegalStateException("Impossible de charger la sauvegarde");
		}

		return grille;
	}
}