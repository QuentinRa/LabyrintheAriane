package source.tools.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
*
* Charge un image, l&#232;ve une exception si une erreur survient.
* La largeur et la hauteur de l'image sont conserv&#233;es.
*
* @version 1.0 2 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class ImageLoader{

	/** largeur de l'image */
	private int width;
	/** hauteur de l'image */
	private int height;
	/** l'image sous la forme d'un bufferedImage */
	private BufferedImage image;

	/**
	*
	* Charge une image, et sauvegarde sa taille (hauteur, largeur)
	*
	* @param path le chemin de l'image
	*
	* @throws IOException si une &#233;tape du chargement de l'image
	* à &#233;chou&#233;e
	*
	*/
	public ImageLoader(String path){
		this.width = 0;
		this.height = 0;
		this.image = null;
		this.buildImage(path);
	}

	/**
	*
	* Cr&#233;e un composant pour contenir une image, avec emplacement pour
	* sauvegarder sa taille (hauteur, largeur).
	*
	*/
	public ImageLoader(){
		this.width = 0;
		this.height = 0;
		this.image = null;
	}

	/**
	*
	* renvoi une ImageLoader contenant une Image, ainsi que sa
	* largeur et sa hauteur
	*
	* @param path chemin de l'image &#224; charg&#233;e
	*
	* @throws IOException si une &#233;tape du chargement de l'image
	* à &#233;chou&#233;e
	*
	*/
	public void buildImage(String path){
		
		// 1 erreur == on quitte == echec
		try{
			//ouverture
			FileInputStream input = new FileInputStream(path);
			
			// lecture de l'image
			this.image = ImageIO.read(input);

			this.width = image.getWidth();
			this.height = image.getHeight();

			//fermeture
			input.close();
		}catch(IOException e){
			String msg = "Impossible de charger le fond d'écran";
			throw new IllegalStateException(msg);
		}
	}

	/**
	*
	* renvoi une image pr&#233;c&#233;demment charg&#233;e
	*
	* @return Image image charg&#233;e ou null si aucune
	*
	*/
	public BufferedImage getImage(){ return this.image; }

	/**
	*
	* renvoi la largeur de l'image charg&#233;e
	*
	* @return int largeur de l'image ou 0 si aucune image charg&#233;e
	*
	*/
	public int getWidth(){ return this.width; }

	/**
	*
	* renvoi la hauteur de l'image charg&#233;e
	*
	* @return int hauteur de l'image ou 0 si aucune image charg&#233;e
	*
	*/
	public int getHeight(){ return this.height; }
}