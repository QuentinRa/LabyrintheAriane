/**
*
* 
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools.utils;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Write{

	public static String createGame(){
		try{
			FileOutputStream flux = new FileOutputStream("../p.lab");
			DataOutputStream file = new DataOutputStream(flux);

			try{
				file.write(3);
				file.write(0);
				file.write(1);
				file.write(0);
				file.write(3);

				file.writeByte(Integer.parseInt("00000110",2));
				file.writeByte(Integer.parseInt("10000000",2));

				file.flush();

			}catch(IOException e){
				System.out.println("Erreur de la l'écriture de la sauvegarde");
			}

			try{
				file.close();
			}catch(IOException e){
				System.out.println("Impossible de fermer la sauvegarde");
			}
		}catch(IOException e){
			throw new IllegalStateException("Impossible de créer la sauvegarde");
		}

		return "../p.lab";
	}
}