/**
*
* Classe qui g&#232;re les opération li&#233;s à la fen&#234;tre
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.tools;

import source.tools.streams.DrawImage;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Window extends JFrame{

	/**
	*
	* Construit une fen&#234;tre, cachée de base.
	*
	* @see #setVisible(boolean)
	*
	*/
	public Window(){
		super();
		this.buildWindow(-1,-1);
	}

	/**
	*
	* Construit une fen&#234;tre avec un titre, cachée de base.
	*
	* @param title le titre de la fen&#234;tre
	*
	* @see #setVisible(boolean)
	*
	*/
	public Window(String title){
		super(title);
		this.buildWindow(-1,-1);
	}

	/**
	*
	* Construit une fen&#234;tre avec un titre de dimension (largeur et
	* hauteur) indiquées, cachée de base.
	*
	* @param width largeur de la fen&#234;tre
	* @param height hauteur de la fen&#234;tre
	* @param title titre de la fen&#234;tre
	*
	* @see #setVisible(boolean)
	*
	*/
	public Window(int width, int height,String title){
		super(title);
		this.buildWindow(width,height);
	}

	/**
	*
	* Cr&#233;e une fen&#234;tre pour les constructeurs, cachée de base.
	*
	* @param width largeur de la fen&#234;tre
	* @param height hauteur de la fen&#234;tre
	* @param title titre de la fen&#234;tre
	*
	* @see #setVisible(boolean)
	*
	*/
	private void buildWindow(int width,int height){
		if(width <= 0) width = 600;
		if(height <= 0) height = 600;
		if(width == 1 && height == 1)
			throw new IllegalStateException("Dimension Fenêtre invalide!");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(ecran.getWidth()-width)/2,
						 (int)(ecran.getHeight()-height)/2);
		this.setResizable(false);
	}

	public void setBackground(String file){
		// throws FileNotFoundException
		this.setContentPane(new DrawImage(file));
	}
}