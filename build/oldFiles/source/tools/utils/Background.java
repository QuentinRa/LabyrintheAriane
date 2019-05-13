package source.tools.utils;

import source.tools.utils.ImageLoader;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
*
* Panneau qui contient une image de fond
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
	* Cr&#233;e un panneau pouvant avoir un fond
	*
	* @throws IllegalStateException si une &#233;tape du chargement de l'image
	* à &#233;chou&#233;e
	*
	* @see ImageLoader
	*
	*/
	public Background(){
		super();
		this.image = new ImageLoader();
	}

	/**
	*
	* Cr&#233;e un panneau avec une image de fond
	*
	* @param imagePath le chemin de l'image de fond
	*
	* @throws IllegalStateException si une &#233;tape du chargement de l'image
	* à &#233;chou&#233;e
	*
	* @see ImageLoader
	*
	*/
	public Background(String imagePath){
		super();
		if(imagePath!=null)
			this.image = new ImageLoader(imagePath);
		else
			this.image = new ImageLoader();
	}

	/**
	*
	* m&#233;thode appell&#233;e lors du dessin du composant
	*
	* @param pinceau le pinceau utilis&#233; pour dessiner
	*
	*/
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

		if(this.image != null)
		//Scale l'image pour qu'elle tienne dans le conteneur
		secondPinceau.drawImage(this.image.getImage(), 0, 0,this.getWidth(),
			this.getHeight(), this);
		else
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	*
	* Change l'image utilis&#233;e comme fond d'&#233;cran
	* null pour supprimer le fond.
	*
	* @param background l'image &#224; mettre en fond d'&#233;cran
	*
	*/
	public void setBackground(String background){
		if(background == null){
			this.image = null;
			this.repaint();
		}else if(background.length() != 0){
			this.image.buildImage(background);
		} else {
			this.image = null;
		}
	}
}