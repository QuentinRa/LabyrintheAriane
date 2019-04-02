/**
*
* Classe qui dessine une image
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
package source.tools;

import source.tools.ImageLoader;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;

public class Background extends JPanel{

	private ImageLoader image;

	public Background(String imagePath){
		super();
		this.setLayout(new BorderLayout());
		this.image = ImageLoader.getImage(imagePath);
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
		secondPinceau.drawImage(this.image.getImage(),
			0-this.image.getWidth()/2,0,this);
	}
}