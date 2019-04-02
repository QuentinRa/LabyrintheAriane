/**
*
* Le menu du jeu
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/

package source.game;

import source.tools.Window;
import source.tools.Background;
import source.tools.MenuButtonsListener;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

public class Menu{

	private Background ecran;
	private MenuButtonsListener menuListener;

	public Menu(Background ecran){
		this.ecran = ecran;
		this.menuListener = new MenuButtonsListener(this.ecran,this);
	}

	public void run(){
		this.ecran.setLayout(new GridLayout(2,1));
		GridBagConstraints bag = new GridBagConstraints();

		JPanel pJouer = new JPanel();
		pJouer.setLayout(new GridBagLayout());
		JButton jouer = new JButton("Créer");
		jouer.addActionListener(this.menuListener);
		pJouer.add(jouer,bag);
		pJouer.setOpaque(false);

		JPanel pCharger = new JPanel();
		pCharger.setLayout(new GridBagLayout());
		JButton charger = new JButton("Charger");
		charger.addActionListener(this.menuListener);
		pCharger.add(charger,bag);
		pCharger.setOpaque(false);

		this.ecran.add(pJouer);
		this.ecran.add(pCharger);

		this.ecran.revalidate();//met à jour l'écran
	}

	public void create(){
		System.out.println("Création du jeu");
	}

	public void load(){
		//Change pour layout qui prends tout l'écran
		this.ecran.setLayout(new BorderLayout());
		//Charge le gestionnaire de fichiers
		JFileChooser gestionnaireFichiers = new JFileChooser();
		this.ecran.add(gestionnaireFichiers, BorderLayout.CENTER);
		gestionnaireFichiers.addActionListener(this.menuListener);

		this.ecran.revalidate();//met à jour l'écran
	}
}