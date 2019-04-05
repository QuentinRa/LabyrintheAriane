package source.tools;

import source.tools.utils.Background;

import java.awt.BorderLayout;
import java.awt.Component;
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

	/** la fen&#234;tre n'existe plus qu'à partir de son JPanel, c'est lui 
	qui sera manipul&#233;, cf r&#233;&#233;criture des add, ce qui fait un vrai 
	effet de fond */
	private Background screen;

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
		this.screen = new Background();
		this.add(this.screen, BorderLayout.CENTER);
	}

	public void setBackground(String background){
		this.screen.setBackground(background);
	}

	/**
	*
	* redessine le composant
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

	public Background getScreen(){
		return this.screen;
	}

	/**
	*
	* Appends the specified component to the end of this container.
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
	* Adds the specified component to this container at the given position.
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
	* Adds the specified component to the end of this container.
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
	* Removes all the components from this container. 
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
	* Revalidates the component hierarchy up to the nearest validate root.
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