package source.tools;

import source.tools.utils.ImageLoader;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
*
* Panneau qui contient un fond d'écran (image)
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Background extends JPanel{

	/** Image */
	private ImageLoader image;

	/**
	*
	* Créer un panneau avec un fond d'écran
	*
	* @param imagePath le chemin du fond d'écran
	*
	* @throws IOException si une &#233;tape du chargement de l'image
	* à &#233;chou&#233;e
	*
	* @see ImageLoader
	*
	*/
	public Background(String imagePath){
		super();
		this.image = new ImageLoader(imagePath);
	}
	
	@Override
	public void paintComponent(Graphics pinceau){
		// on crée un nouveau pinceau pour pouvoir le modifier plus tard
		Graphics secondPinceau = pinceau.create();
		// si le composant n'est pas censé être transparent
		if (this.isOpaque()) {
			// on repeint toute la surface avec la couleur de fond
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		secondPinceau.drawImage(this.image.getImage(), 0, 0,this.getWidth(),
			this.getHeight(), this);
	}
}