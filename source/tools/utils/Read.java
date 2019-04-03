package source.tools.utils;

import source.game.Game;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
*
* ...
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Read{

	/**
	*
	* ...
	*
	* @param game
	*
	* @throws IOException ...
	* @throws FileNotFoundException ...
	*
	*/
	public static void update(Game game){
		try{
			FileInputStream flux = new FileInputStream(game.getFilePath());
			DataInputStream file = new DataInputStream(flux);

			try{
				game.setSize(file.read());
				game.setYPlayer(file.read());
				game.setXPlayer(file.read());
				game.setYOut(file.read());
				game.setXOut(file.read());

				int size = game.getSize()*game.getSize();

				for(int i = 0; i< size; i+=8){
					byte tmp = file.readByte();
					for(int j=7; j >= 0; j--){
						System.out.print((tmp >> j & 00000001) == 1?true:false);
					}
					System.out.println();
				}
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
	}
}