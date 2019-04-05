package source.tools;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;

/**
*
* Une case de notre jeu
*
* @version 1.0 5 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class Case extends JButton{

	private int x;
	private int y;
	private int width;
	private int height;
	private String icone;

	private boolean available;

	private boolean player;
	private boolean exit;

	public Case(int x, int y){
		super();
		this.width = 48;
		this.height = 48;
		this.setPreferredSize(new Dimension(this.width,this.height));
		this.setMinimumSize(new Dimension(this.width,this.height));
		this.setFocusable(false); //images ne sont par resurlignés après clic
		this.setRolloverEnabled(false);
		this.setFocusPainted(false);
		this.x = x;
		this.y = y;
		this.available = true;
		this.icone = "";
	}

	@Override
	public void setIcon(Icon defaultIcon){
		super.setIcon(defaultIcon);
		try{
			this.icone = this.getIcon().toString();
		}catch(NullPointerException e){
			//getIcon renvoi null si pas d'icone
			this.icone = "";
		}
		System.out.println(this.icone);
	}

	public void setXpos(int x){ this.x = x; }
	public int getXpos(){ return this.x; }
	public void setYpos(int y){ this.y = y; }
	public int getYpos(){ return this.y; }
	public void setCaseWidth(int width){ this.width = width; }
	public int getCaseWidth(){ return this.width; }
	public void setCaseHeight(int height){ this.height = height; }
	public int getCaseHeight(){ return this.height; }

	public void setAvailable(boolean available){ this.available = available; }
	public boolean isAvailable(){ return this.available; }
}