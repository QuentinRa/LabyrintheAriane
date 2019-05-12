package engine.utils;

//import engine.utils.ImageLoader;

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
public class Background extends JPanel {
	
	//private static final long serialVersionUID = 4;

	/**
	 *
	 * Image
	 *
	 */
	private ImageLoader image;

	/**
	 *
	 * Cr&#233;e un panneau pouvant avoir une image de fond
	 *
	 * @throws engine.exceptions.LoadImageException si une &#233;tape du chargement de l'image
	 * &#224; &#233;chou&#233;e
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
	 * Cr&#233;e un panneau avec un fond
	 *
	 * @param imagePath le chemin du fond d'&#233;cran
	 *
	 * @throws engine.exceptions.LoadImageException si une &#233;tape du chargement de l'image
	 * &#224; &#233;chou&#233;e
	 *
	 * @see ImageLoader
	 *
	 */
	public Background(String imagePath){
		super();
		if(imagePath!= null) {
			this.image = new ImageLoader(imagePath);
		} else {
			this.image = new ImageLoader();
		}
	}

	/**
	 *
	 * M&#233;thode appel&#233; lors du dessin d'un composant
	 *
	 * @param pinceau le pinceau qui sert &#224; dessiner le composant
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

		//Si on a une image
		if(this.image != null) {
			//Scale l'image pour qu'elle tienne dans le conteneur
			secondPinceau.drawImage(this.image.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), this);
		} else {
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}

	/**
	 *
	 * Change le fond d'&#233;cran de la fen&#234;tre
	 *
	 * @param background chemin d'un fond d'&#233;cran ou null/cha&#238;e vide si aucun
	 *
	 * @throws engine.exceptions.LoadImageException si le chargement du fond d'&#233;cran &#233;choue
	 *
	 */
	public void setBackground(String background){
		if(background == null || background.length() == 0){
			this.image = null;
			this.repaint();
		}else {
			this.image.buildImage(background);
		}
	}

	/**
	 *
	 * Renvoi le chemin du fond d'&#233;cran
	 *
	 * @return le chemin du fond d'&#233;cran ou null si aucun
	 *
	 */
	public String getBackgroundPath(){
		String path = this.image.getPath();
		if(path == null || path.length() == 0){
			return null;
		} else {
			return path;
		}
	}
}
