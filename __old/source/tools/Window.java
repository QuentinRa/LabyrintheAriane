package source.tools;

import source.tools.utils.Background;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import javax.swing.JFrame;

/**
*
* Classe qui g&#232;re les opération li&#233;s à la fen&#234;tre
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Window extends JFrame{

	/** la fen&#234;tre n'existe plus qu'&#224; partir de son JPanel, c'est lui 
	qui sera manipul&#233;, cf r&#233;&#233;criture des add, ce qui fait un vrai 
	effet de fond */
	private Background screen;
	/** chemin du fond d'écran */
	private String background;

	/** positionner la fen&234;tre au centre horizontal et vertical &#233;cran */
	public static final int CENTER = 0;

	/**
	*
	* Construit une fen&#234;tre, cach&#233;e de base.
	*
	* @see #setVisible(boolean)
	*
	* @throws IllegalStateException si la cr&#233;ation de la fen&#234;tre
	* &#233;choue
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
	* @throws IllegalStateException si la cr&#233;ation de la fen&#234;tre
	* &#233;choue
	*
	*/
	public Window(String title){
		super(title);
		this.buildWindow(-1,-1);//taille auto
	}

	/**
	*
	* Construit une fen&#234;tre avec un titre de dimension (largeur et
	* hauteur) saisies, cach&#233;e de base.
	*
	* @param width largeur de la fen&#234;tre
	* @param height hauteur de la fen&#234;tre
	* @param title titre de la fen&#234;tre
	*
	* @see #setVisible(boolean)
	*
	* @throws IllegalStateException si la cr&#233;ation de la fen&#234;tre
	* &#233;choue
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
	* @param title titre de la fen&#234;tre
	*
	* @see #setVisible(boolean)
	*
	* @throws IllegalStateException si la cr&#233;ation de la fen&#234;tre
	* &#233;choue
	*
	*/
	private void buildWindow(int width,int height){
		if(width <= 0) width = 600;
		if(height <= 0) height = 600;
		if(width == 1 && height == 1)
			throw new IllegalStateException("Dimension Fen&#234;tre invalide!");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(Window.CENTER);
		this.screen = new Background();
		this.background = "";
		this.add(this.screen, BorderLayout.CENTER);
	}

	/**
	*
	* change le fond d'&#233;cran de la fen&#234;tre
	*
	* @param background le nouveau fond d'&#233;cran
	* null pour supprimer le fond.
	*
	* @throws IllegalStateException si la chargement du fond d'&#233;cran
	* &#233;choue
	*
	* @see #reloadBackground
	* @see #getBackgroundPath
	*
	*/
	public void setBackground(String background){
		this.screen.setBackground(background);
		this.background = background;
	}

	/**
	*
	* Recharge le fond d'&#233;cran
	*
	* @param value true pour recharger le fond d'&#233;cran sinon false
	*
	* @throws IllegalStateException si la chargement du fond d'&#233;cran
	* &#233;choue
	*
	* @see #setBackground
	* @see #getBackgroundPath
	*
	*/
	public void reloadBackground(boolean value){
		if(value){
			this.setBackground(background);
		}
	}

	/**
	*
	* Change la position de la fen&#234;tre
	*
	* @param constante une constante de la classe parmi :
	*	CENTER : centré horizontalement et verticalement par rapport &#224;
	*			l'&#233;cran
	*
	* @see setLocation(int,int)
	*
	*/
	public void setLocation(int constante){
		if(constante == Window.CENTER){
			Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((int)(ecran.getWidth()-this.getWidth())/2,
							(int)(ecran.getHeight()-this.getHeight())/2);
		}
	}

	/**
	*
	* Renvoi la largeur de l'&#233;cran dans lequel est la fen&#234;tre
	*
	* @return largeur du moniteur
	*
	*/
	public int getMonitorWidth(){
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) ecran.getWidth();
	}

	/**
	*
	* Renvoi la hauteur de l'&#233;cran dans lequel est la fen&#234;tre
	*
	* @return hauteur du moniteur
	*
	*/
	public int getMonitorHeight(){
		Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) ecran.getHeight();
	}

	/**
	*
	* redessine la fen&#234;tre
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
	* Renvoi le chemin du fond d'&#233;cran
	*
	* @return chaine vide si aucun fond d'&#233;cran sinon le chemin du dernier
	* fond ajout&#233; à la fen&#234;tre
	*
	* @see #setBackground
	* @see #reloadBackground
	*
	*/
	public String getBackgroundPath(){
		return this.background;
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
	* @param constaints contraintes sur la position du composant
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

	/**
	*
	* Supprime tous les composants du conteneur
	*
	*/
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
	*/
	public void revalidate(){
		//Si on a un écran
		if(this.screen != null)
			this.screen.revalidate();
		else
			super.revalidate();
	}

	/**
	*
	* d&#233;finit le gestionnaire de mise en page
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
}