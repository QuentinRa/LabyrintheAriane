package engine.utils;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 *
 * Cr&#233;e un Panneau dont les &#233;l&#233;ments sont centr&#233;s horizontalement et verticalement.
 *
 * @version 1.0 4 mai 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class CenteredPanel extends JPanel{

	/**
	 *
	 * Contraintes pour placer les &#233;l&#233;ments
	 *
	 */
	private GridBagConstraints bag;

	/**
	 *
	 * Construit le panneau
	 *
	 */
	public CenteredPanel(){
		super();
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		this.bag = new GridBagConstraints();
	}

	/**
	 *
	 * Renvoi le gestionnaire des contraintes associ&#233; au panneau
	 *
	 * @return le gestionnaire des contraintes
	 *
	 */
	public GridBagConstraints getBag() {
		return this.bag;
	}
}
