/**
*
* 
*
* @version 1.0 11 mars 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools.streams;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import java.awt.Image;

public class ImageLoader{

	private int width;
	private int height;
	private Image image;

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
	* @return ImageLoader qui contient iamge,width,height
	*
	* @throws IOException si une &#233;tape à &#233;chou&#233;e
	*
	*/
	public static ImageLoader getImage(String path){
		BufferedImage img = null; //notre future image
		ImageLoader loader = new ImageLoader();

		// 1 erreur = on quitte
		try{
			//ouverture
			FileInputStream input = new FileInputStream(path);
			
			// lecture de l'image
			img = ImageIO.read(input);

			loader.width = img.getWidth();
			loader.height = img.getHeight();

			//fermeture
			input.close();
		}catch(IOException e){
			String msg = "Impossible de charger le fond d'écran";
			throw new IllegalStateException(msg);
		}

		loader.image = img;

		return loader;
	}

	/**
	*
	* renvoi une image pr&#233;c&#233;demment charg&#233;e
	*
	* @return Image image charg&#233;e ou null si aucune
	*
	*/
	public Image getImage(){ return this.image; }

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