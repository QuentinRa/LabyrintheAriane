package source.tools;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;

/**
*
* 
*
* @version 1.0 3 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class AreaGame extends JPanel{

	private int size;
	private final int xOffset = 100;
	private final int yOffset = 100;

	public AreaGame(int size){
		super();
		this.size = size;
	}
	
	@Override
	public void paintComponent(Graphics pinceau){
		// on crée un nouveau pinceau pour pouvoir le modifier plus tard
		Graphics secondPinceau = pinceau.create();
		// si le composant n'est pas censé être transparent
		if (this.isOpaque()){
			// on repeint toute la surface avec la couleur de fond
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		panel.setOpaque(false);

		JButton white = new JButton("white");
		white.setOpaque(false);
		panel.add(white);
		panel.add(new JButton("black"));
		panel.add(new JButton("player"));
		panel.add(new JButton("exit"));

		secondPinceau.setColor(Color.BLACK);
		secondPinceau.drawOval(xOffset, yOffset/2, this.getWidth()-xOffset-20, this.getHeight()-yOffset);

		this.add(panel, BorderLayout.WEST);

		this.revalidate();
	}
}