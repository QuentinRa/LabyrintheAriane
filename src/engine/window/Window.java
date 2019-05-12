package engine.window;

import engine.exceptions.LoadImageException;
import engine.exceptions.WindowException;
import engine.utils.Background;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.LayoutManager;

/**
 *
 * Repr&#233;sentation d'une fen&#234;tre.
 *
 * @version 1.0 5 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class Window extends JFrame {

	/**
	 *
	 * Fond d'&#233;cran de la fen&#234;tre.
	 *
	 */
	private Background screen;

	/**
	 *
	 * Option de positionnement de la fen&#234;tre : centr&#233;e
	 *
	 * @see Window#setLocation(int)
	 *
	 */
	public static final int ALIGN_CENTER = 0;

	/**
	 *
	 * Construit une fen&#234;tre, cach&#233;e de base.
	 *
	 * @see #setVisible(boolean)
	 *
	 * @throws WindowException si la cr&#233;ation de la fen&#234;tre &#233;choue
	 *
	 */
	public Window(){
		super();
		this.buildWindow(-1,-1); //taille auto
	}

	/**
	 *
	 * Construit une fen&#234;tre avec un titre, cach&#233;e de base.
	 *
	 * @param title le titre de la fen&#234;tre
	 *
	 * @see #setVisible(boolean)
	 *
	 * @throws WindowException si la cr&#233;ation de la fen&#234;tre &#233;choue
	 *
	 */
	public Window(String title){
		super(title);
		this.buildWindow(-1,-1);//taille auto
	}

	/**
	 *
	 * Construit une fen&#234;tre avec un titre de dimensions largeur x hauteur, cach&#233;e de base.
	 *
	 * @param width largeur de la fen&#234;tre
	 * @param height hauteur de la fen&#234;tre
	 * @param title titre de la fen&#234;tre
	 *
	 * @see #setVisible(boolean)
	 *
	 * @throws WindowException si la cr&#233;ation de la fen&#234;tre &#233;choue
	 *
	 */
	public Window(int width, int height,String title){
		super(title);
		this.buildWindow(width,height);
	}

	/**
	 *
	 * Cr&#233;e une fen&#234;tre pour les constructeurs, cach&#233;e de base.
	 *
	 * @param width largeur de la fen&#234;tre
	 * @param height hauteur de la fen&#234;tre
	 *
	 * @see #setVisible(boolean)
	 *
	 * @throws WindowException si la cr&#233;ation de la fen&#234;tre &#233;choue
	 *
	 */
	private void buildWindow(int width,int height){
		if(width <= 0) width = 600;
		if(height <= 0) height = 600;
		if(width == 1 && height == 1) {
			throw new WindowException("Dimension Fen&#234;tre invalide!");
		}
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(Window.ALIGN_CENTER);
		this.screen = new Background();
		this.add(this.screen, BorderLayout.CENTER);
	}

	/**
	 *
	 * Change la position de la fen&#234;tre
	 *
	 * @param constante une constante de la classe parmi :
	 *	<ul>
	 *		<li>ALIGN_CENTER : centr&#233;e horizontalement et verticalement par rapport &#224; l'&#233;cran</li>
	 *	</ul>
	 *
	 * @see JFrame#setLocation(int, int)
	 *
	 */
	public void setLocation(int constante){
		if(constante == Window.ALIGN_CENTER){
			//Récupération des dimensions de l'écran
			Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
			if(ecran != null) {
				this.setLocation((int) (ecran.getWidth() - this.getWidth()) / 2,
						(int) (ecran.getHeight() - this.getHeight()) / 2);
			} else {
				String msg = "Impossible de récupérer le dimensions de l'écran.";
				throw new WindowException(msg);
			}
		}
	}

	/**
	 *
	 * Change le fond d'&#233;cran de la fen&#234;tre
	 *
	 * @param background le nouveau fond d'&#233;cran. null pour supprimer le fond.
	 *
	 * @throws WindowException si la chargement du fond d'&#233;cran &#233;choue
	 *
	 */
	public void setBackground(String background){
		try {
			this.screen.setBackground(background);
		} catch (LoadImageException e){
			throw new WindowException(e.getMessage());
		}
	}

	/**
	 *
	 * Renvoi le chemin du fond d'&#233;cran
	 *
	 * @return null si aucun fond d'&#233;cran sinon le chemin du dernier fond ajout&#233; &#224; la fen&#234;tre
	 *
	 * @see #setBackground
	 *
	 */
	public String getBackgroundPath(){
		return this.screen.getBackgroundPath();
	}

	/**
	 *
	 * Renvoi la largeur de l'&#233;cran dans lequel est la fen&#234;tre
	 *
	 * @return largeur du moniteur
	 *
	 * @throws WindowException si la r&#233;cup&#233;ration des dimensions de l'&#233;cran &#233;choue
	 *
	 */
	public int getMonitorWidth(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		if(screen != null) {
			return (int) screen.getWidth();
		} else {
			String message = "Impossible de récupérer les dimensions de l'écran.";
			throw new WindowException(message);
		}
	}

	/**
	 *
	 * Renvoi la hauteur de l'&#233;cran dans lequel est la fen&#234;tre
	 *
	 * @return hauteur du moniteur
	 *
	 * @throws WindowException si la r&#233;cup&#233;ration des dimensions de l'&#233;cran &#233;choue
	 *
	 */
	public int getMonitorHeight(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		if(screen != null) {
			return (int) screen.getHeight();
		} else {
			String message = "Impossible de récupérer les dimensions de l'écran.";
			throw new WindowException(message);
		}
	}

	/**
	 *
	 * re-dessine la fen&#234;tre
	 *
	 * @see JFrame#repaint()
	 *
	 */
	@Override
	public void repaint(){
		//Si on a un écran
		if(this.screen != null)
			this.screen.repaint();
		else
			super.repaint();
	}

	/**
	 *
	 * Supprime tous les composants du conteneur
	 *
	 * @see JFrame#removeAll()
	 *
	 */
	@Override
	public void removeAll(){
		//Si on a un écran
		if(this.screen != null)
			this.screen.removeAll();
		else
			super.removeAll();
	}

	/**
	 *
	 * Revalide les composants
	 *
	 * @see JFrame#revalidate()
	 *
	 */
	@Override
	public void revalidate(){
		//Si on a un écran
		if(this.screen != null)
			this.screen.revalidate();
		else
			super.revalidate();
	}

	/**
	 *
	 * D&#233;finit le gestionnaire de mise en page
	 *
	 * @see JFrame#setLayout(LayoutManager)
	 *
	 */
	@Override
	public void setLayout(LayoutManager manager){
		//Si on a un écran
		if(this.screen != null)
			this.screen.setLayout(manager);
		else
			super.setLayout(manager);
	}

	/**
	 *
	 * Ajoute un composant &#224; la fen&#234;tre
	 *
	 * @param comp le composant &#224; ajouter
	 *
	 * @see JFrame#add(Component)
	 *
	 */
	@Override
	public Component add(Component comp){
		//Si on a un écran et que l'on ajoute autre chose que
		//celui a on l'ajoute à l'écran
		if(this.screen != null && this.screen!=comp)
			return this.screen.add(comp);
		else
			return super.add(comp);
	}

	/**
	 *
	 * Ajoute un composant &#224; la fen&#234;tre &#224; la position index
	 *
	 * @param comp le composant &#224; ajouter
	 * @param index la position &#224; donner au composant
	 *
	 * @see JFrame#add(Component,int)
	 *
	 */
	@Override
	public Component add(Component comp, int index){
		//Si on a un écran et que l'on ajoute autre chose que
		//celui a on l'ajoute à l'écran
		if(this.screen != null && this.screen!=comp)
			return this.screen.add(comp,index);
		else
			return super.add(comp,index);
	}

	/**
	 *
	 * Ajoute un composant &#224; la fen&#234;tre &#224; la fin du conteneur.
	 *
	 * @param comp le composant &#224; ajouter
	 * @param constraints contraintes sur la position du composant
	 *
	 * @see JFrame#add(Component, Object)
	 *
	 */
	@Override
	public void	add(Component comp, Object constraints){
		//Si on a un écran et que l'on ajoute autre chose que
		//celui a on l'ajoute à l'écran
		if(this.screen != null && this.screen!=comp)
			this.screen.add(comp,constraints);
		else
			super.add(comp,constraints);
	}
}
