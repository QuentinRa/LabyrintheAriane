package game.utils;

import engine.graph.Grille;

import engine.graph.Case;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 *
 * <p>&#233;crit dans un fichier une sauvegarde selon un format.</p>
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
public class WriteLoader{

	/**
	 *
	 * &#233;crit une sauvegarde
	 *
	 * @param grille la grille du jeu
	 * @param path le chemin o&#249; de la sauvegarde, null pour dossier courant. Le nom de base
	 *             des fichiers de sauvegarde est sav.lab
	 *
	 * @throws IOException si une erreur d'écriture arrive.
	 *
	 */
	public static void writeSave(Grille grille, String path) throws IOException{

		if(path == null || path.length() == 0){
			path = "./sav.lab";
		}

		try{
			//Ouverture du fichier
			FileOutputStream flux = new FileOutputStream(path);
			DataOutputStream file = new DataOutputStream(flux);

			//écriture
			try{
				//écris taille
				file.write(grille.getSize());
				//écris ligne joueur
				file.write(grille.getYPlayer());
				//écris colonne joueur
				file.write(grille.getXPlayer());
				//écris ligne sortie
				file.write(grille.getYExit());
				//écris colonne sortie
				file.write(grille.getXExit());

				//écris les bits correspondants aux cases
				int size = grille.getSize();
				Case[][] cases = grille.getCasesArray();
				String octet = ""; //crée chaîne de 8 puis on l'écrit

				for (int i=0; i<size ; i++ ) {
					for(int j=0; j<size ; j++){
						octet += cases[i][j].getValue()?"1":"0";
						if(octet.length() == 8){
							file.writeByte(Integer.parseInt(octet,2));
							octet = "";
						}
					}
				}
				if(octet.length() != 0){
					while(octet.length() < 8){
						octet += "0";
					}
					file.writeByte(Integer.parseInt(octet,2));
				}

			}catch(IOException e){
				throw new IOException("Erreur lors de l'écriture de la sauvegarde");
			}

			//Fermeture du fichier
			try{
				file.close();
			}catch(IOException e){
				throw new IOException("Impossible de fermer la sauvegarde");
			}
		}catch(IOException e){
			throw new IOException("Impossible de créer la sauvegarde");
		}

	}
}