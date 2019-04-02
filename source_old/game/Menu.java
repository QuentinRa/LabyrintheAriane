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
import source.tools.streams.DrawImage;
import source.tools.listeners.MenuButtonsListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Menu{

	private JPanel menu;

	public Menu(JPanel ecran){
		this.menu = ecran;
	}

	public void build(){
		try{
			DrawImage fond = new DrawImage("ressources/rem.png");
			fond.setLayout(new BorderLayout());
			
			JButton charger = new JButton("Charger");
			charger.addActionListener(new MenuButtonsListener(this.menu));
			
			fond.add(charger,BorderLayout.SOUTH);
			this.menu.add(fond);
		}catch(IllegalStateException e){
			//erreur de chargement du fond d'écran, on continue
			System.err.println(e.getMessage());
		}
	}
}