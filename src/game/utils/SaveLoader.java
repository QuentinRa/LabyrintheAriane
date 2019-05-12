package game.utils;

import engine.graph.Grille;
import engine.graph.Case;
import engine.exceptions.ErrorPopup;
import engine.exceptions.InvalidDataException;
import engine.utils.ReaderBitByBit;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 *
 * <p>Lit un fichier de sauvegarde selon un format.</p>
 *
 * Le format est le suivant :
 * <ul>
 *     <li>Le premier octet contient la taille de la grille.</li>
 *     <li>Le second octet contient la position y (ligne) du joueur.</li>
 *     <li>Le troisi&#232;me octet contient la position x (colonne) du joueur.</li>
 *     <li>Le quatri&#232;me octet contient la position y (ligne) de la sortie.</li>
 *	   <li>Le cinqui&#232;me octet contient la position x (colonne) de la sortie.</li>
 *	   <li>Le reste sont des bits, qui indique si une case est bloqu&#233;e : 0 = libre, 1 = bloqu&#233;e</li>
 * </ul>
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class SaveLoader{
	/**
	 *
	 * Lit un fichier de sauvegarde et le renvoi sous forme de Grille.
	 * L&#232;ve un popup d'erreur s'il y a eu une erreur puis ferme le programme.
	 *
	 * @param path chemin du fichier de sauvegarde
	 *
	 * @return Grille remplie avec le fichier de sauvegarde
	 *
	 * @throws IOException s'il y a une erreur de lecture
	 *
	 * @see Grille
	 * @see ErrorPopup
	 *
	 */
	public static Grille getSave(String path) throws IOException{
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
				Case[][] cases = new Case[size][size];
				for(int i=0; i<size; i++){
					for(int j=0; j<size; j++){
						cases[i][j] = new Case();
						cases[i][j].setPosition(i,j);
						try {
							cases[i][j].setValue(buffer[i][j]);
							//Si la taille > nombre de valeurs du buffer.
						} catch (IndexOutOfBoundsException e){
							throw new InvalidDataException("");
						}
					}
				}
				grille.setCasesArray(cases);
			}catch(IOException e){
				throw new IOException("Erreur de la lecture de la sauvegarde");
			}catch(InvalidDataException e){
				throw new IOException("Les données sont au mauvais format/invalides");
			}

			try{
				file.close();
			}catch(IOException e){
				throw new IOException("Impossible de fermer la sauvegarde");
			}
		}catch(FileNotFoundException e){
			throw new IOException("Impossible de charger la sauvegarde");
		}

		return grille;
	}
}