package game.utils;

import engine.graph.Case;
import engine.graph.DrawGrille;
import engine.graph.Grille;
import engine.window.Window;
import engine.exceptions.ErrorPopup;
import game.GameCore;
import game.evenements.CreateGameButtonsListener;
import game.evenements.CreateGameFillButtonsListener;
import game.evenements.CreateGameListener;
import game.evenements.SaveAsListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * Menu de cr&#233;ation du jeu
 *
 * @version 1.0 3 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class CreateGame {

	/**
	 *
	 * &#233;cran sur lequel afficher le menu
	 *
	 */
	private Window screen;
	/**
	 *
	 * La grille de jeu
	 *
	 */
	private Grille grille;
	/**
	 *
	 * Observateur des boutons de cr&#233;ation du jeu
	 *
	 */
	private CreateGameListener createGameListener;
	/**
	 *
	 * Observateur du bouton de sauvegarde
	 *
	 */
	private SaveAsListener saveAsListener;
	/**
	 *
	 * Icon actuellement s&#233;lectionn&#233;e
	 *
	 */
	private ImageIcon selectedIcon;

	/**
	 *
	 * Cr&#233;e un menu de cr&#233;ation du jeu
	 *
	 * @param screen &#233;cran sur lequel afficher le menu
	 * @param grille grille du jeu
	 * @param gameCore partie principale du jeu, sert pour lancer jeu
	 *
	 */
	public CreateGame(Window screen, Grille grille, GameCore gameCore){
		this.screen = screen;
		this.grille = grille;
		this.createGameListener = new CreateGameListener(this.screen,this.grille,gameCore);
		this.saveAsListener = new SaveAsListener(this.screen,gameCore);
	}

	/**
	 *
	 * D&#233;ssine l'&#233;cran de cr&#233;ation de jeu
	 *
	 * @param randomlyFilled remplissage al&#233;atoire = true
	 *
	 */
	public void render(boolean randomlyFilled){
		//Ajuste la fenêtre si elle est trop petite et que l'écran le permet
		int size = this.grille.getSize()* Case.getCaseSize()+100; // 100 = marge bordures
		if(size > this.screen.getWidth() || size>this.screen.getHeight()){
			if(size<this.screen.getMonitorWidth() && size<this.screen.getMonitorHeight()) {
				this.screen.setSize(size, size); //augmente sa taille
				this.screen.setLocation(Window.ALIGN_CENTER); //centre par rapport à l'écran
			} else {
				String message = "La taille de l'écran ne permet pas le jeu sur une grille de cette taille";
				ErrorPopup errorPopup = new ErrorPopup(this.screen,message);
			}
		}

		if(randomlyFilled){
			this.grille.setCasesArray(this.grille.generate());
		} else {
			this.grille.createCasesArray();
		}

		//Listeners
		CreateGameButtonsListener gameButtons = new CreateGameButtonsListener(this,this.grille);
		CreateGameFillButtonsListener fillButtons = new CreateGameFillButtonsListener(this);

		this.grille.allAddListener(gameButtons);

		//Création des éléments
		//Jouer
		JButton play = new JButton("Jouer");
		JButton generate = new JButton("Re-générer");
		//Remplissage
		JButton wall = new JButton(Case.WALL);
		JButton clear = new JButton("effacer");
		JButton player = new JButton(Case.PLAYER);
		JButton exit = new JButton(Case.EXIT);
		//Save
		JLabel saveAs = new JLabel("Sauvegarder sous : ");
		JTextField saveName = new JTextField();
		//Observateurs
		play.addActionListener(this.createGameListener);
		generate.addActionListener(this.createGameListener);
		saveName.addActionListener(this.saveAsListener);
		wall.addActionListener(fillButtons);
		clear.addActionListener(fillButtons);
		player.addActionListener(fillButtons);
		exit.addActionListener(fillButtons);


		//Panneaux
		JPanel lunch = new JPanel(); //lancer jeu, relancer grille
		JPanel save = new JPanel(); //Sauvegarde
		JPanel fill = new JPanel(); //icônes remplissage de la grille
		lunch.setLayout(new BorderLayout());
		save.setLayout(new GridLayout(1,2));
		fill.setLayout(new GridLayout(4,1));

		lunch.add(play, BorderLayout.CENTER);
		lunch.add(generate, BorderLayout.EAST);
		save.add(saveAs);
		save.add(saveName);
		fill.add(wall);
		fill.add(clear);
		fill.add(player);
		fill.add(exit);

		//Création de la grille
		DrawGrille grille = new DrawGrille(this.grille);
		grille.setOpaque(false);

		//Ajoute et on re-valide (=on re-dessine l'écran)
		this.screen.setLayout(new BorderLayout());
		this.screen.add(lunch, BorderLayout.NORTH);
		this.screen.add(save, BorderLayout.SOUTH);
		this.screen.add(fill, BorderLayout.WEST);
		this.screen.add(grille, BorderLayout.CENTER);
		this.screen.revalidate();
	}

	/**
	 *
	 * Remplie de nouveau la grille
	 *
	 */
	public void repaint(){
		this.grille.setCasesArray(this.grille.generate());
		//RE-Création de la grille
		DrawGrille grille = new DrawGrille(this.grille);
		grille.setOpaque(false);
		this.screen.add(grille, BorderLayout.CENTER);
		this.screen.revalidate();
	}

	/**
	 *
	 * Renvoi l'icon de s&#233;lection
	 *
	 * @return l'icon de s&#233;lection
	 *
	 */
	public ImageIcon getSelectedIcon(){
		return this.selectedIcon;
	}

	/**
	 *
	 * Change l'icon de s&#233;lection
	 *
	 */
	public void setSelectedIcon(ImageIcon icon){
		this.selectedIcon = icon;
	}
}
