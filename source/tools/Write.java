/**
*
* 
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Write{

	public static String createGame(){
		try{
			FileOutputStream flux = new FileOutputStream("../p.lab");
			DataOutputStream file = new DataOutputStream(flux);

			try{
				file.write(4);
				file.write(0);
				file.write(1);
				file.write(0);
				file.write(3);

				String str = "00000110";

				file.write(str.toBinaryString());
				file.write(str.toBinaryString());

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