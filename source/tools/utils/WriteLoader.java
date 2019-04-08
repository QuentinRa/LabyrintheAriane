package source.tools.utils;

import source.tools.Grille;
import source.tools.Cases;
import source.tools.Window;
import source.tools.exceptions.ErrorPopup;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
*
*
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class WriteLoader{

	public static void writeSave(Grille grille, String path, Window ecran){

		try{
			//Ouverture du fichier
			FileOutputStream flux = new FileOutputStream(path);
			DataOutputStream file = new DataOutputStream(flux);

			try{
				//ecris taille
				file.write(grille.getSize());
				//ecris ligne joueur
				file.write(grille.getYPlayer());
				//ecris colonne joueur
				file.write(grille.getXPlayer());
				//ecris ligne sortie
				file.write(grille.getYExit());
				//ecris colonne sortie
				file.write(grille.getXExit());

				int size = grille.getSize();

				Cases[][] cases = grille.getCasesArray();

				String str = "";

				for (int i=0; i<size ; i++ ) {
					for(int j=0; j<size ; j++){
						str+=cases[i][j].getValue()==true?"1":"0";
						if(str.length() == 8){
							System.out.println(str);
							file.writeByte(Integer.parseInt(str,2));
							str = "";
						}
					}
				}
				if(str.length() != 0){
					while(str.length() < 8){
						str += "0";
					}
					System.out.println(str);
					file.writeByte(Integer.parseInt(str,2));
				}
			}catch(IOException e){
				String message = "Erreur lors de l'écriture de la sauvegarde";
				ErrorPopup error = new ErrorPopup(ecran,message);
			}
		
			try{
				file.close();
			}catch(IOException e){
				String message = "Impossible de fermer la sauvegarde";
				ErrorPopup error = new ErrorPopup(ecran,message);
			}
		}catch(IOException e){
			String message = "Impossible de créer la sauvegarde";
			ErrorPopup error = new ErrorPopup(ecran,message);
		}
	}
}