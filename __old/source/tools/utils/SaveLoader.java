package source.tools.utils;

import source.tools.Grille;
import source.tools.Cases;
import source.tools.Window;
import source.tools.utils.ReaderBitByBit;
import source.tools.exceptions.ErrorPopup;
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
	* Lit un fichier de sauvegarde et le renvoi sous forme de Grille.
	* Lève un popup d'erreur s'il y a eu une erreur puis ferme le programme
	*
	* @param path chemin du fichier de sauvegarde
	* @param ecran ou afficher les messages d'erreurs
	*
	* @return Grille remplie avec le fichier de sauvegarde
	*
	* @see Grille
	* @see ErrorPopup
	*
	*/
	public static Grille getSave(String path, Window ecran){
		Grille grille = new Grille();

		try{
			//Ouverture du fichier
			FileInputStream flux = new FileInputStream(path);
			DataInputStream file = new DataInputStream(flux);

			try{
				//lis taille
				grille.setSize(file.read());
				//lis ligne joueur
				grille.setYPlayer(file.read());
				//lis colonne joueur
				grille.setXPlayer(file.read());
				//lis ligne sortie
				grille.setYExit(file.read());
				//lis colonne sortie
				grille.setXExit(file.read());
				int size = grille.getSize();
				//lis les 0 et 1
				boolean[][] buffer = ReaderBitByBit.read(file,size,size);
				//Inverse le tableau car la lecture est en ligne mais
				//on doit l'interpréter en colonnes
				Cases[][] cases = new Cases[size][size];
				for(int i=0; i<size; i++){
					for(int j=0; j<size; j++){
						cases[j][i] = new Cases(0,j,i);
						cases[j][i].setValue(buffer[i][j]);
					}
				}
				//crée la grille
				grille.setCasesArray(cases);
				if(grille.check() == false){ //vérifie les données
					//si invalide
					throw new InvalidDataException("");
				}
			}catch(IOException e){
				String message = "Erreur de la lecture de la sauvegarde";
				ErrorPopup error = new ErrorPopup(ecran,message);
			}catch(InvalidDataException e){
				String message = "Les données sont au mauvais format/invalides";
				ErrorPopup error = new ErrorPopup(ecran,message);
			}
		
			try{
				file.close();
			}catch(IOException e){
				String message = "Impossible de fermer la sauvegarde";
				ErrorPopup error = new ErrorPopup(ecran,message);
			}
		}catch(FileNotFoundException e){
			String message = "Impossible de charger la sauvegarde";
			ErrorPopup error = new ErrorPopup(ecran,message);
		}

		return grille;
	}
}