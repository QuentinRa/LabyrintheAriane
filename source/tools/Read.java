/**
*
* 
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools;

import source.game.Game;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Read{

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
						System.out.print(tmp >> j & 00000001);
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
		}catch(IOException e){
			throw new IllegalStateException("Impossible de charger la sauvegarde");
		}
	}
}

/*
					byte tmp = file.readByte();
					for(int i=7; i >= 0; i--){
						System.out.print(tmp >> i & 00000001);
					}
					System.out.println();
				*/